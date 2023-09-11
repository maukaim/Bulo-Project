import 'package:bulo_ui/widgets/ui/splitter/home_divider_painter.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:multi_split_view/multi_split_view.dart';

class SettingsDialog extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    MultiSplitViewController _controller = MultiSplitViewController(areas: [
      Area(weight: 0.16, minimalSize: 150),
      Area(minimalWeight: .80)
    ]);

    return Container(
      child: MultiSplitViewTheme(
        data: MultiSplitViewThemeData(
          dividerThickness: 4,
          dividerPainter: HomeDividerPainter(),
        ),
        child: Padding(
          padding: const EdgeInsets.fromLTRB(12, 36.0, 12, 12),
          child: Container(
            child: MultiSplitView(
              controller: _controller,
              children: [
                Container(
                  color: Colors.white70,
                  child: Text("Ici la liste des servers"),
                ),
                Container(
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(12)),
                    color: Colors.white,
                  ),
                  child: Text("ICi les futures configs"),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
