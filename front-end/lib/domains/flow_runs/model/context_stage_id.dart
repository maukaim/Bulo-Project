class ContextStageId {
  final String stageId;
  final int marker;

  ContextStageId({required this.stageId, required this.marker});

  factory ContextStageId.fromJson(Map<String, dynamic> json) {
    return ContextStageId(
        stageId: json['stageId'],
        marker: json['marker']
    );
  }
}