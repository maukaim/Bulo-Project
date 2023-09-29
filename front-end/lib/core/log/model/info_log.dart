import 'package:bulo_ui/core/log/model/log.dart';
import 'package:bulo_ui/core/log/model/log_level.dart';
import 'package:flutter/material.dart';

class InfoLog extends Log {
  InfoLog(String msg)
      : super(
            color: Colors.yellow,
            msg: msg,
            timeStr: Log.timeStamp(),
            logLevel: LogLevel.info);
}
