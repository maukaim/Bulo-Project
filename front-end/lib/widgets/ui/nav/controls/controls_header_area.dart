import 'package:bulo_ui/core/util/current_system.dart';
import 'package:bulo_ui/widgets/ui/nav/controls/macos/macos_controls_header.dart';
import 'package:bulo_ui/widgets/ui/nav/controls/windows_controls_header.dart';
import 'package:flutter/material.dart';

class ControlsHeaderArea extends StatelessWidget {
  const ControlsHeaderArea({super.key});

  @override
  Widget build(BuildContext context) {
    switch (currentSystem) {
      case System.macOS:
        return const MacOSControlsHeader();
      case System.windows:
        return const WindowsControlsHeader();
      default:
        return const Center();
    }
  }
}
