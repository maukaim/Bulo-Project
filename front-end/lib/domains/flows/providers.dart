import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:bulo_ui/core/log/providers.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'flow_service.dart';
import 'model/flow.dart';

final flowServiceProvider = Provider.autoDispose
    .family<FlowService, ServerConnector>((ref, serverConnector) {
  var logger = ref.watch(loggerProvider(serverConnector.serverConfig));
  return FlowService(serverConnector, logger);
});

final flowsAvailableProvider = FutureProvider.autoDispose
    .family<List<Flow>, ServerConnector>((ref, serverConnector) async {
  var flowService = ref.watch(flowServiceProvider(serverConnector));
  return flowService.getAll();
});
