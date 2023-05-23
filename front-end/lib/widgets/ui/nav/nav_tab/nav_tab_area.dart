import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class NavTabArea extends StatelessWidget {
  const NavTabArea({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Row(
      mainAxisAlignment: MainAxisAlignment.center, // Center align
      children: <Widget>[
        Icon(CupertinoIcons.arrow_2_squarepath, color: Colors.cyan),
        // First icon
        SizedBox(width: 16),
        // Gap of 16
        Icon(CupertinoIcons.app_badge_fill, color: Colors.blueGrey),
        // Second icon
        SizedBox(width: 16),
        // Gap of 16
        Icon(CupertinoIcons.person_3_fill, color: Colors.blueGrey),
        // Third icon
      ],
    );
  }
}
