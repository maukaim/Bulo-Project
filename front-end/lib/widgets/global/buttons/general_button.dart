import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class CustomIconButton extends StatefulWidget {
  final Icon icon;
  final VoidCallback onTap;

  CustomIconButton({required this.icon, required this.onTap});

  @override
  _CustomIconButtonState createState() => _CustomIconButtonState();
}

class _CustomIconButtonState extends State<CustomIconButton> {
  bool _isHovering = false;
  bool _isTapping = false;

  @override
  Widget build(BuildContext context) {
    Color determineBackgroundColor() {
      if (_isTapping) {
        return Colors.white54;
      }
      if (_isHovering) {
        return Colors.black12;
      }
      return Colors.transparent;
    }

    return MouseRegion(
      onEnter: (_) => setState(() => _isHovering = true),
      onExit: (_) => setState(() => _isHovering = false),
      child: GestureDetector(
        onTapDown: (_) => setState(() => _isTapping = true),
        onTapUp: (_) => setState(() => _isTapping = false),
        onTapCancel: () => setState(() => _isTapping = false),
        onTap: widget.onTap,
        child: AnimatedContainer(
          duration: Duration(milliseconds: 200),
          padding: EdgeInsets.all(12.0),
          decoration: BoxDecoration(
            color: determineBackgroundColor(),
          ),
          child: widget.icon,
        ),
      ),
    );
  }
}