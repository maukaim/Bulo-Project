import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerSettingsContentArea extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var selectedServer = ref.watch(selectedServerConfigForSettingsWindow);

    return selectedServer == null ? getDefaultScreen() : Text("Selected ${selectedServer.serverName}");
  }

  Widget getDefaultScreen() {
    return const Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Icon(
          size: 24,
          Icons.public,
          color: Colors.blueGrey,
        ),
        Text(
          "Select a Server to modify its configuration.",
          style: TextStyle(
              color: Colors.blueGrey,
              fontSize: 16,
              fontWeight: FontWeight.bold),
        ),
      ],
    );
  }
}
