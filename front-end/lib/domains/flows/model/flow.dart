import 'flow_stage.dart';
import 'owner_keys.dart';

class Flow {
  final String flowId;
  final List<OwnerKeys> ownerKeys;
  final List<FlowStage> flowStages;
  final bool parallelRunAllowed;

  Flow(
      {required this.flowId, required this.ownerKeys, required this.flowStages, required this.parallelRunAllowed});

  factory Flow.fromJson(Map<String, dynamic> json) {
    return Flow(
        flowId: json['flowId'],
        ownerKeys: [for (var x in json['ownerKeys']) OwnerKeys.fromJson(x)],
        flowStages: [for (var x in json['flowStages']) FlowStage.fromJson(x)],
        parallelRunAllowed: json['parallelRunAllowed']
    );
  }
}
