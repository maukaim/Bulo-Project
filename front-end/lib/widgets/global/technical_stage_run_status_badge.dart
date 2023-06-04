import 'package:bulo_ui/core/util/extensions/string_extension.dart';
import 'package:bulo_ui/domains/flow_runs/model/technical_stage_run_status.dart';
import 'package:flutter/material.dart';

class TechnicalStageRunStatusBadge extends StatelessWidget {
  final TechnicalStageRunStatus status;

  const TechnicalStageRunStatusBadge({super.key, required this.status});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      decoration: BoxDecoration(
        color: getBgColor().withOpacity(0.6),
        borderRadius: BorderRadius.circular(8),
      ),
      child: Padding(
        padding: EdgeInsets.all(8),
        child: Text(getBadgeText()),
      ),
    );
  }

  Color getBgColor() {
    switch (status) {
      // TO_BE_REQUESTED,
      // REQUESTED,
      // ACKNOWLEDGED,
      // RUNNING,
      // TO_BE_CANCELLED,
      // CANCELLED,
      // FAILED,
      // SUCCESS
      case TechnicalStageRunStatus.TO_BE_REQUESTED:
      case TechnicalStageRunStatus.REQUESTED:
        return Colors.yellow;
      case TechnicalStageRunStatus.ACKNOWLEDGED:
        return Colors.lightBlueAccent;
      case TechnicalStageRunStatus.RUNNING:
        return Colors.blue;
      case TechnicalStageRunStatus.TO_BE_CANCELLED:
        return Colors.pinkAccent;
      case TechnicalStageRunStatus.CANCELLED:
        return Colors.pinkAccent;
      case TechnicalStageRunStatus.FAILED:
        return Colors.red;
      case TechnicalStageRunStatus.SUCCESS:
        return Colors.green;
    }
  }

  String getBadgeText() {
    return status.name.replaceAll('_', " ").capitalize();
  }
}
