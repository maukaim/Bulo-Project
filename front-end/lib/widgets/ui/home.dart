import 'package:bulo_ui/widgets/ui/content/main_content.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_bar.dart';
import 'package:flutter/material.dart';
import 'package:multi_split_view/multi_split_view.dart';

import 'splitter/home_divider_painter.dart';

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    MultiSplitViewController _controller = MultiSplitViewController(areas: [
      Area(weight: 0.16, minimalSize: 150),
      Area(minimalWeight: .66)
    ]);

    return Scaffold(
      backgroundColor: const Color.fromARGB(255, 255, 255, 255),
      body: MultiSplitViewTheme(
        data: MultiSplitViewThemeData(
          dividerThickness: 4,
          dividerPainter: HomeDividerPainter(),
        ),
        child: Padding(
          padding: const EdgeInsets.all(0.0),
          child: MultiSplitView(
            controller: _controller,
            children: [NavBar(), MainArea()],
          ),
        ),
      ),
    );
  }
}
