import 'package:bulo_ui/widgets/main_window/home.dart';
import 'package:desktop_window/desktop_window.dart';
import 'package:flutter/material.dart';

class BuloApp extends StatelessWidget {
  const BuloApp({super.key});

  @override
  Widget build(BuildContext context) {
    DesktopWindow.setMinWindowSize(const Size(470, 380));

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Bulo Project',
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(),
    );
  }
}
