import 'package:bulo_ui/widgets/ui/nav/controls/shared/server_manager_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

abstract class AbstractNavControlsWidget extends ConsumerWidget {
  const AbstractNavControlsWidget({super.key});

  Widget getSharedNavWidget(double serverIconSize) {
    return Flexible(
      child: Center(
        child: ServerManagerWidget(serverIconSize),
      ),
    );
  }
}
