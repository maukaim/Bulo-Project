import 'package:bulo_ui/widgets/global/buttons/bulo_button.dart';
import 'package:bulo_ui/widgets/global/extensions/neumorphic_extension.dart';
import 'package:bulo_ui/widgets/global/splitter/home_divider_painter.dart';
import 'package:bulo_ui/widgets/server_settings/content/content_area.dart';
import 'package:bulo_ui/widgets/server_settings/nav/server_settings_navbar.dart';
import 'package:flutter/material.dart' hide BoxDecoration, BoxShadow;
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:multi_split_view/multi_split_view.dart';

class SettingsDialog extends ConsumerWidget {
  final BuildContext dialogContext;

  const SettingsDialog({super.key, required this.dialogContext});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(36, 48, 36, 36),
      child: Column(
        children: [
          getDialogHeader(),
          getDialogContent(),
        ],
      ).glassmorphic(borderRadius: BorderRadius.circular(16)),
    );
  }

  Widget getDialogHeader() {
    return Row(
      children: [
        BuloButton(
          borderRadius:
              const BorderRadius.only(bottomRight: Radius.circular(8)),
          child: const Icon(
            Icons.clear,
            size: 16,
            color: Colors.black54,
          ),
          onPressed: () {
            Navigator.of(dialogContext).pop();
          },
        ),
        const Flexible(
          child: Center(
            child: Text(
              "Server Settings",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 14,
                color: Colors.blueGrey, // dark grey color
              ),
            ),
          ),
        ),
      ],
    );
  }

  getDialogContent() {
    MultiSplitViewController _controller = MultiSplitViewController(areas: [
      Area(weight: 0.16, minimalSize: 150),
      Area(minimalWeight: .80)
    ]);

    return Flexible(
      child: MultiSplitViewTheme(
        data: MultiSplitViewThemeData(
          dividerThickness: 4,
          dividerPainter: HomeDividerPainter(),
        ),
        child: Container(
          child: MultiSplitView(
            controller: _controller,
            children: [
              ServerSettingsNavBar(),
              Padding(
                padding: const EdgeInsets.all(10),
                child: ServerSettingsContentArea()
                    .neumorphicPressed(borderRadius: BorderRadius.circular(6)),
              )
            ],
          ),
        ),
      ),
    );
  }
}
