import 'dart:io';
import 'dart:convert'; // Importez dart:convert pour utiliser utf8.decoder
import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter/services.dart';




import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'bulo_app.dart';




void main() {
  runApp(const ProviderScope(child: BuloApp()));
}

class BackendManager {
  Process? _backendProcess;
  final int backendPort = 10020; // Port local à utiliser

  Future<void> startBackend() async {
    final pathToJar = '../../../back-end/app-modules/standalone/standalone-app/target/application-standalone-1.0.0-SNAPSHOT.jar';

    try {
      _backendProcess = await Process.start('java', ['-jar', pathToJar]);
      _backendProcess?.stdout.transform(utf8.decoder).listen((data) {
        print('Backend Output: $data');
      });
    } catch (e) {
      print('Erreur lors du démarrage du backend : $e');
    }
  }

  Future<void> stopBackend() async {
    _backendProcess?.kill();
    await _backendProcess?.exitCode;
  }
}


