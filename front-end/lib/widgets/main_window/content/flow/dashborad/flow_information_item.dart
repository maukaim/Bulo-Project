import 'package:bulo_ui/domains/flow_runs/model/flow_run.dart';
import 'package:bulo_ui/domains/flow_runs/model/orchestrable_status.dart';
import 'package:bulo_ui/domains/flow_runs/providers.dart';
import 'package:flutter/material.dart' hide Flow;
import 'package:flutter/widgets.dart' hide Flow;
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:bulo_ui/domains/flows/model/flow.dart';





class FlowInformationItem extends ConsumerWidget {
  final Flow flow;

  FlowInformationItem({super.key, required this.flow});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    AsyncValue<List<FlowRunView>> asyncFlowRuns = ref.watch(flowRunsOfFlowProvider(flow));

    return DataTable(
      columns: [
        DataColumn(label: Text('Nom du flow')),
        DataColumn(label: Text('État des Flow Runs')),
        DataColumn(label: Text('LastModificationBy')),
        DataColumn(label: Text('LastModificationDate')),
      ],
      rows: [
        DataRow(cells: [
          DataCell(Text(flow.flowId)),
          DataCell(
            asyncFlowRuns.when(
              data: (flowRuns) {
                return Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: List.generate(10, (index) {
                    if (index < flowRuns.length) {
                      return Container(
                        width: 40,
                        height: 40,
                        child: Center(
                          child: Container(
                            width: 20,
                            height: 20,
                            decoration: BoxDecoration(
                              shape: BoxShape.circle,
                              color: getStatusColor(flowRuns[index]),
                            ),
                          ),
                        ),
                      );
                    } else {
                      return Container(
                        width: 40,
                        height: 40,
                        child: Center(
                          child: Container(
                            width: 20,
                            height: 20,
                            decoration: BoxDecoration(
                              shape: BoxShape.circle,
                              color: Colors.grey,
                            ),
                          ),
                        ),
                      );
                    }
                  }),
                );
              },
              loading: () => Text("Loading..."),
              error: (error, stack) => Text("Error"),
            ),
          ),
          DataCell(Text("lastModificationBy")),
          DataCell(Text("LastModificationDate")),
        ]),
      ],
    );


  }

  Color getStatusColor(FlowRunView flowRun) {
    switch (flowRun.status) {
      case OrchestrableStatus.NEW:
      case OrchestrableStatus.PENDING_START:
        return Colors.yellow;

      case OrchestrableStatus.RUNNING:
        return Colors.blue;

      case OrchestrableStatus.PAUSED:
        return Colors.orangeAccent;
      case OrchestrableStatus.CANCELLED:
        return Colors.pinkAccent;

      case OrchestrableStatus.FAILED:
        return Colors.red;

      case OrchestrableStatus.SUCCESS:
        return Colors.green;
      case OrchestrableStatus.UNKNOWN:
        return Colors.grey;
    }
  }
}


