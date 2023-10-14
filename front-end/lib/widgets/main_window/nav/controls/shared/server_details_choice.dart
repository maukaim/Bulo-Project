import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerDetailsChoice extends ConsumerWidget {
  final ServerConfig server;

  ServerDetailsChoice({super.key, required this.server});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final isServerConnected =
        ref.watch(isServerConnectedProvider(getServerConnector(ref, server)));
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          Padding(
            padding: const EdgeInsets.only(right: 8.0),
            child: Icon(
              size: 18,
              server.isEmbedded()
                  ? CupertinoIcons.desktopcomputer
                  : Icons.public,
              color: isServerConnected.when(
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
              server.serverName,
              overflow: TextOverflow.ellipsis,
              style: TextStyle(
                  fontSize: 12,
                  fontWeight: FontWeight.bold,
                  color: Colors.blueGrey.shade300),
            ),
          ),
        ],
      ),
    );
  }
}
