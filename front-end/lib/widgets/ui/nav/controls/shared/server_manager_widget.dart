import 'dart:ui';

import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/widgets/ui/nav/controls/shared/settings_dialog.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerManagerWidget extends ConsumerWidget {

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var hasEmbeddedServer = ref.watch(hasEmbeddedServerProvider);

    return InkWell(
      onTap: () {
        showDialog(
          context: context,
          builder: (BuildContext context) {
            return SettingsDialog();
          },
        );
        // Handle your click action here
      },
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
    );
  }
}
