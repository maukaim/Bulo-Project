import 'dart:convert';

import 'package:bulo_ui/core/connect/server_connector.dart';

import 'model/flow_run.dart';

class FlowRunsService {
  final ServerConnector _backendConnector;

  FlowRunsService(this._backendConnector);

  @override
  Future<List<FlowRunView>> getByFlowId(String flowId) async {
    var response =
        await _backendConnector.get("runs/getByFlowId", {'flowId': flowId});
    var decode = json.decode(response.body);
    return [for (var x in decode) FlowRunView.fromJson(x)];
  }
}
