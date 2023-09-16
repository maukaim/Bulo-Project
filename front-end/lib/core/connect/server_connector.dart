import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:equatable/equatable.dart';
import 'package:http/http.dart' as http;

class ServerConnector extends Equatable {
  final ServerConfig? serverConfig;
  static const String API_VERSION = 'api/v1';
  static const String HTTP_PROTOCOL = 'http';
  bool serverIsUp = false;

  ServerConnector(this.serverConfig);

  Future<http.Response> get(String addressPath,
      [Map<String, String> requestParam = const {}]) async {
    if (serverConfig != null) {
      var uri = Uri.http('${serverConfig!.addressRoot}:${serverConfig!.port}',
          '/$API_VERSION/$addressPath', requestParam);
      print("GET $uri");
      http.Response response = await http.get(uri);
      if (response.statusCode == 200) {
        return response;
      } else {
        throw Exception('Failed to load from GET method on $uri');
      }
    } else {
      throw Exception("No server config selected. Please choose one");
    }
  }

  @override
  List<Object?> get props => [serverConfig];
}
