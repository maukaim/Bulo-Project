import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final selectedFlowProvider = StateProvider.family<String?, ServerConnector>((ref, serverConnector) => null);

getSelectedFlowProvider(dynamic ref){
  return selectedFlowProvider(getCurrentServerConnector(ref));
}