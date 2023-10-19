import 'package:bulo_ui/domains/flow_runs/model/flow_run.dart';
import 'package:bulo_ui/domains/flow_runs/model/orchestrable_status.dart';
import 'package:bulo_ui/widgets/main_window/content/flow/runs/items/shared/run_context_item.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class FlowRunItem extends StatefulWidget {
  final FlowRunView flowRunView;

  const FlowRunItem({super.key, required this.flowRunView});

  @override
  // ignore: library_private_types_in_public_api
  _FlowRunItemState createState() => _FlowRunItemState();
}

class _FlowRunItemState extends RunContextItemState<FlowRunItem> {
  @override
  OrchestrableStatus getStatus() {
    return widget.flowRunView.status;
  }

  @override
  String getTitleText() {
    return widget.flowRunView.flowRunId;
  }

  @override
  Duration? getDuration() {
    var endTime =  widget.flowRunView.endTime;
    var startTime =  widget.flowRunView.startTime;
    return endTime != null && startTime!=null ? endTime.difference(startTime): null;
  }

  @override
  DateTime? getEndTime() {
    return widget.flowRunView.endTime;
  }

  @override
  List getStageRuns() {
    return widget.flowRunView.stageRuns;
  }
}
