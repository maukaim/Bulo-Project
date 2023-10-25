import 'package:flutter/material.dart';

class FlowContentHeader extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 36,
      child: Container(
        decoration: BoxDecoration(
          color: Color(0xff429579),
          border: Border(
            bottom: BorderSide(
              color: Colors.grey.shade300,
              width: 1.0,
            ),
          ),
        ),
      ),
    );
  }
}
