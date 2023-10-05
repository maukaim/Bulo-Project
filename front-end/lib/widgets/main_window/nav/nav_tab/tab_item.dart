import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'nav_menu_type.dart';

class TabItem extends ConsumerWidget {
  final IconData icon;
  final NavMenuType menuType;

  TabItem({super.key, required this.menuType, required this.icon});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    NavMenuType? tabMenuSelected = ref.watch(selectedTabMenuProvider(getCurrentServerConnector(ref)));
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: InkWell(
        borderRadius: BorderRadius.circular(4),
        hoverColor: Colors.blueGrey.withOpacity(0.3),
        onTap: () {
          ref.watch(selectedTabMenuProvider(getCurrentServerConnector(ref)).notifier).state = menuType;
        },
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 4, horizontal: 4),
          child: Icon(
              size: 18,
              icon,
              color:
                  tabMenuSelected == menuType ? Colors.cyan : Colors.black54),
        ),
      ),
      //
    );
    // First icon
  }
}
