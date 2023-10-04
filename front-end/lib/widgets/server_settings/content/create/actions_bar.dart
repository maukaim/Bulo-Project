import 'package:bulo_ui/core/connect/model/remote_server_config.dart';
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/server_settings/content/create/providers.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/actions_bar.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class CreateAreaActionsBar extends ActionsBar {
  const CreateAreaActionsBar(
      {super.key,
      required super.isFormValid,
      required super.serverConfig,
      required super.formKey,
      required super.controllers});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var newServerConnectionWorks = ref.watch(oneTimeServerConnectionProvider);
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: [
        Row(
          children: [
            if (newServerConnectionWorks != null) ...[
              newServerConnectionWorks
                  ? getStaticSuccess()
                  : getStaticFailure(),
            ],
            getActionButton(
              "TEST",
              () {
                var serverConnector =
                    getServerConnector(ref, getNewServerConfig());
                ref
                    .read(oneTimeServerConnectionProvider.notifier)
                    .checkConnection(serverConnector);
              },
              isActivated: isFormValid,
            ),
            Padding(
              padding: const EdgeInsets.only(left: 8),
              child: getActionButton(
                "Register",
                () {
                  if (formKey.currentState!.validate()) {
                    register(serverConfig, ref);
                  }
                },
                isActivated: isFormValid,
              ),
            ),
          ],
        )
      ],
    );
  }

  void register(ServerConfig serverConfig, WidgetRef ref) {
    serverConfig.addressRoot = controllers.addressController.text;
    serverConfig.serverName = controllers.serverNameController.text;
    serverConfig.port = int.parse(controllers.portController.text);

    var newServerConfig = getNewServerConfig();

    var serverManager = ref.watch(serverManagerProvider);
    serverManager.add(newServerConfig);

    ref.invalidate(availableServersProvider);
    ref.read(selectedServerConfigForSettingsWindow.notifier).state =
        newServerConfig;
  }

  ServerConfig getNewServerConfig() {
    return RemoteServerConfig(
      controllers.addressController.text,
      int.parse(controllers.portController.text),
      controllers.serverNameController.text,
    );
  }

  Widget getStaticSuccess() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 8.0),
      child: Row(
        children: [
          Text(
            "Connection successful ! ",
            style: TextStyle(color: Colors.green),
          ),
          Icon(
            Icons.check,
            color: Colors.green,
          )
        ],
      ),
    );
  }

  Widget getStaticFailure() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 8.0),
      child: Row(
        children: [
          Text(
            "Connection failed ! ",
            style: TextStyle(color: Colors.red),
          ),
          Icon(
            Icons.clear,
            color: Colors.red,
          )
        ],
      ),
    );
  }
}
