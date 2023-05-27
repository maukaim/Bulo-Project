import 'dart:convert';
import 'dart:developer';

import 'package:bulo_ui/core/connect/backend_connector.dart';

import 'model/flow_run.dart';

class FlowRunsService {
  final BackendConnector _backendConnector;

  FlowRunsService(this._backendConnector);

  @override
  Future<List<FlowRun>> getByFlowId(String flowId) async {
    print("blalaba");
    var response = await _backendConnector.get("flowRuns/$flowId");
    var decode = json.decode(response.body);
    return [for (var x in decode) FlowRun.fromJson(x)];
  }
}
