import 'package:bulo_ui/widgets/ui/server_settings/server_settings_button.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

abstract class AbstractNavControlsWidget extends ConsumerWidget {
  const AbstractNavControlsWidget({super.key});

  Widget getSharedNavWidget() {
    return const Flexible(
      child: Center(
        child: ServerSettingsButton(),
      ),
    );
  }
}
