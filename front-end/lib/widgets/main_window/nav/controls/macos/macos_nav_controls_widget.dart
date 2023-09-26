import 'package:bulo_ui/widgets/main_window/nav/controls/shared/providers.dart';
import 'package:bulo_ui/widgets/main_window/nav/controls/shared/abstract_nav_controls_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class MacOSNavControlsWidget extends AbstractNavControlsWidget {
  const MacOSNavControlsWidget({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final fullScreenStateStream = ref.watch(fullscreenStatusProvider);

    return getNavControls(fullScreenStateStream, ref);
  }

  Widget getNavControls(AsyncValue<bool> fullScreenStateStream, WidgetRef ref) {
    double flashLightControlsHWidth = 60;

    List<Widget> children = [getSharedNavWidget()];

    return fullScreenStateStream.when(
      data: (isFullscreen) {
        if (!isFullscreen) {
          children.insert(
              0,
              Container(
                width: flashLightControlsHWidth,
                color: Colors.transparent,
              ));
        }
        return getRowWidget(children);
      },
      loading: () => getRowWidget(children),
      error: (error, stackTrace) => getRowWidget(children),
    );
  }

  Widget getRowWidget(List<Widget> children) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: children,
    );
  }
}
