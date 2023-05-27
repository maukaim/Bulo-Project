
import 'package:bulo_ui/widgets/ui/nav/logo/logo_area.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_content/nav_content_area.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_tab/nav_tab_area.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class NavBar extends StatelessWidget{
  const NavBar({super.key});

  @override
  Widget build(BuildContext context) {
    return const Padding(
      padding: EdgeInsets.symmetric(vertical: 12, horizontal: 0),
      child: Column(
        children: [
          LogoArea(),
          NavContentArea(),
          NavTabArea(),
        ],
      ),
    );
  }
}