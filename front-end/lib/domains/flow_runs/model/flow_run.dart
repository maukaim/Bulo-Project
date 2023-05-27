class FlowRun {
  final String flowRunId;
  final String flowId;

  FlowRun({required this.flowRunId, required this.flowId});

  factory FlowRun.fromJson(Map<String, dynamic> json) {
    return FlowRun(
      flowRunId: json['flowRunId'],
      flowId: json['flowId'],
    );
  }
}
