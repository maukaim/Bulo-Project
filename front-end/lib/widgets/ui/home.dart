import 'package:bitsdojo_window/bitsdojo_window.dart';
import 'package:bulo_ui/core/util/current_system.dart';
import 'package:bulo_ui/widgets/ui/content/main_content.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_bar.dart';
import 'package:flutter/material.dart';
import 'package:multi_split_view/multi_split_view.dart';

import 'splitter/home_divider_painter.dart';

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {

    switch (currentSystem) {
      case System.windows:
        return Stack(
          children:[
            getDefaultHomePage(),
            SizedBox(
              height: 36,
                child: MoveWindow()),
          ]
        );
    default:
      return getDefaultHomePage();
     }
  }

  Widget getDefaultHomePage(){
    MultiSplitViewController _controller = MultiSplitViewController(areas: [
      Area(weight: 0.16, minimalSize: 150),
      Area(minimalWeight: .66)
    ]);
    return Scaffold(

      backgroundColor: const Color.fromARGB(255, 253, 253, 253),
      body: MultiSplitViewTheme(
        data: MultiSplitViewThemeData(
          dividerThickness: 4,
          dividerPainter: HomeDividerPainter(),
        ),
        child: Padding(
          padding: const EdgeInsets.all(0.0),
          child: MultiSplitView(
            controller: _controller,
            children:[
              NavBar(),
              MainArea(),
            ],
          ),
        ),
      ),
    );
  }
}
