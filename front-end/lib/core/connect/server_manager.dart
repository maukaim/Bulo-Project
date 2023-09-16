import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:riverpod/src/provider.dart';
import 'package:riverpod/src/state_controller.dart';

class ServerManager {
  final Map<String, ServerConfig> serverMap = {};
  final ProviderRef<ServerManager> providerRef;

  ServerManager(this.providerRef);

  switchCurrentServer(ServerConfig? server) {
    getCurrentServerController().state = server;
  }

  ServerConfig? getById(String id) {
    return serverMap[ServerConfig];
  }

  add(ServerConfig server) {
    serverMap.addAll({server.id: server});
    if (serverMap.length == 1) {
      getCurrentServerController().state = server;
    }
  }

  delete(String serverId) {
    ServerConfig? server = serverMap.remove(serverId);

    if (server == providerRef.watch(currentServerProvider)) {
      getCurrentServerController().state = serverMap.values.firstOrNull;
    }

    //invalidate the providers relying on this record. The dependents chain should ALL be GC.
    providerRef.invalidate(serverConnectorProvider(server));
  }

  List<ServerConfig> get allServers {
    return serverMap.values.toList();
  }

  StateController<ServerConfig?> getCurrentServerController() {
    return providerRef.read(currentServerProvider.notifier);
  }
}
