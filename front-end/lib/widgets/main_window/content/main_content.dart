import 'package:bulo_ui/widgets/main_window/content/flow/flow_content_area.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/nav_menu_type.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'flow/runs/flow_runs_area.dart';

class MainArea extends ConsumerWidget {
  const MainArea({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    NavMenuType? tabMenuType = ref.watch(selectedTabMenuProvider);
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 10, 10, 10),
      child: Container(
        decoration: const BoxDecoration(
          boxShadow: [
            BoxShadow(
              offset: Offset(-4, -4),
              spreadRadius: -3,
              blurRadius: 4,
              color: Color.fromRGBO(0, 0, 0, 0.15),
            ),
            BoxShadow(
              offset: Offset(4, 4),
              spreadRadius: -3,
              blurRadius: 4,
              color: Color.fromRGBO(0, 0, 0, 0.15),
            ),
            BoxShadow(
              offset: Offset(-4, 4),
              spreadRadius: -3,
              blurRadius: 4,
              color: Color.fromRGBO(0, 0, 0, 0.15),
            ),
            BoxShadow(
              offset: Offset(4, -4),
              spreadRadius: -3,
              blurRadius: 4,
              color: Color.fromRGBO(0, 0, 0, 0.15),
            ),
          ],
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(8)),
        ),
        child: Padding(
          padding: const EdgeInsets.all(12),
          child: ClipRect(
            child: (() {
              switch (tabMenuType) {
                case NavMenuType.flowMenu:
                  return FlowContentArea();
                case NavMenuType.stageMenu:
                  return Container(color: Colors.blue,);
                case NavMenuType.teamMenu:
                  return Container(color: Colors.green,);
                default:
                  return const Center(child: Text("Welcome ! Choose a menu."),);
              }
            }()),
          ),
        ),
      ),
    );
  }
}
