import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/core/util/current_system.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class LogoArea extends ConsumerWidget {
  const LogoArea({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    AsyncValue hasEmbeddedServer = currentSystem.isDesktop
        ? ref.watch(hasEmbeddedServerProvider)
        : const AsyncValue.data(false);

    final isCurrentServerConnected =
        ref.watch(isServerConnectedProvider(getCurrentServerConnector(ref)));
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 0, 10, 10),
      child: Center(
        child: Stack(
          alignment: AlignmentDirectional.topEnd,
          children: [
            Image.asset(
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
            if (hasEmbeddedServer.maybeWhen(
                    data: (data) => data,
                    loading: () => false,
                    error: (_, __) => false,
                    orElse: () => false) ??
                false)
              const Tooltip(
                message: "This app has an embedded server.",
                child: Icon(
                  Icons.circle,
                  color: Colors.green,
                  size: 6,
                ),
              ),
          ],
        ),
      ),
    );
  }
}
