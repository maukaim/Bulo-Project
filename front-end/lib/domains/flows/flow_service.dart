import 'dart:convert';

import 'package:bulo_ui/core/log/logger.dart';
import 'package:bulo_ui/domains/flows/model/flow.dart';

import '../../core/connect/server_connector.dart';

class FlowService {
  final ServerConnector _backendConnector;
  final Logger logger;

  FlowService(this._backendConnector, this.logger);

  @override
  Future<List<Flow>> getAll() async {
    var response = await _backendConnector.get("flows");
    var decode = json.decode(response.body);
    var result = [for (var x in decode) Flow.fromJson(x)];
    logger.info("We have ${result.length} flows available: $result");
    return result;
  }
}
