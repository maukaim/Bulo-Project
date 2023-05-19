import 'package:bulo_ui/core/connect/backend_service.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'backend_connector.dart';


final backendServiceProvider = Provider((ref) => BackendService(ref.watch(backendConnectorProvider)));
final backendConnectorProvider = Provider((ref) => BackendConnector());
