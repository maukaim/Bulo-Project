import 'package:bulo_ui/widgets/server_settings/nav/add_button.dart';
import 'package:bulo_ui/widgets/server_settings/nav/items/server_config_items.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerSettingsNavBar extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 16, horizontal: 4),
      child: Column(
        children: [
          ServerConfigItems(),
          Padding(
            padding: const EdgeInsets.only(top:8.0),
            child: ServerConfigAddButton(),
          )
        ],
      ),
    );
  }
}
