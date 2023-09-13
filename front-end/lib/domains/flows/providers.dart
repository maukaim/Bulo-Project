import 'package:bulo_ui/core/connect/providers.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'flow_service.dart';
import 'model/flow.dart';

final flowServiceProvider =
    Provider((ref) => FlowService(ref.watch(backendConnectorProvider)));

final flowsAvailableProvider = FutureProvider.autoDispose.family<List<Flow>,String>((ref,currentServerDomain) async {
  var flowService = ref.watch(flowServiceProvider);
  return flowService.getAll();
});
/**
 // * 1. utiliser ce flowsAvailableProvider, pour afficher la liste des flows dans la navbar
 * 2. ajouter le call au backend dans le FlowService
 * 3. ajouter le button refresh qui ref._refresh(<provider>)
 */