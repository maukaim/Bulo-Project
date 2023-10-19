import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/main_window/content/flow/flow_content_area.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/nav_menu_type.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';


class MainArea extends ConsumerWidget {
  final BorderRadius boxRadius = const BorderRadius.all(Radius.circular(8));
  const MainArea({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    NavMenuType? tabMenuType = ref.watch(selectedTabMenuProvider(getCurrentServerConnector(ref)));
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 10, 10, 10),
      child: Container(
        decoration: BoxDecoration(
          boxShadow: const [
            BoxShadow(
              offset: Offset(-4, -4),
              spreadRadius: -3,
              blurRadius: 3,
              color: Color.fromRGBO(0, 0, 0, 0.10),
            ),
            BoxShadow(
              offset: Offset(4, 4),
              spreadRadius: -3,
              blurRadius: 3,
              color: Color.fromRGBO(0, 0, 0, 0.10),
            ),
            BoxShadow(
              offset: Offset(-4, 4),
              spreadRadius: -3,
              blurRadius: 3,
              color: Color.fromRGBO(0, 0, 0, 0.10),
            ),
            BoxShadow(
              offset: Offset(4, -4),
              spreadRadius: -3,
              blurRadius: 3,
              color: Color.fromRGBO(0, 0, 0, 0.10),
            ),
          ],
          color: Colors.white,
          borderRadius: boxRadius,
        ),
        child: ClipRRect(
          borderRadius: boxRadius,
          child: (() {
            switch (tabMenuType) {
              case NavMenuType.flowMenu:
                return const FlowContentArea();
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
    );
  }
}
