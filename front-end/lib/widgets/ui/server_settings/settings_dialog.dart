import 'package:bulo_ui/widgets/global/buttons/general_button.dart';
import 'package:bulo_ui/widgets/global/extensions/container/glassmorphic_extension.dart';
import 'package:bulo_ui/widgets/global/extensions/neumorphic_extension.dart';
import 'package:bulo_ui/widgets/ui/splitter/home_divider_painter.dart';
import 'package:flutter/material.dart' hide BoxDecoration, BoxShadow;
import 'package:flutter_inset_box_shadow/flutter_inset_box_shadow.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:multi_split_view/multi_split_view.dart';

class SettingsDialog extends ConsumerWidget {
  final BuildContext dialogContext;

  const SettingsDialog({super.key, required this.dialogContext});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(24, 36, 24, 0),
      child: Column(
        children: [
          getDialogHeader(),
          getDialogContent(),
        ],
      ).glassmorphic(borderRadius: BorderRadius.circular(10)),
    );
  }

  Widget getDialogHeader() {
    return Row(
      children: [
        Container(
          child: CustomIconButton(
            icon: const Icon(
              Icons.clear,
              size: 16,
              color: Colors.black26,
            ),
            onTap: () {
              Navigator.of(dialogContext).pop();
            },
          ),
        ),
        Flexible(
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
              Container(
                child: Text("Ici la liste des servers"),
              ),
              Padding(
                padding: const EdgeInsets.all(10),
                child: Container(
                  padding: EdgeInsets.all(8),
                  child: Text("ICi les futures configs"),
                ).neumorphicPressed(
                    borderRadius: BorderRadius.circular(6)),
              )
            ],
          ),
        ),
      ),
    );
  }
}
