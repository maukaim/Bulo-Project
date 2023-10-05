import 'package:bulo_ui/widgets/main_window/content/flow/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../nav/nav_content/flows/providers.dart';
import 'runs/flow_runs_area.dart';

class FlowContentArea extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var isOnRunMode = ref.watch(isOnRunModeProvider);
    var selectedFlowId = ref.watch(getSelectedFlowProvider(ref));
    return selectedFlowId == null
        ? getDefaultScreen()
        : isOnRunMode
            ? FlowRunsArea()
            : Container(color: Colors.blueGrey);
  }

  Widget getDefaultScreen() {
    return  const Column(
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
              color: Colors.blueGrey, fontSize: 16, fontWeight: FontWeight.bold),
        ),
      ],
    );
  }
}
