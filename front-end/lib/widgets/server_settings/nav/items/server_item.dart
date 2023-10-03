import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/global/buttons/basic_button.dart';
import 'package:bulo_ui/widgets/global/extensions/neumorphic_extension.dart';
import 'package:bulo_ui/widgets/server_settings/nav/items/server_item_icon.dart';
import 'package:bulo_ui/widgets/server_settings/nav/providers.dart';
import 'package:bulo_ui/widgets/server_settings/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';



class ServerItem extends ConsumerWidget {
  final ServerConfig serverConfig;

  const ServerItem(this.serverConfig, {super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final ValueNotifier<bool> _isHovered = ValueNotifier<bool>(false);

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
    var currentServer = ref.watch(currentServerProvider);

    var selectedServerForSettings =
        ref.watch(selectedServerConfigForSettingsWindow);

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
          ServerItemIcon(serverConfig),
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
                deleteServer(ref, serverConfig);
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
