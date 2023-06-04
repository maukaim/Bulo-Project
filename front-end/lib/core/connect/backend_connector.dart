import 'package:http/http.dart' as http;

class BackendConnector {
  String _addressRoot = 'localhost:10020';
  static const String API_VERSION = 'api/v1';
  static const String HTTP_PROTOCOL = 'http';

  set addressRoot(String addressRoot) => _addressRoot = addressRoot;

  Future<http.Response> get(String addressPath, [Map<String, String> requestParam = const {}]) async {
    var uri = Uri.http(_addressRoot, '/$API_VERSION/$addressPath', requestParam);
    print("GET $uri");
    http.Response response = await http.get(uri);
    if (response.statusCode == 200) {
      return response;
    } else {
      throw Exception('Failed to load from GET method on $uri');
    }
  }
}
