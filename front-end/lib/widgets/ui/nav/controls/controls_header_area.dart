import 'package:bulo_ui/core/util/current_system.dart';
import 'package:bulo_ui/widgets/ui/nav/controls/macos/macos_nav_controls_widget.dart';
import 'package:bulo_ui/widgets/ui/nav/controls/windows/windows_nav_controls_widget.dart';
import 'package:flutter/material.dart';

class ControlsHeaderArea extends StatelessWidget {
  const ControlsHeaderArea({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: [getPlatformControlsHeader()],
      ),
    );
  }

  getPlatformControlsHeader() {
    switch (currentSystem) {
      case System.macOS:
        return MacOSNavControlsWidget();
      case System.windows:
        return WindowsNavControlsWidget();
      default:
        return const Text("Platform not Officially supported.");
    }
  }
}
