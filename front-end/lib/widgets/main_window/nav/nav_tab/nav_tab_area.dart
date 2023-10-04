import 'package:bulo_ui/widgets/main_window/nav/nav_tab/tab_item.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'nav_menu_type.dart';

class NavTabArea extends StatelessWidget {
  const NavTabArea({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center, // Center align
      children: <Widget>[
        TabItem(menuType: NavMenuType.flowMenu, icon: CupertinoIcons.arrow_2_squarepath),
        TabItem(menuType: NavMenuType.stageMenu, icon: CupertinoIcons.app_badge_fill),
        TabItem(menuType: NavMenuType.teamMenu, icon: CupertinoIcons.person_3_fill),
        // Third icon
      ],
    );
  }
}
