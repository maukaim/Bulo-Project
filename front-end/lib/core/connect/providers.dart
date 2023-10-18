import 'dart:async';
import 'dart:io';

import 'package:bulo_ui/core/connect/model/embedded_server_config.dart';
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/server_manager.dart';
import 'package:bulo_ui/core/log/providers.dart';
import 'package:bulo_ui/core/util/current_system.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'server_connector.dart';

final serverManagerProvider = Provider<ServerManager>((ref) {
  return ServerManager(ref);
});

final serverConnectorProvider = Provider.autoDispose
    .family<ServerConnector, ServerConfig?>((ref, serverConfig) {
      var logger = ref.watch(loggerProvider(serverConfig));
  return ServerConnector(serverConfig,logger);
});

final currentServerProvider = StateProvider<ServerConfig?>((ref) => null);

// Embedded Server Management
const hasEmbeddedServerChannel =
    MethodChannel('com.maukaim.bulo/hasEmbeddedServer');
final hasEmbeddedServerProvider = FutureProvider<bool>((ref) async {
  var serverManager = ref.watch(serverManagerProvider);
  final bool hasEmbeddedServer =
      await hasEmbeddedServerChannel.invokeMethod('hasEmbeddedServer');
  if (hasEmbeddedServer) {
    serverManager.add(EmbeddedServerConfig(10020, "dummyPathUnusedAnyway"));
  }
  return hasEmbeddedServer;
});

final isServerConnectedProvider = FutureProvider.autoDispose
    .family<bool, ServerConnector>((ref, serverConnector) async {
  var serverConfig = serverConnector.serverConfig;
  if (serverConfig == null) {
    return false;
  }
  final logger = ref.watch(loggerProvider(serverConfig));
  String? isConnectedErrorMessage = await isServerConnected(serverConnector);

  String serverQualifier = "${serverConfig.serverName} at ${serverConfig.addressRoot}:${serverConfig.port}";
  if(isConnectedErrorMessage == null){
    logger.info("Successfully pinged $serverQualifier !");
  }else{
    logger.error("Could not contact $isConnectedErrorMessage");
  }

  ref.listenSelf((previous, next) {
    Timer(const Duration(seconds: 10), () {
      //TODO: Looks like long pooling. No better way before Servers support socket?
      ref.invalidateSelf();
    });
  });

  return isConnectedErrorMessage == null;
});

Future<String?> isServerConnected(ServerConnector serverConnector) async {
  var currentSystem = getCurrentSystem();
  if(currentSystem.isDesktop){
    return isServerConnectedDesktop(serverConnector);
  }else{
    return isServerConnectedWeb(serverConnector);
  }
}

Future<String?> isServerConnectedWeb(ServerConnector serverConnector) async {
  try {
    return await serverConnector.healthCheck() ? null : "Server is down. Check logs.";

  } catch (e) {
    debugPrint('Server is down: $e');
    return 'Server is down: $e';
  }
}

Future<String?> isServerConnectedDesktop(ServerConnector serverConnector) async {
  Socket? socket;
  try {
    socket = await Socket.connect(serverConnector.serverConfig!.addressRoot,
        serverConnector.serverConfig!.port,
        timeout: const Duration(seconds: 2));
    return null;
  } catch (e) {
    debugPrint('Server is down: $e');
    return 'Server is down: $e';
  } finally {
    socket?.destroy();
  }
}

ServerConnector getCurrentServerConnector(dynamic ref) {
  return ref.watch(serverConnectorProvider(ref.watch(currentServerProvider)));
}

ServerConnector getServerConnector(dynamic ref, ServerConfig serverConfig) {
  return ref.watch(serverConnectorProvider(serverConfig));
}
