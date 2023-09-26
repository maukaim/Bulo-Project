import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/global/buttons/basic_button.dart';
import 'package:bulo_ui/widgets/global/extensions/neumorphic_extension.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final ValueNotifier<bool> _isHovered = ValueNotifier<bool>(false);

class ServerItem extends ConsumerWidget {
  final ServerConfig serverConfig;

  const ServerItem(this.serverConfig, {super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return MouseRegion(
      onEnter: (_) => _isHovered.value = true,
      onExit: (_) => _isHovered.value = false,
      child: ValueListenableBuilder(
          valueListenable: _isHovered,
          builder: (context, isHovered, child) {
            return Column(
              children: [
                getServerItem(ref, isHovered),
                if (serverConfig.isEmbedded()) ...[
                  const Divider(
                    color: Color.fromRGBO(150, 150, 150, 0.5),
                  )
                ],
              ],
            );
          }),
    );
  }

  Widget getServerItem(WidgetRef ref, bool isHovered) {
    var selectedServerForSettings =
        ref.watch(selectedServerConfigForSettingsWindow);

    return selectedServerForSettings == serverConfig
        ? getServerItemVisual(ref, isHovered)
            .neumorphicPressed(borderRadius: BorderRadius.circular(6))
        : getServerItemVisual(ref, isHovered);
  }

  Widget getServerItemVisual(WidgetRef ref, bool isHovered) {
    var selectedServerForSettings =
        ref.watch(selectedServerConfigForSettingsWindow);

    var isServerConnected = ref.watch(
        isServerConnectedProvider(getServerConnector(ref, serverConfig)));
    var currentServer = ref.watch(currentServerProvider);

    return CustomButton(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
      hoverColor: Colors.blueGrey.withOpacity(0.075),
      onPressed: () {
        if (selectedServerForSettings != serverConfig) {
          ref.read(selectedServerConfigForSettingsWindow.notifier).state =
              serverConfig;
        }
      },
      child: Row(
        children: [
          Icon(
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
                return Colors.red;
              },
            ),
          ),
          Flexible(
            fit: FlexFit.tight,
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8),
              child: Text(
                serverConfig.serverName,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                  color: serverConfig.isEmbedded()
                      ? Colors.blueGrey
                      : Colors.black87,
                  fontWeight: serverConfig.isEmbedded()
                      ? FontWeight.bold
                      : FontWeight.normal,
                  fontSize: 12,
                ),
              ),
            ),
          ),
          if (isHovered && !serverConfig.isEmbedded()) ...[
            CustomButton(
              borderRadius: BorderRadius.zero,
              padding: EdgeInsets.all(4),
              child: Icon(
                Icons.clear,
                size: 10,
              ),
              onPressed: () {
                if (selectedServerForSettings == serverConfig) {
                  ref.invalidate(selectedServerConfigForSettingsWindow);
                }

                var serverManager = ref.watch(serverManagerProvider);
                serverManager.delete(serverConfig.id, ref);
                ref.invalidate(availableServersProvider);
              },
            ),
          ] else if (currentServer == serverConfig) ...[
            Icon(
              size: 8,
              Icons.circle,
              color: Colors.green,
            ),
          ]
        ],
      ),
    );
  }
}
