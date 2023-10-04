
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/log/log_manager.dart';
import 'package:bulo_ui/core/log/logger.dart';
import 'package:bulo_ui/core/log/model/log.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final loggerProvider = Provider.autoDispose.family<Logger, ServerConfig?>((ref,serverConfig) {
  var logManager = ref.watch(logManagerProvider(serverConfig));
  return Logger(logManager);
});

final logManagerProvider = Provider.family<LogManager, ServerConfig?>((ref, serverConfig) {
  return LogManager(serverConfig);
});

final logsStreamProvider = StreamProvider.family<List<Log>, ServerConfig?>(
      (ref, serverConfig) => ref.watch(logManagerProvider(serverConfig)).logsStream,
);

