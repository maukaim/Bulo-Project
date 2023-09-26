import 'package:bulo_ui/widgets/global/technical_stage_run_status_badge.dart';
import 'package:bulo_ui/widgets/main_window/content/flow/runs/items/shared/item_head_line.dart';
import 'package:flutter/cupertino.dart';

import '../../../../../../domains/flow_runs/model/technical_stage_run.dart';

class TechnicalStageRunItem extends StatelessWidget {
  final TechnicalStageRun run;

  const TechnicalStageRunItem({super.key, required this.run});

  @override
  Widget build(BuildContext context) {
    return ItemHeadLine(
      titleText:
          "${run.contextualizedStageId.stageId}[Marker:${run.contextualizedStageId.marker}]",
      statusBadge: TechnicalStageRunStatusBadge(status: run.status),
      duration: run.endTime != null && run.startTime != null
          ? run.endTime!.difference(run.startTime!)
          : null,
      endTime: run.endTime,
    );
  }
}
