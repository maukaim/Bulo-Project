import 'package:bulo_ui/core/connect/model/remote_server_config.dart';
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/global/buttons/popup_button.dart';
import 'package:bulo_ui/widgets/main_window/nav/controls/shared/server_details_choice.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:bulo_ui/widgets/server_settings/settings_dialog.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerSettingsButton extends ConsumerWidget {
  final double serverIconSize;

  const ServerSettingsButton({required this.serverIconSize, super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var currentServer = ref.watch(currentServerProvider);
    final isCurrentServerConnected =
        ref.watch(isServerConnectedProvider(getCurrentServerConnector(ref)));
    var servers = ref.watch(availableServersProvider);
    servers.sort((a, b) => (b.isEmbedded() ? 1 : 0) - (a.isEmbedded() ? 1 : 0));

    var serverManager = ref.watch(serverManagerProvider);

    var placeHolderForGeneralSettings = RemoteServerConfig("", 80, "");
    return Flexible(
      child: Padding(
        padding: const EdgeInsets.only(left: 4.0),
        child: CustomPopupButton(
          choices: [...servers, placeHolderForGeneralSettings],
          itemBuilder: (ServerConfig value) {
            if (value == placeHolderForGeneralSettings) {
              return Padding(
                padding: const EdgeInsets.symmetric(vertical: 2.0),
                child: Text("Server Settings...",
                    style: TextStyle(
                        fontSize: 12, color: Colors.blueGrey.shade300)),
              );
            } else {
              return ServerDetailsChoice(server: value);
            }
          },
          onSelected: (ServerConfig? choice) {
            if (servers.contains(choice)) {
              serverManager.switchCurrentServer(choice);
            } else {
              _showPopupMenu(context);
            }
          },
          child: Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 8.0),
                child: Icon(
                  size: serverIconSize,
                  currentServer == null
                      ? Icons.public
                      : currentServer.isEmbedded()
                          ? CupertinoIcons.desktopcomputer
                          : Icons.public,
                  color: isCurrentServerConnected.when(
                    loading: () => Colors.grey,
                    data: (data) {
                      return data ? Colors.green : Colors.red;
                    },
                    error: (_, __) {
                      print(
                          "Issue when checking EmbeddedServer. Error itself: $_ \nStack Trace: $__");
                      return Colors.orangeAccent;
                    },
                  ),
                ),
              ),
              Flexible(
                child: Text(
                  currentServer?.serverName ?? "Select server",
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(
                      fontSize: 12,
                      fontWeight: FontWeight.bold,
                      color: Colors.blueGrey.shade300),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _showPopupMenu(BuildContext context) {
    showDialog(
      barrierColor: Colors.black12.withOpacity(0.2),
      context: context,
      builder: (BuildContext context) {
        return SettingsDialog(dialogContext: context);
      },
    );
  }
}
