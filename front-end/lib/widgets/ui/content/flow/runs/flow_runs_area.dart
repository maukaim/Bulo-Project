import 'package:bulo_ui/domains/flow_runs/model/flow_run.dart';
import 'package:bulo_ui/domains/flow_runs/model/orchestrable_status.dart';
import 'package:bulo_ui/domains/flow_runs/providers.dart';
import 'package:bulo_ui/widgets/global/orchestrable_status_badge.dart';
import 'package:bulo_ui/widgets/ui/content/flow/runs/flows_runs_list_area.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_content/flows/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../providers.dart';

class FlowRunsArea extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var flowSelected = ref.watch(selectedFlowProvider);
    var flowRuns = ref.watch(flowRunsOfFlowSelectedProvider);
    // TODO: implement build
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            InkWell(
              borderRadius: BorderRadius.circular(4),
              hoverColor: Colors.blueGrey.withOpacity(0.8),
              onTap: () {
                ref.read(isOnRunModeProvider.notifier).state = false;
              },
              child: const Icon(
                size: 24,
                CupertinoIcons.pencil,
                color: Colors.blueGrey,
              ),
            ),
            const SizedBox(
              width: 18,
            ),
            Title(
              color: Colors.black,
              child: Text(
                'Flow $flowSelected',
                style: const TextStyle(
                    color: Colors.black87,
                    fontWeight: FontWeight.bold,
                    fontSize: 24),
              ),
            ),
            const SizedBox(
              width: 12,
            ),
            LastRunStatusBadge()
            // Widget pour le badge  de success/ error
          ],
        ),
        flowRuns.when(
          data: (data) => Text("Runs History (${data.length})"),
          error: (err, stack) => const Text("Impossible to load Runs History"),
          loading: () => const Text("Runs History (loading)"),
        ),
        const Divider(
          thickness: 2,
          color: Colors.black54,
        ),
        FlowRunsListArea()
      ],
    );
  }

// @override
// Widget build(BuildContext context, WidgetRef ref) {
//   final flowRunsAsync = ref.watch(flowRunsOfFlowSelectedProvider);
//   return flowRunsAsync.when(
//     loading: () => const Center(child: CircularProgressIndicator()),
//     error: (err, stack) => Text('Error: $err'),
//     data: (runs) {
//       return ListView(
//         children: runs.map((flowRun) => Text(flowRun.flowRunId)).toList(),
//       );
//     },
//   );
// }
}

class LastRunStatusBadge extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final flowRunsAsync = ref.watch(flowRunsOfFlowSelectedProvider);
    return flowRunsAsync.when(
      loading: () =>
          const OrchestrableStatusBadge(status: OrchestrableStatus.RUNNING),
      error: (err, stack) =>
          const OrchestrableStatusBadge(status: OrchestrableStatus.RUNNING),
      data: (flowRuns) {
        return OrchestrableStatusBadge(status: getLastRunStatus(flowRuns));
      },
    );
  }

  OrchestrableStatus getLastRunStatus(List<FlowRunView> runs) {
    if (runs.isEmpty) {
      return OrchestrableStatus.UNKNOWN;
    } else {
      return runs.first.status;
    }
  }
}
