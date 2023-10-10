import 'package:bulo_ui/domains/flow_runs/model/flow_run.dart';
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

    return Padding(
      padding: EdgeInsets.symmetric(vertical: 8),
      child: //TODO: A la palce du Container(Text) mettre tes bails a toi
      Container(color: Colors.red,
      child: Text("Je suis un Flow! Je m'appele ${flow.flowId} ok?!")),
    );
  }
}
