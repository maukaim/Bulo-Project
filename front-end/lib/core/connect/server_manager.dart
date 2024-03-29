import 'package:bulo_ui/core/connect/model/remote_server_config.dart';
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerManager {
  final Map<String, ServerConfig> serverMap = {};
  final ProviderRef<ServerManager> providerRef;

  ServerManager(this.providerRef) {
    var fake1 = RemoteServerConfig("localhost", 10020, "Local server");
    var fake2 = RemoteServerConfig("load-balancer-jenkins-master-1462245991.ap-northeast-1.elb.amazonaws.com", 10020, "Dev AWS Server");
    serverMap.addAll({
      fake1.id: fake1,
      fake2.id: fake2,
    });
  }

  switchCurrentServer(ServerConfig? server) {
    getCurrentServerController().state = server;
  }

  ServerConfig? getById(String id) {
    return serverMap[id];
  }

  add(ServerConfig server) {
    serverMap.addAll({server.id: server});
    if (serverMap.length == 1 || getCurrentServerController().state == null) {
      getCurrentServerController().state = server;
    }
    providerRef.invalidate(availableServersProvider);
  }

  delete(String serverId, WidgetRef widgetRef) {
    ServerConfig? server = serverMap.remove(serverId);

    if (server == widgetRef.read(currentServerProvider)) {
      getCurrentServerController().state = serverMap.values.firstOrNull;
    }

    providerRef.invalidate(serverConnectorProvider(server));
    providerRef.invalidate(availableServersProvider);
  }

  List<ServerConfig> get allServers {
    return serverMap.values.toList();
  }

  StateController<ServerConfig?> getCurrentServerController() {
    return providerRef.read(currentServerProvider.notifier);
  }
}
