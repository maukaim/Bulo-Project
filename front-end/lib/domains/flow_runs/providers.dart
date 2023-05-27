import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/domains/flow_runs/flow_runs_service.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';


final flowRunsServiceProvider = Provider((ref) => FlowRunsService(ref.watch(backendConnectorProvider)));
