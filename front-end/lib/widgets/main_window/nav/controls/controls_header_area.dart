import 'package:bulo_ui/core/util/current_system.dart';
import 'package:bulo_ui/widgets/main_window/nav/controls/macos/macos_nav_controls_widget.dart';
import 'package:bulo_ui/widgets/main_window/nav/controls/web/web_nav_controls_widget.dart';
import 'package:bulo_ui/widgets/main_window/nav/controls/windows/windows_nav_controls_widget.dart';
import 'package:flutter/material.dart';

class ControlsHeaderArea extends StatelessWidget {
  const ControlsHeaderArea({super.key});

  @override
  Widget build(BuildContext context) {
    return getPlatformControlsHeader();
  }

  getPlatformControlsHeader() {
    switch (currentSystem) {
      case System.web:
        return WebNavControlsWidget();
      case System.macOS:
        return MacOSNavControlsWidget();
      case System.windows:
        return WindowsNavControlsWidget();
      default:
        return Row(
          children: [
            Flexible(
              child: const Text("Platform not Officially supported.", overflow: TextOverflow.ellipsis,),
            ),
          ],
        );
    }
  }
}
