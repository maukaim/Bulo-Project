import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LogoArea extends StatelessWidget {
  const LogoArea({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 0, 10, 10),
      child: Center(
        child: Image.asset(
          'assets/images/bulo-logo-alpha-FULL-NAME.png',
          height: 40,
          // color: Colors.grey // For when The current server is not reachable
        ),
      ),
    );
  }
}
