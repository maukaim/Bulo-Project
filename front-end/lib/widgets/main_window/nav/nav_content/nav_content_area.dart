import 'package:bulo_ui/widgets/main_window/nav/nav_content/flows/nav_flows_area.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../nav_tab/nav_menu_type.dart';

class NavContentArea extends ConsumerWidget {
  const NavContentArea({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var tabMenuType = ref.watch(selectedTabMenuProvider);
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.fromLTRB(12, 16, 12, 16),
        child: (() {
          switch (tabMenuType) {
            case NavMenuType.flowMenu:
              return NavFlowsArea();
            case NavMenuType.stageMenu:
              return Container(
                color: Colors.blue,
              );
            case NavMenuType.teamMenu:
              return Container(
                color: Colors.green,
              );
            default:
              return const Center();
          }
        }()),
      ),
    );
  }
}
