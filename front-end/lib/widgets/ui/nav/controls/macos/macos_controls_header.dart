import 'package:bulo_ui/widgets/ui/nav/controls/macos/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class MacOSControlsHeader extends ConsumerWidget {
  const MacOSControlsHeader({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final fullScreenStateStream = ref.watch(fullscreenStatusProvider);

    double flashLightControlsHeight = 28;

    return SizedBox(
      height: flashLightControlsHeight,
      child: getNavControls(fullScreenStateStream),
    );
  }

  Widget getNavControls(AsyncValue<bool> fullScreenStateStream) {
    double flashLightControlsHWidth = 60;

    List<Widget> children = [
      const Expanded(
        child: Center(
          child: Icon(
            size: 20,
            CupertinoIcons.desktopcomputer,
            color: Colors.green,
          ),
        ),
      ),
    ];

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
        return Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: children);
      },
      loading: () => CircularProgressIndicator(),
      error: (error, stackTrace) => Text('Error: $error'),
    );
  }
}
