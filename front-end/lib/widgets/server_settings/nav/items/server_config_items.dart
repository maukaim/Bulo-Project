import 'package:bulo_ui/widgets/server_settings/nav/items/server_item.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerConfigItems extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var allServers = ref.watch(availableServersProvider);
    allServers
        .sort((a, b) => (b.isEmbedded() ? 1 : 0) - (a.isEmbedded() ? 1 : 0));

    return Flexible(
      child: ListView.builder(
        itemCount: allServers.length,
        itemBuilder: (ctx, index) => Padding(
          padding: const EdgeInsets.symmetric(vertical: 2),
          child: ServerItem(allServers[index]),
        ),
      ),
    );
  }
}
