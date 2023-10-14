import 'package:bulo_ui/core/connect/model/remote_server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/global/buttons/bulo_button.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerConfigAddButton extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Center(
      child: BuloButton(
          padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
          hoverColor: Colors.blueGrey.withOpacity(0.075),
          onPressed: () {
            ref.read(selectedServerConfigForSettingsWindow.notifier).state = RemoteServerConfig("", 80, "");
          },
          child: const Row(
            children: [
              Padding(
                padding: EdgeInsets.only(right: 4.0),
                child: Icon(
                  Icons.add,
                  size: 16,
                  weight: 800,
                  // color: Colors.blueGrey,
                ),
              ),
              Flexible(
                child: Text(
                  "Add Server",
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(color: Colors.grey, fontSize: 12),
                ),
              ),
            ],
          )),
    );
  }
}
