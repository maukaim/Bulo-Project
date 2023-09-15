import 'dart:ui';

import 'package:flutter/material.dart' hide BoxDecoration, BoxShadow, Widget;
import 'package:flutter/widgets.dart' hide BoxDecoration, BoxShadow;
import 'package:flutter_inset_box_shadow/flutter_inset_box_shadow.dart';

extension WidgetExtension on Widget {
  Container neumorphicPressed({BorderRadius borderRadius = BorderRadius.zero}) {
    return Container(
      decoration: BoxDecoration(boxShadow: const [
        BoxShadow(
            color: Colors.black38, //Color(0xFFDFDFDF),
            offset: Offset(2, 2),
            blurRadius: 3,
            // spreadRadius: -5.0,
            inset: true),
        BoxShadow(
            color: Colors.black26, //Color(0xFFDFDFDF),
            offset: Offset(-2, -2),
            blurRadius: 3,
            // spreadRadius: -5.0,
            inset: true),
      ], borderRadius: borderRadius),
      child: this,
    );
  }

  ClipRRect glassmorphic({BorderRadius borderRadius = BorderRadius.zero}) {
    return ClipRRect(
      borderRadius: borderRadius,
      child: BackdropFilter(
        filter: ImageFilter.blur(sigmaX: 6.0, sigmaY: 6.0),
        child: Container(
          decoration: BoxDecoration(
            border: Border.all(
              color: Colors.white,
            ),
            boxShadow: [
              BoxShadow(
                color: Colors.white.withOpacity(0.6),
                blurRadius: 20.0,
                offset: Offset(0, 10),
              ),
            ],
            color: Colors.white.withOpacity(0.5),
          ),
          child: this,
        ),
      ),
    );
  }
}
