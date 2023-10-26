<<<<<<< HEAD
import 'package:bulo_ui/widgets/main_window/content/flow/dashborad/flows_dashboard.dart';
=======
import 'package:bulo_ui/widgets/main_window/content/flow/flow_content_header.dart';
>>>>>>> 174742a00275b6497da1cb35d99296dfef51a1ef
import 'package:bulo_ui/widgets/main_window/content/flow/providers.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_content/flows/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';


import 'runs/flow_runs_area.dart';

class FlowContentArea extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var isOnRunMode = ref.watch(isOnRunModeProvider);
    var selectedFlowId = ref.watch(getSelectedFlowProvider(ref));
<<<<<<< HEAD
    return selectedFlowId == null
        ? getDashboard()
        : isOnRunMode
            ? FlowRunsArea()
            : Container(color: Colors.blueGrey);
=======
    return Column(
      mainAxisSize: MainAxisSize.max, // Column takes maximum height
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        FlowContentHeader(),
        Flexible(
          child: selectedFlowId == null
              ? getDefaultScreen()
              : isOnRunMode
                  ? FlowRunsArea()
                  : Container(color: Colors.blueGrey),
        ),
      ],
    );
>>>>>>> 174742a00275b6497da1cb35d99296dfef51a1ef
  }


  Widget getDashboard(){
    print("hihion montre dashboard");
    return Column(
      children: [
        FlowsDashboard(),
      ],
    );
  }

  Widget getDefaultScreen() {
    return const Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Icon(
          size: 24,
          CupertinoIcons.archivebox_fill,
          color: Colors.blueGrey,
        ),
        Text(
          "Select a flow to see its content.",
          style: TextStyle(
              color: Colors.blueGrey,
              fontSize: 16,
              fontWeight: FontWeight.bold),
        ),
      ],
    );
  }
}
