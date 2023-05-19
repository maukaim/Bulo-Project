import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:multi_split_view/multi_split_view.dart';

import 'home_divider_painter.dart';

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    TargetPlatform ta = TargetPlatform.iOS;
    switch (ta) {
      //all cases
      case TargetPlatform.iOS:
        break;
      case TargetPlatform.android:
        break;

      case TargetPlatform.fuchsia:
        break;
      default:
        break;
    }
    ;
    ;
    MultiSplitViewController _controller = MultiSplitViewController(areas: [
      Area(weight: 0.16, minimalWeight: .10),
      Area(minimalWeight: .66)
    ]);

    MultiSplitView multiSplitView = MultiSplitView(
      controller: _controller,
      children: [
        Padding(
          padding: const EdgeInsets.symmetric(vertical: 24, horizontal: 0),
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.fromLTRB(4, 10, 10, 10),
                child: Center(
                  child: Image.asset(
                    'assets/images/bulo-logo-alpha-cyan.png',
                    height: 80,
                  ),
                ),
              ),
              const Expanded(
                child: SingleChildScrollView(
                  child: Padding(
                    padding: EdgeInsets.fromLTRB(0, 32, 0, 0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            Icon(
                              size: 16,
                              CupertinoIcons.arrow_2_squarepath,
                              color: Colors.blueGrey,
                            ),
                            Text(
                              " Flows Available",
                              style: TextStyle(
                                  color: Colors.blueGrey,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 12),
                            )
                          ],
                        ),
                        Padding(
                          padding: EdgeInsets.fromLTRB(16, 16, 0, 0),
                          child: Column(
                            children: [
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              ),
                              Text(
                                "Mail Automatisation - Maukaim",
                                style: TextStyle(
                                    color: Colors.blueGrey,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 12),
                              )
                            ],
                          ),
                        )
                      ],
                    ),
                  ),
                ),
              ),
              const Row(
                mainAxisAlignment: MainAxisAlignment.center, // Center align
                children: <Widget>[
                  Icon(CupertinoIcons.arrow_2_squarepath, color: Colors.cyan),
                  // First icon
                  SizedBox(width: 16),
                  // Gap of 16
                  Icon(CupertinoIcons.app_badge_fill, color: Colors.blueGrey),
                  // Second icon
                  SizedBox(width: 16),
                  // Gap of 16
                  Icon(CupertinoIcons.person_3_fill, color: Colors.blueGrey),
                  // Third icon
                ],
              ),
            ],
          ),
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(4, 10, 10, 10),
          child: Container(
            decoration: const BoxDecoration(
                boxShadow: [
                  BoxShadow(
                    offset: Offset(-4, -4),
                    spreadRadius: -3,
                    blurRadius: 4,
                    color: Color.fromRGBO(0, 0, 0, 0.15),
                  ),
                  BoxShadow(
                    offset: Offset(4, 4),
                    spreadRadius: -3,
                    blurRadius: 4,
                    color: Color.fromRGBO(0, 0, 0, 0.15),
                  ),
                  BoxShadow(
                    offset: Offset(-4, 4),
                    spreadRadius: -3,
                    blurRadius: 4,
                    color: Color.fromRGBO(0, 0, 0, 0.15),
                  ),
                  BoxShadow(
                    offset: Offset(4, -4),
                    spreadRadius: -3,
                    blurRadius: 4,
                    color: Color.fromRGBO(0, 0, 0, 0.15),
                  ),
                ],
                color: Colors.white,
                borderRadius: BorderRadius.all(Radius.circular(8))),
          ),
        )
      ],
    );

    MultiSplitViewTheme theme = MultiSplitViewTheme(
      data: MultiSplitViewThemeData(
        dividerThickness: 4,
        dividerPainter: HomeDividerPainter(),
      ),
      child: Padding(
        padding: const EdgeInsets.all(0.0),
        child: multiSplitView,
      ),
    );

    return Scaffold(
      backgroundColor: const Color.fromARGB(255, 145, 213, 208),
      body: theme,
    );
  }
}
