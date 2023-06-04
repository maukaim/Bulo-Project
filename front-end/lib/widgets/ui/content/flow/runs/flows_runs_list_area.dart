import 'package:bulo_ui/domains/flow_runs/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'items/flow_run_item.dart';

class FlowRunsListArea extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var flowRuns = ref.watch(flowRunsOfFlowSelectedProvider);
    return flowRuns.when(
      loading: () => const CircularProgressIndicator(),
      error: (err, stack) =>
          const Center(child: Text("Impossible to load Runs History")),
      data: (runs) => Expanded(
        child: ListView.builder(
          shrinkWrap: true,
          itemCount: runs.length,
          itemBuilder: (ctx, index) => FlowRunItem(
            flowRunView: runs[index],
          ),
        ),
      ),
    );
  }
}
