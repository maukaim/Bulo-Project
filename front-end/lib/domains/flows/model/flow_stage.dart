import 'flow_stage_id.dart';
import 'io_dependency.dart';

class FlowStage {
  final FlowStageId flowStageId;
  final List<IoDependency> ioDependencies;

  FlowStage({required this.flowStageId, required this.ioDependencies});

  factory FlowStage.fromJson(Map<String, dynamic> json) {
    return FlowStage(
      flowStageId: FlowStageId.fromJson(json['flowStageId']),
      ioDependencies: [
        for (var x in json['ioDependencies']) IoDependency.fromJson(x)
      ],
    );
  }
}
