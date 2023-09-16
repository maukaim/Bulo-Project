import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/ui/server_settings/settings_dialog.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerSettingsButton extends ConsumerWidget {
  const ServerSettingsButton({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var hasEmbeddedServer = ref.watch(hasEmbeddedServerProvider);

    return InkWell(
      onTap: () {
        showDialog(
          barrierColor: Colors.black12.withOpacity(0.2),
          context: context,
          builder: (BuildContext context) {
            return SettingsDialog(dialogContext: context);
          },
        );
        // Handle your click action here
      },
      child: Padding(
        padding: const EdgeInsets.all(4.0),
        child: Icon(
          size: 20,
          CupertinoIcons.desktopcomputer,
          color: hasEmbeddedServer.when(
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
    );
  }
}
