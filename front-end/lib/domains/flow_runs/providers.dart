import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:bulo_ui/domains/flow_runs/flow_runs_service.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../widgets/main_window/nav/nav_content/flows/providers.dart';
import 'model/flow_run.dart';

final flowRunsServiceProvider = Provider.autoDispose
    .family<FlowRunsService, ServerConnector>((ref, serverConnector) {
  return FlowRunsService(serverConnector);
});

var flowRunsOfFlowSelectedProvider =
    FutureProvider.autoDispose<List<FlowRunView>>((ref) async {
  final selectedFlow = ref.watch(selectedFlowProvider);
  if (selectedFlow != null && selectedFlow.isNotEmpty) {
    final FlowRunsService flowRunService =
        ref.watch(flowRunsServiceProvider(getCurrentServerConnector(ref)));
    List<FlowRunView> flowRuns = await flowRunService.getByFlowId(selectedFlow);
    flowRuns.sort((a, b) => b.endTime == null
        ? 1
        : a.endTime == null
            ? -1
            : b.endTime!.compareTo(a.endTime!));
    return flowRuns;
  } else {
    return List.empty();
  }
});
