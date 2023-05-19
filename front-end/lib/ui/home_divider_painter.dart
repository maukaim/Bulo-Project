import 'package:flutter/material.dart';
import 'package:multi_split_view/multi_split_view.dart';

class HomeDividerPainter extends DividerPainter {
  HomeDividerPainter()
      : super(
          animationEnabled: DividerPainter.defaultAnimationEnabled,
          animationDuration: DividerPainter.defaultAnimationDuration,
          backgroundColor: Colors.transparent,
          highlightedBackgroundColor: const Color.fromARGB(50, 50, 50, 50),
        );

  @override
  void paint(
      {required Axis dividerAxis,
      required bool resizable,
      required bool highlighted,
      required Canvas canvas,
      required Size dividerSize,
      required Map<int, dynamic> animatedValues}) {
    final paint = Paint()
      ..color =
          highlighted ? const Color.fromARGB(50, 0, 0, 0) : Colors.transparent
      ..style = PaintingStyle.fill;

    final height = dividerSize.height;
    final dividerHeight = height * 0.96; // 90% of total height
    // final topOffset = (height - dividerHeight) / 2; // centered vertically

    final rect =
        Rect.fromLTWH(0, height * 0.02, dividerSize.width, dividerHeight);
    final rrect = RRect.fromRectAndRadius(
        rect, const Radius.circular(8)); // 10 is the radius of the corners

    canvas.drawRRect(rrect, paint);

    // canvas.drawRect(rect, paint);
  }
}
