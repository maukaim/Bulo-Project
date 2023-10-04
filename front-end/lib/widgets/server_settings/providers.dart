
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

void deleteServer(WidgetRef ref, ServerConfig serverConfig){
  var selectedServerForSettings = ref.watch(selectedServerConfigForSettingsWindow);
  if (selectedServerForSettings == serverConfig) {
    ref.invalidate(selectedServerConfigForSettingsWindow);
  }

  var serverManager = ref.watch(serverManagerProvider);
  serverManager.delete(serverConfig.id, ref);
  ref.invalidate(availableServersProvider);
}