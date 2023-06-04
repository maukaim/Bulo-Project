import 'package:bulo_ui/domains/flow_runs/model/technical_stage_run_status.dart';

import 'context_stage_id.dart';
import 'stage_type.dart';

class TechnicalStageRun {
  final String stageRunId;
  final StageType stageType;
  final ContextStageId contextualizedStageId;
  final TechnicalStageRunStatus status;
  final DateTime? startTime;
  final DateTime? endTime;

  TechnicalStageRun(
      {required this.stageRunId,
      required this.stageType,
      required this.contextualizedStageId,
      required this.status,
      required this.startTime,
      required this.endTime});

  factory TechnicalStageRun.fromJson(Map<String, dynamic> json) {
    return TechnicalStageRun(
      stageRunId: json['stageRunId'],
      stageType: getStageTypeFromString(json['stageType']),
      contextualizedStageId:
          ContextStageId.fromJson(json['contextualizedStageId']),
      status: getTsrStatusFromString(json['status']),
      startTime:
          json['startTime'] == null ? null : DateTime.parse(json['startTime']),
      endTime: json['endTime'] == null ? null : DateTime.parse(json['endTime']),
    );
  }
}
