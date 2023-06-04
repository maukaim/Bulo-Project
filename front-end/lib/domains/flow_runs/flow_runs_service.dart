import 'dart:convert';

import 'package:bulo_ui/core/connect/backend_connector.dart';

import 'model/flow_run.dart';

class FlowRunsService {
  final BackendConnector _backendConnector;

  FlowRunsService(this._backendConnector);

  @override
  Future<List<FlowRunView>> getByFlowId(String flowId) async {
    var response =
        await _backendConnector.get("runs/getByFlowId", {'flowId': flowId});
    var decode = json.decode(response.body);
    return [for (var x in decode) FlowRunView.fromJson(x)];
  }
}
