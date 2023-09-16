import 'package:bulo_ui/core/connect/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class LogoArea extends ConsumerWidget {
  const LogoArea({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final isCurrentServerConnected =
        ref.watch(isServerConnectedProvider(getCurrentServerConnector(ref)));
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 0, 10, 10),
      child: Center(
        child: Image.asset(
          'assets/images/bulo-logo-alpha-FULL-NAME.png',
          height: 40,
          color: isCurrentServerConnected.when(
            loading: () => Colors.deepPurpleAccent,
            data: (data) {
              return data ? null : Colors.grey;
            },
            error: (_, __) {
              print(
                  "Issue when checking EmbeddedServer. Error itself: $_ \nStack Trace: $__");
              return Colors.grey;
            },
          ), // For when The current server is not reachable
        ),
      ),
    );
  }
}
