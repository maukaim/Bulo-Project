import 'package:bulo_ui/widgets/main_window/nav/controls/controls_header_area.dart';
import 'package:bulo_ui/widgets/main_window/nav/logo/logo_area.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_content/nav_content_area.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/nav_tab_area.dart';
import 'package:flutter/material.dart';

class NavBar extends StatelessWidget {
  const NavBar({super.key});

  @override
  Widget build(BuildContext context) {
    return  const Padding(
      padding: EdgeInsets.fromLTRB(0, 0, 0, 12),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          ControlsHeaderArea(),
          Padding(
            padding: EdgeInsets.symmetric(vertical:8, horizontal: 0),
            child: LogoArea(),
          ),
          NavContentArea(),
          NavTabArea(),
        ],
      ),
    );
  }
}
