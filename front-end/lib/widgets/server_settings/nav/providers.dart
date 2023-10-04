

import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final selectedServerConfigForSettingsWindow = StateProvider.autoDispose<ServerConfig?>((ref) => null);

final availableServersProvider = Provider<List<ServerConfig>>((ref) {
  var serverManager = ref.watch(serverManagerProvider);
  return serverManager.allServers;
});
