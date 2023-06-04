import 'dart:convert';

import 'package:bulo_ui/domains/flows/model/flow.dart';

import '../../core/connect/backend_connector.dart';

class FlowService {
  final BackendConnector _backendConnector;

  FlowService(this._backendConnector);

  @override
  Future<List<Flow>> getAll() async{
      print("blalaba");
      var response = await _backendConnector.get("flows");
      var decode = json.decode(response.body);
      return [for (var x in decode) Flow.fromJson(x)];
  }
}