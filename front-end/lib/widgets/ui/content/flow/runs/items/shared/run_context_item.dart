import 'package:bulo_ui/domains/flow_runs/model/functional_stage_run.dart';
import 'package:bulo_ui/domains/flow_runs/model/technical_stage_run.dart';
import 'package:bulo_ui/widgets/ui/content/flow/runs/items/functional_stage_run_item.dart';
import 'package:flutter/material.dart';

import '../../../../../../../domains/flow_runs/model/orchestrable_status.dart';
import '../../../../../../global/orchestrable_status_badge.dart';
import '../technical_stage_run_item.dart';
import 'item_head_line.dart';

abstract class RunContextItemState<DD extends StatefulWidget>
    extends State<DD> {
  bool isExpanded = false;

  @override
  Widget build(BuildContext context) {
    var stageRuns = getStageRuns();
    return ExpansionPanelList(
      expansionCallback: (int index, bool isExpanded) {
        setState(() {
          this.isExpanded = !isExpanded;
        });
      },
      children: [
        ExpansionPanel(
          headerBuilder: (BuildContext context, bool isExpanded) {
            return ItemHeadLine(
              titleText: getTitleText(),
              statusBadge: OrchestrableStatusBadge(status: getStatus()),
              duration: getDuration(),
              endTime: getEndTime(),
            );
          },
          body: ListView.builder(
              shrinkWrap: true,
              itemCount: stageRuns.length,
              itemBuilder: (ctx, index) => Padding(
                    padding: const EdgeInsets.fromLTRB(18, 8, 0, 8),
                    child: getSubStageWidget(stageRuns[index]),
                  )),
          isExpanded: isExpanded,
        ),
      ],
    );
  }

  String getTitleText();

  OrchestrableStatus getStatus();

  Duration? getDuration();

  DateTime? getEndTime();

  List<dynamic> getStageRuns();

  Widget getSubStageWidget(dynamic stageRun) {
    if (stageRun is FunctionalStageRunView) {
      return FunctionalStageRunItem(functionalStageRunView: stageRun);
    } else if (stageRun is TechnicalStageRun) {
      return TechnicalStageRunItem(run: stageRun);
    }
    throw Exception("Unknown stage run type found: ${stageRun.runtimeType}");
  }
}
