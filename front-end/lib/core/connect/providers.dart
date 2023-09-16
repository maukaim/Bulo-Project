import 'dart:async';
import 'dart:io';

import 'package:bulo_ui/core/connect/model/embedded_server_config.dart';
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/server_manager.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'server_connector.dart';

final serverManagerProvider = Provider<ServerManager>((ref) {
  return ServerManager(ref);
});

final serverConnectorProvider = Provider.autoDispose
    .family<ServerConnector, ServerConfig?>((ref, currentServ) {
  print("Construct my backend Connector with...$currentServ");

  return ServerConnector(currentServ);
});

final currentServerProvider = StateProvider<ServerConfig?>((ref) {
  var serverManager = ref.watch(serverManagerProvider);
  List<ServerConfig> allPreConfigServers = serverManager.allServers;
  return allPreConfigServers.firstOrNull;
});

// Embedded Server Management
const hasEmbeddedServerChannel =
    MethodChannel('com.maukaim.bulo/hasEmbeddedServer');
final hasEmbeddedServerProvider = FutureProvider<bool>((ref) async {
  var serverManager = ref.watch(serverManagerProvider);
  final bool hasEmbeddedServer =
      await hasEmbeddedServerChannel.invokeMethod('hasEmbeddedServer');
  if (hasEmbeddedServer) {
    serverManager.add(EmbeddedServer(10020, "dummyPathUnusedAnyway"));
  }
  return hasEmbeddedServer;
});

final isServerConnectedProvider = FutureProvider.autoDispose
    .family<bool, ServerConnector>((ref, serverConnector) async {
  if (serverConnector.serverConfig == null) {
    return false;
  }

  Socket? socket;
  try {
    socket = await Socket.connect(serverConnector.serverConfig!.addressRoot,
        serverConnector.serverConfig!.port,
        timeout: Duration(seconds: 2));
    return true;
  } catch (e) {
    print('Server is down: $e');
    return false;
  } finally {
    socket?.destroy();
    ref.listenSelf((previous, next) {
      Timer(const Duration(seconds: 10), () {
        //TODO: Looks like long pooling. No better way before Servers support socket?
        ref.invalidateSelf();
      });
    });
  }
});

ServerConnector getCurrentServerConnector(dynamic ref) {
  return ref.watch(serverConnectorProvider(ref.watch(currentServerProvider)));
}

ServerConnector getServerConnector(dynamic ref, ServerConfig serverConfig) {
  return ref.watch(serverConnectorProvider(serverConfig));
}
