

import 'package:flutter/cupertino.dart';

class LogoArea extends StatelessWidget{
  const LogoArea({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 0, 10, 10),
      child: Center(
        child: Image.asset(
          'assets/images/bulo-logo-alpha-cyan.png',
          height: 80,
        ),
      ),
    );
  }

}