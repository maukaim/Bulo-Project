
import 'dart:ui';

import 'package:bulo_ui/core/log/model/log_level.dart';
import 'package:intl/intl.dart';

abstract class Log{
  final Color color;
  final String msg;
  final String timeStr;
  final LogLevel logLevel;

  Log({required this.color, required this.msg, required this.timeStr, required this.logLevel});

  static String timeStamp() {
    return DateFormat('yyyy-MM-dd HH:mm:ss.SSS').format(DateTime.now());
  }

  bool isError(){
    return this.logLevel == LogLevel.error;
  }
}