import 'dart:convert';

import 'package:bulo_ui/domains/flows/model/flow.dart';

import '../../core/connect/server_connector.dart';

class FlowService {
  final ServerConnector _backendConnector;

  FlowService(this._backendConnector);

  @override
  Future<List<Flow>> getAll() async {
    var response = await _backendConnector.get("flows");
    var decode = json.decode(response.body);
    return [for (var x in decode) Flow.fromJson(x)];
  }
}
