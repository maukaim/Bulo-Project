import 'package:bulo_ui/domains/flow_runs/model/context_stage_id.dart';
import 'package:bulo_ui/domains/flow_runs/model/technical_stage_run.dart';

import 'orchestrable_status.dart';

class FunctionalStageRunView {
  final String flowRunId;
  final ContextStageId contextStageId;
  final OrchestrableStatus status;
  final List<dynamic> stageRuns;
  final DateTime? startTime;
  final DateTime? endTime;

  FunctionalStageRunView(
      {required this.flowRunId,
      required this.contextStageId,
      required this.status,
      required this.stageRuns,
      required this.startTime,
      required this.endTime});

  factory FunctionalStageRunView.fromJson(Map<String, dynamic> json) {
    return FunctionalStageRunView(
      flowRunId: json['runId'],
      contextStageId: ContextStageId.fromJson(json['sourceId']),
      status: getOrchestrableStatusFromString(json['status']),
      stageRuns: [for (var x in json['stageRuns']) buildStageRunFromJson(x)],
      startTime:
          json['startTime'] == null ? null : DateTime.parse(json['startTime']),
      endTime: json['endTime'] == null ? null : DateTime.parse(json['endTime']),
    );
  }

  static dynamic buildStageRunFromJson(Map<String, dynamic> json) {
    if (json['stageRuns'] != null) {
      return FunctionalStageRunView.fromJson(json);
    } else {
      return TechnicalStageRun.fromJson(json);
    }
  }
}
