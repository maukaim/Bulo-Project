import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'flow_service.dart';
import 'model/flow.dart';

final flowServiceProvider = Provider.autoDispose
    .family<FlowService, ServerConnector>((ref, serverConnector) {
  return FlowService(serverConnector);
});

final flowsAvailableProvider = FutureProvider.autoDispose
    .family<List<Flow>, ServerConnector>((ref, serverConnector) async {
  var flowService = ref.watch(flowServiceProvider(serverConnector));
  return flowService.getAll();
});
