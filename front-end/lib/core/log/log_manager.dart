import 'dart:async';

import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/log/model/log.dart';
import 'package:equatable/equatable.dart';

class LogManager extends Equatable {
  final ServerConfig? serverConfig;
  final int maxSize;
  final _logs = <Log>[];
  final _controller = StreamController<List<Log>>.broadcast();

  LogManager(this.serverConfig, {this.maxSize = 50});

  List<Log> get logs => _logs;

  Stream<List<Log>> get logsStream => _controller.stream;

  void add(Log log) {
    if (_logs.length >= maxSize) {
      _logs.removeAt(0); // Remove the oldest log
    }
    _logs.add(log);

    _controller.sink.add(_logs); // Notify listeners
  }

  void dispose() {
    _controller.close();
  }

  @override
  // TODO: implement props
  List<Object?> get props => [serverConfig];
}
