import 'package:flutter/material.dart';
import 'package:multi_split_view/multi_split_view.dart';

class CustomDividerPainter extends DividerPainter {
  CustomDividerPainter()
      : super(
          animationEnabled: DividerPainter.defaultAnimationEnabled,
          animationDuration: DividerPainter.defaultAnimationDuration,
          backgroundColor: Colors.transparent,
          highlightedBackgroundColor: const Color.fromARGB(150, 200, 200, 200),
        );
}
