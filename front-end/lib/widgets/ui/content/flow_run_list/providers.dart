import 'package:bulo_ui/domains/flow_runs/flow_runs_service.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../domains/flow_runs/providers.dart';
import '../../nav/nav_content/flows/providers.dart';

final AutoDisposeFutureProvider<List<String>> flowRunsListProvider =
    FutureProvider.autoDispose<List<String>>((ref) async {
  final selectedFlow = ref.watch(selectedFlowProvider);
  if (selectedFlow.isNotEmpty) {
    final FlowRunsService flowRunService = ref.watch(flowRunsServiceProvider);
    return flowRunService.getByFlowId(selectedFlow, true);
  }
  return List.empty();
});
