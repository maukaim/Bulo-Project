import 'package:bulo_ui/widgets/ui/content/flow_run_list/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class FlowRunsList extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final flowRunsAsync = ref.watch(flowRunsListProvider);
    return flowRunsAsync.when(
      loading: () => const Center(child: CircularProgressIndicator()),
      error: (err, stack) => Text('Error: $err'),
      data: (runs) {
        return ListView(
          children: runs.map((flowRun) => Text(flowRun)).toList(),
        );
      },
    );
  }
}
