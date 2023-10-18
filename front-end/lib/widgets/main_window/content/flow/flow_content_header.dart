import 'package:flutter/material.dart';

class FlowContentHeader extends StatelessWidget {
  const FlowContentHeader({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 36,
      child: Container(
        decoration: BoxDecoration(
          color: const Color(0xff429579),
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
