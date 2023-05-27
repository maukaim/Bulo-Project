
import 'flow_stage_id.dart';

class InputProvider{
  final FlowStageId flowStageId;
  final List<String> outputIds;

  InputProvider({required this.flowStageId, required this.outputIds});

  factory InputProvider.fromJson(Map<String, dynamic> json) {
    return InputProvider(
      flowStageId: FlowStageId.fromJson(json['flowStageId']),
      outputIds: [for (var x in json['outputIds']) x],
    );
  }
}