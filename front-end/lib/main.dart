import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:multi_split_view/multi_split_view.dart';
import 'package:provider/provider.dart';

import 'CustomDividerPainter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => MyAppState(),
      child: MaterialApp(
        title: 'Namer App',
        theme: ThemeData(
          useMaterial3: true,
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        ),
        home: MyHomePage(),
      ),
    );
  }
}

class MyAppState extends ChangeNotifier {
  var current = WordPair.random();

  void getNext() {
    current = WordPair.random();
    notifyListeners();
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var appState = context.watch<MyAppState>();

    MultiSplitViewController _controller = MultiSplitViewController(areas: [
      Area(weight: 0.16, minimalWeight: .10),
      Area(minimalWeight: .66)
    ]);

    MultiSplitView multiSplitView = MultiSplitView(
      controller: _controller,
      children: [
        const Padding(
          padding: EdgeInsets.fromLTRB(4, 16, 0, 16),
          child: Column(
            children: [
              Text("Flows Available"),
              SizedBox(height: 48),
              Text("Flows Parabola"),
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
        dividerPainter: CustomDividerPainter(),
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
