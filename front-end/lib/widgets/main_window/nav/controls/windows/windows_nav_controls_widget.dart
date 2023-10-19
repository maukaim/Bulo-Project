import 'package:bulo_ui/widgets/main_window/nav/controls/shared/abstract_nav_controls_widget.dart';
import 'package:bulo_ui/widgets/main_window/nav/controls/windows/windows_buttons.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class WindowsNavControlsWidget extends AbstractNavControlsWidget {
  const WindowsNavControlsWidget({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        const Padding(
          padding: EdgeInsets.only(right: 16.0),
          child: WindowButtons(),
        ),
        getSharedNavWidget(18),
      ],
    );
  }
}
