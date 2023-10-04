import 'package:bitsdojo_window/bitsdojo_window.dart';
import 'package:flutter/material.dart';


final buttonColors = WindowButtonColors(
    iconNormal: const Color(0xFF805306),
    mouseOver: const Color(0xFFF6A00C),
    mouseDown: const Color(0xFF805306),
    iconMouseOver: const Color(0xFF805306),
    iconMouseDown: const Color(0xFFFFD500));

final closeButtonColors = WindowButtonColors(
    mouseOver: const Color(0xFFD32F2F),
    mouseDown: const Color(0xFFB71C1C),
    iconNormal: const Color(0xFF805306),
    iconMouseOver: Colors.white);


class WindowButtons extends StatelessWidget {
  const WindowButtons({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
        children:[
      CloseWindowButton(colors: closeButtonColors),
      MinimizeWindowButton(colors: buttonColors),
      MaximizeWindowButton(colors: buttonColors),
    ]);
  }
}