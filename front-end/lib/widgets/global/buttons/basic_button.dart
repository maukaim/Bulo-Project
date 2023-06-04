import 'package:flutter/material.dart';

class CustomButton extends StatefulWidget {
  final Widget child;
  final VoidCallback onPressed;
  final Color color;
  final Color? hoverColor;
  final Color? pressedColor;

  const CustomButton(
      {super.key,
      this.color = const Color(0x00FFFFFF),
      this.hoverColor,
      this.pressedColor,
      required this.onPressed,
      required this.child});

  @override
  _CustomButtonState createState() => _CustomButtonState();
}

class _CustomButtonState extends State<CustomButton> {
  bool _isHovering = false;
  bool _isPressing = false;

  @override
  Widget build(BuildContext context) {
    return MouseRegion(
      onEnter: (_) => setState(() => _isHovering = true),
      onExit: (_) => setState(() => _isHovering = false),
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
        onTapDown: (_) => setState(() => _isPressing = true),
        onTapUp: (_) => setState(() => _isPressing = false),
        onTapCancel: () => setState(() => _isPressing = false),
        onTap: widget.onPressed,
        child: Container(
          decoration: BoxDecoration(
            color: _isPressing
                ? widget.pressedColor?? widget.color
                : (_isHovering ? widget.hoverColor?? widget.color : widget.color),
            borderRadius: BorderRadius.circular(8),
          ),
          padding: EdgeInsets.symmetric(horizontal: 16, vertical: 10),
          child: widget.child,
        ),
      ),
    );
  }
}
