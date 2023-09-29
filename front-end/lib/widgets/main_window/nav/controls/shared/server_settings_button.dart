import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/global/buttons/basic_button.dart';
import 'package:bulo_ui/widgets/server_settings/settings_dialog.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerSettingsButton extends ConsumerWidget {
  const ServerSettingsButton({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var currentServer = ref.watch(currentServerProvider);
    final isCurrentServerConnected =
        ref.watch(isServerConnectedProvider(getCurrentServerConnector(ref)));
    return Flexible(
      child: Padding(
        padding: const EdgeInsets.only(left: 4.0),
        child: CustomButton(
          padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
          borderRadius: BorderRadius.vertical(bottom: Radius.circular(12)),
          onPressed: () {
            showDialog(
              barrierColor: Colors.black12.withOpacity(0.2),
              context: context,
              builder: (BuildContext context) {
                return SettingsDialog(dialogContext: context);
              },
            );
            // Handle your click action here
          },
          child: Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 8.0),
                child: Icon(
                  size: 18,
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
}
