import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerConnectionNotifier extends StateNotifier<bool?> {
  ServerConnectionNotifier() : super(null);

  void checkConnection(ServerConnector serverConnector) async {
    final isConnected = await isServerConnected(serverConnector);
    state = isConnected == null;
  }
}

final oneTimeServerConnectionProvider = StateNotifierProvider.autoDispose<ServerConnectionNotifier, bool?>((ref) => ServerConnectionNotifier());