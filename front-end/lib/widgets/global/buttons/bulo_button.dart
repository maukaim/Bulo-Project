import 'package:flutter/material.dart';

class BuloButton extends StatefulWidget {
  final Widget? child;
  final VoidCallback? onPressed;
  final Color color;
  final Color? hoverColor;
  final Color? pressedColor;
  final Color disableColor;
  final EdgeInsets? padding;
  final BorderRadius? borderRadius;
  final bool isEnabled;

  const BuloButton(
      {super.key,
      this.color = const Color(0x00FFFFFF),
      this.isEnabled = true,
      this.disableColor = const Color(0x99FAFAFA),
      this.padding = const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
      this.borderRadius = const BorderRadius.all(Radius.circular(8)),
      this.hoverColor = const Color(0x34607D8B),
      this.pressedColor,
      required this.onPressed,
      required this.child});

  @override
  _BuloButtonState createState() => _BuloButtonState();
}

class _BuloButtonState extends State<BuloButton> {
  bool _isHovering = false;
  bool _isPressing = false;

  @override
  Widget build(BuildContext context) {
    return MouseRegion(
      onEnter:
          widget.isEnabled ? (_) => setState(() => _isHovering = true) : null,
      onExit:
          widget.isEnabled ? (_) => setState(() => _isHovering = false) : null,
      cursor: widget.isEnabled
          ? SystemMouseCursors.click
          : SystemMouseCursors.basic,
      child: GestureDetector(
        onTapDown:
            widget.isEnabled ? (_) => setState(() => _isPressing = true) : null,
        onTapUp: widget.isEnabled
            ? (_) => setState(() => _isPressing = false)
            : null,
        onTapCancel:
            widget.isEnabled ? () => setState(() => _isPressing = false) : null,
        onTap: widget.isEnabled ? widget.onPressed : null,
        child: Container(
          decoration: BoxDecoration(
            color: !widget.isEnabled
                ? widget.disableColor
                : (_isPressing
                    ? widget.pressedColor ?? widget.color
                    : (_isHovering
                        ? widget.hoverColor ?? widget.color
                        : widget.color)),
            borderRadius: widget.borderRadius,
          ),
          padding: widget.padding,
          child: widget.child,
        ),
      ),
    );
  }
}
