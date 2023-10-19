import 'package:bulo_ui/widgets/global/buttons/bulo_button.dart';
import 'package:flutter/material.dart';

class BuloPopupMenu<T> extends StatelessWidget {
  final List<T> choices;
  final BuloPopupMenuItemBuilder<T> itemBuilder;
  final String? tooltip;
  final Widget button;
  final PopupMenuItemSelected<T>? onSelected;
  final BorderRadius? borderRadius;

  static const Color popupBackgroundColor = Color(0xFF2B2D30);
  static const Offset popupOffset = Offset(0, 8);
  static ShapeBorder defaultPopupShare =
      RoundedRectangleBorder(borderRadius: BorderRadius.circular(16));
  static const PopupMenuPosition defaultPopupPosition = PopupMenuPosition.under;

  const BuloPopupMenu(
      {super.key,
      required this.choices,
      required this.itemBuilder,
      this.tooltip,
      required this.button,
      this.onSelected,
      this.borderRadius});

  @override
  Widget build(BuildContext context) {
    return _BuloPopupMenuButton<T>(
      borderRadius: borderRadius,
      offset: popupOffset,
      shape: defaultPopupShare,
      color: popupBackgroundColor,
      tooltip: tooltip,
      surfaceTintColor: Colors.transparent,
      onSelected: onSelected,
      position: defaultPopupPosition,
      itemBuilder: (BuildContext context) {
        return choices.map((T choice) {
          return _BuloPopupMenuItem(
            choice,
            child: itemBuilder(choice),
          );
        }).toList();
      },
      child: button,
    );
  }
}

class _BuloPopupMenuButton<T> extends PopupMenuButton<T> {
  final BorderRadius? borderRadius;

  const _BuloPopupMenuButton(
      {super.key,
      required super.itemBuilder,
      super.onSelected,
      super.tooltip,
      super.surfaceTintColor,
      super.child,
      super.offset = Offset.zero,
      super.enabled = true,
      super.shape,
      super.color,
      super.position,
      this.borderRadius});

  @override
  PopupMenuButtonState<T> createState() => BuloPopupMenuButtonState<T>();
}

class BuloPopupMenuButtonState<T> extends PopupMenuButtonState<T> {
  @override
  Widget build(BuildContext context) {
    var buloWidget = widget as _BuloPopupMenuButton<T>;
    return Tooltip(
      message: buloWidget.tooltip ??
          MaterialLocalizations.of(context).showMenuTooltip,
      child: BuloButton(
        isEnabled: buloWidget.enabled,
        borderRadius: buloWidget.borderRadius,
        padding: null,
        onPressed: buloWidget.enabled ? showButtonMenu : null,
        child: buloWidget.child,
      ),
    );
  }
}

typedef BuloPopupMenuItemBuilder<T> = Widget Function(T value);

class _BuloPopupMenuItem<T> extends PopupMenuEntry<T> {
  final Widget child;
  final T actualValue;

  const _BuloPopupMenuItem(this.actualValue, {super.key, required this.child});

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

class _CustomPopupMenuItemState<T> extends State<_BuloPopupMenuItem<T>> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: BuloButton(
        padding: null,
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
