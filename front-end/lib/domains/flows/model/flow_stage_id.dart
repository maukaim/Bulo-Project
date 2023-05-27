
class FlowStageId{
  final String stageId;
  final int marker;

  FlowStageId({required this.stageId, required this.marker});

  factory FlowStageId.fromJson(Map<String, dynamic> json) {
    return FlowStageId(
      stageId: json['stageId'],
      marker: json['marker']
    );
  }
}