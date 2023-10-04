import 'package:bulo_ui/core/log/model/log.dart';
import 'package:bulo_ui/core/log/model/log_level.dart';
import 'package:flutter/material.dart';

class ErrorLog extends Log {
  ErrorLog(String msg)
      : super(
            color: Colors.red.shade900,
            msg: msg,
            timeStr: Log.timeStamp(),
            logLevel: LogLevel.error);
}
