import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/log/logger.dart';
import 'package:equatable/equatable.dart';
import 'package:http/http.dart' as http;

class ServerConnector extends Equatable {
  final ServerConfig? serverConfig;
  final Logger logger;
  static const String API_VERSION = 'api/v1';
  static const String HTTP_PROTOCOL = 'http';

  static const String HEALTH_CHECK_PATH ='swagger-ui';
  bool serverIsUp = false;

  ServerConnector(this.serverConfig, this.logger);

  Future<bool> healthCheck() async {
    if (serverConfig != null) {
      var uri = Uri.http('${serverConfig!.addressRoot}:${serverConfig!.port}',
          '/$HEALTH_CHECK_PATH/');
      http.Response response = await http.head(uri);

      if (response.statusCode == 200) {
        logger.info("Head check successful!");
        return true;
      } else {
        String errorMessage = 'Sounds like an issue with $uri. Response status code is ${response.statusCode}.';
        logger.error(errorMessage);
        return false;
      }
    } else {
      String errorMessage = "No server config, can't health check.";
      logger.error(errorMessage);
      return false;
    }
  }

  Future<http.Response> get(String addressPath,
      [Map<String, String> requestParam = const {}]) async {
    if (serverConfig != null) {
      var uri = Uri.http('${serverConfig!.addressRoot}:${serverConfig!.port}',
          '/$API_VERSION/$addressPath', requestParam);
      print("GET $uri");
      http.Response response = await http.get(uri);
      if (response.statusCode == 200) {
        logger.info(
            "GET from $addressPath successful. Data received is:\n ${response.body}");
        return response;
      } else {
        String errorMessage = 'Failed to load from GET method on $uri';
        logger.error(errorMessage);
        throw Exception(errorMessage);
      }
    } else {
      String errorMessage = "No server config selected. Please choose one";
      logger.error(errorMessage);
      throw Exception(errorMessage);
    }
  }

  @override
  List<Object?> get props => [serverConfig];
}
