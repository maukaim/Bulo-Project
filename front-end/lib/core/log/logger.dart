import 'package:bulo_ui/core/log/log_manager.dart';
import 'package:bulo_ui/core/log/model/error_log.dart';
import 'package:bulo_ui/core/log/model/info_log.dart';
import 'package:equatable/equatable.dart';
import 'package:intl/intl.dart';

class Logger extends Equatable {
  final LogManager logManager;

  Logger(this.logManager);

  void info(String msg) {
    logManager.add(InfoLog(msg));
  }

  void error(String msg) {
    logManager.add(ErrorLog(msg));
  }

  @override
  List<Object?> get props => [logManager];
}
