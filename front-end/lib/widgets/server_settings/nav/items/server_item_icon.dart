
import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerItemIcon extends ConsumerWidget{
  final ServerConfig serverConfig;

  ServerItemIcon(this.serverConfig);
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var isServerConnected = ref.watch(
        isServerConnectedProvider(getServerConnector(ref, serverConfig)));

    return Icon(
      size: 20,
      serverConfig.isEmbedded()
          ? CupertinoIcons.desktopcomputer
          : Icons.public,
      color: isServerConnected.when(
        loading: () => Colors.black87,
        data: (data) {
          return data ? Colors.green : Colors.red;
        },
        error: (_, __) {
          print(
              "Issue when checking IsServerConnected for ${serverConfig.serverName}. Error itself: $_ \nStack Trace: $__");
          return Colors.grey;
        },
      ),
    );
  }

}