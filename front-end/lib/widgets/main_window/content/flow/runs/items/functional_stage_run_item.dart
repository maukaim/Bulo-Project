import 'package:bulo_ui/domains/flow_runs/model/functional_stage_run.dart';
import 'package:bulo_ui/domains/flow_runs/model/orchestrable_status.dart';
import 'package:bulo_ui/widgets/main_window/content/flow/runs/items/shared/run_context_item.dart';
import 'package:flutter/widgets.dart';

class FunctionalStageRunItem extends StatefulWidget {
  final FunctionalStageRunView functionalStageRunView;

  const FunctionalStageRunItem(
      {super.key, required this.functionalStageRunView});

  @override
  // ignore: library_private_types_in_public_api
  _FunctionalStageRunItemState createState() => _FunctionalStageRunItemState();
}

class _FunctionalStageRunItemState
    extends RunContextItemState<FunctionalStageRunItem> {
  @override
  Duration? getDuration() {
    var endTime =  widget.functionalStageRunView.endTime;
    var startTime =  widget.functionalStageRunView.startTime;
    return endTime != null && startTime!=null ? endTime.difference(startTime): null;
  }

  @override
  DateTime? getEndTime() {
    return widget.functionalStageRunView.endTime;
  }

  @override
  List getStageRuns() {
    return widget.functionalStageRunView.stageRuns;
  }

  @override
  OrchestrableStatus getStatus() {
    return widget.functionalStageRunView.status;
  }

  @override
  String getTitleText() {
    return "${widget.functionalStageRunView.contextStageId.stageId}[Marker:${widget.functionalStageRunView.contextStageId.marker}]";
  }
}
