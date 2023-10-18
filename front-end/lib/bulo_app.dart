import 'package:bulo_ui/core/util/current_system.dart';
import 'package:bulo_ui/widgets/main_window/home.dart';
import 'package:desktop_window/desktop_window.dart';
import 'package:flutter/material.dart';

class BuloApp extends StatelessWidget {
  const BuloApp({super.key});

  @override
  Widget build(BuildContext context) {
    if(currentSystem.isDesktop){
      if (currentSystem case System.macOS) {
        DesktopWindow.setMinWindowSize(const Size(470, 380));
      } else if (currentSystem case System.windows) {
       DesktopWindow.setMinWindowSize(const Size(1200, 600));
      }
    }

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
