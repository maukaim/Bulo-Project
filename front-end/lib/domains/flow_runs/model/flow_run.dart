import 'package:bulo_ui/domains/flow_runs/model/technical_stage_run.dart';

import 'functional_stage_run.dart';
import 'orchestrable_status.dart';

class FlowRunView {
  final String flowRunId;
  final String flowId;
  final OrchestrableStatus status;
  final List<dynamic> stageRuns;
  final DateTime? startTime;
  final DateTime? endTime;

  FlowRunView(
      {required this.flowRunId,
      required this.flowId,
      required this.status,
      required this.stageRuns,
      required this.startTime,
      required this.endTime});

  factory FlowRunView.fromJson(Map<String, dynamic> json) {
    return FlowRunView(
      flowRunId: json['runId'],
      flowId: json['sourceId'],
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
