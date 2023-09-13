import 'package:bulo_ui/core/connect/backend_service.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'backend_connector.dart';

final backendServiceProvider =
    Provider((ref) => BackendService(ref.watch(backendConnectorProvider)));
final backendConnectorProvider = Provider((ref) => BackendConnector());

// Embedded Server Management
const hasEmbeddedServerChannel =
    MethodChannel('com.maukaim.bulo/hasEmbeddedServer');
final hasEmbeddedServerProvider = FutureProvider<bool>((ref) async {
  final bool hasEmbeddedServer =
      await hasEmbeddedServerChannel.invokeMethod('hasEmbeddedServer');
  return hasEmbeddedServer;
});

final currentServerDomainProvider = StateProvider((ref) => "localhost"); // Default value to Machine.