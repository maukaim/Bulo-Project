import 'package:bulo_ui/widgets/main_window/nav/controls/shared/abstract_nav_controls_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class WindowsNavControlsWidget extends AbstractNavControlsWidget {
  const WindowsNavControlsWidget({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    //TODO-ANTOINE: Here put necessary logic if title-bar visually stack with this controls bar.
    return getSharedNavWidget();
  }
}
