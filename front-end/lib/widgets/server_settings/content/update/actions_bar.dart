import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/actions_bar.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:bulo_ui/widgets/server_settings/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class UpdateAreaActionsBar extends ActionsBar {
  const UpdateAreaActionsBar(
      {super.key,
      required super.isFormValid,
      required super.serverConfig,
      required super.formKey,
      required super.controllers});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        getDangerousButton("DELETE", () {
          deleteServer(ref, serverConfig);
        }),
        Row(
          children: [
            getActionButton("CANCEL", () {
              Navigator.of(context).pop();
            }),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8),
              child: getActionButton(
                "APPLY",
                () {
                  if (formKey.currentState!.validate()) {
                    update(serverConfig, ref);
                  }
                },
                isActivated: isFormValid,
              ),
            ),
            getActionButton(
              "OK",
              () {
                if (formKey.currentState!.validate()) {
                  update(serverConfig, ref);
                }

                Navigator.of(context).pop();
              },
              isActivated: isFormValid,
            ),
          ],
        )
      ],
    );
  }

  Widget getDangerousButton(String text, VoidCallback actionOnPress) {
    return getActionButton(
      text,
      actionOnPress,
      basicColor: Color(0xFFD32F2F),
      hoverColor: Color(0xFFC62828),
      textColor: Color(0xFFFFFFFF),
    );
  }

  void update(ServerConfig serverConfig, WidgetRef ref) {
    if (!serverConfig.isEmbedded()) {
      serverConfig.addressRoot = controllers.addressController.text;
      serverConfig.serverName = controllers.serverNameController.text;
    }
    serverConfig.port = int.parse(controllers.portController.text);

    ref.invalidate(serverConnectorProvider(serverConfig));
    ref.invalidate(availableServersProvider);
  }
}
