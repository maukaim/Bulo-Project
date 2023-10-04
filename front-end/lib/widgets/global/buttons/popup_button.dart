import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/widgets/global/buttons/basic_button.dart';
import 'package:flutter/material.dart';

class CustomPopupButton<T> extends StatelessWidget {
  final List<T> choices;
  final PopupMenuItemVisualBuilder<T> itemBuilder;
  final String? tooltip;
  final Widget child;
  final PopupMenuItemSelected<T>? onSelected;

  const CustomPopupButton(
      {super.key,
      required this.choices,
      required this.itemBuilder,
      this.tooltip,
      required this.child,
      this.onSelected});

  @override
  Widget build(BuildContext context) {
    return PopupMenuButton<T>(
      offset: Offset(0,8),
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      color: Color(0xFF2B2D30),
      tooltip: tooltip,
      surfaceTintColor: Colors.transparent,
      onSelected: onSelected,
      position: PopupMenuPosition.under,
      itemBuilder: (BuildContext context) {
        return choices.map((T choice) {
          return _CustomPopupMenuItem(
            choice,
            child: itemBuilder(choice),
          );
        }).toList();
      },
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.vertical(bottom: Radius.circular(12)),
        ),
        child: child,
      ),
    );
  }
}

typedef PopupMenuItemVisualBuilder<T> = Widget Function(T value);

class _CustomPopupMenuItem<T> extends PopupMenuEntry<T> {
  final Widget child;
  final T actualValue;

  _CustomPopupMenuItem(this.actualValue, {required this.child});

  @override
  double get height => 50;

  @override
  State<StatefulWidget> createState() {
    return _CustomPopupMenuItemState();
  }

  @override
  bool represents(T? other) {
    return actualValue == other;
  }
}

class _CustomPopupMenuItemState<T> extends State<_CustomPopupMenuItem<T>> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: CustomButton(
        padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
        hoverColor: Colors.white10.withOpacity(0.2),
        onPressed: handleTap,
        child: widget.child,
      ),
    );
  }

  @protected
  void handleTap() {
    Navigator.pop<T>(context, widget.actualValue);
  }
}
