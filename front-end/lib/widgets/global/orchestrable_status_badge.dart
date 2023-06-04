import 'package:bulo_ui/core/util/extensions/string_extension.dart';
import 'package:bulo_ui/domains/flow_runs/model/orchestrable_status.dart';
import 'package:flutter/material.dart';

class OrchestrableStatusBadge extends StatelessWidget {
  final OrchestrableStatus status;

  const OrchestrableStatusBadge({super.key, required this.status});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      decoration: BoxDecoration(
        color: getBgColor().withOpacity(0.6),
        borderRadius: BorderRadius.circular(8),
      ),
      child: Padding(
        padding: EdgeInsets.all(8),
        child: Text(getBadgeText()),
      ),
    );
  }

  Color getBgColor() {
    switch (status) {
      case OrchestrableStatus.NEW:
      case OrchestrableStatus.PENDING_START:
        return Colors.yellow;
      case OrchestrableStatus.RUNNING:
        return Colors.blue;
      case OrchestrableStatus.PAUSED:
        return Colors.orangeAccent;
      case OrchestrableStatus.CANCELLED:
        return Colors.pinkAccent;
      case OrchestrableStatus.FAILED:
        return Colors.red;
      case OrchestrableStatus.SUCCESS:
        return Colors.green;
      case OrchestrableStatus.UNKNOWN:
        return Colors.grey;
    }
  }

  String getBadgeText() {
    return status.name.replaceAll('_', " ").capitalize();
  }
}
