import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:bulo_ui/domains/flow_runs/flow_runs_service.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../widgets/main_window/nav/nav_content/flows/providers.dart';
import 'model/flow_run.dart';
import 'package:bulo_ui/domains/flows/model/flow.dart';


final flowRunsServiceProvider = Provider.autoDispose
    .family<FlowRunsService, ServerConnector>((ref, serverConnector) {
  return FlowRunsService(serverConnector);
});

var flowRunsOfFlowProvider = FutureProvider.autoDispose.family<List<FlowRunView>, Flow>((ref, flow) async{
  return getFlowRuns(flow.flowId, ref);
});

var flowRunsOfFlowSelectedProvider =
    FutureProvider.autoDispose<List<FlowRunView>>((ref) async {
  final selectedFlowId = ref.watch(getSelectedFlowProvider(ref));
  return getFlowRuns(selectedFlowId, ref);

});

Future<List<FlowRunView>> getFlowRuns(String flowId, dynamic ref) async {
  if (flowId != null && flowId.isNotEmpty) {
    final FlowRunsService flowRunService =
    ref.watch(flowRunsServiceProvider(getCurrentServerConnector(ref)));
    List<FlowRunView> flowRuns = await flowRunService.getByFlowId(flowId);
    flowRuns.sort((a, b) => b.endTime == null
        ? 1
        : a.endTime == null
        ? -1
        : b.endTime!.compareTo(a.endTime!));
    return flowRuns;
  } else {
    return List.empty();
  }
}
