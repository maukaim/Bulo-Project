import 'package:bitsdojo_window/bitsdojo_window.dart';
import 'package:bulo_ui/bulo_app.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';


void main() {
  runApp(const ProviderScope(child: BuloApp()));
  doWhenWindowReady(() {
    appWindow.alignment = Alignment.center;
    appWindow.show();
  });
}




