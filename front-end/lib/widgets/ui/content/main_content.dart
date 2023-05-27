import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../nav/nav_tab/nav_menu_type.dart';
import '../nav/nav_tab/providers.dart';

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
          child: (() {
            switch (tabMenuType) {
              case NavMenuType.flowMenu:
                return Container(color: Colors.red,);
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
