import 'package:bulo_ui/widgets/main_window/nav/controls/shared/abstract_nav_controls_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class WebNavControlsWidget extends AbstractNavControlsWidget {
  const WebNavControlsWidget({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return getSharedNavWidget();
  }
}
