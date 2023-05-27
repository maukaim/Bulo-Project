import 'package:http/http.dart' as http;

class BackendConnector{
  String _addressRoot = 'localhost:10020';
  static const String API_VERSION = 'api/v1';
  static const String HTTP_PROTOCOL = 'http';

  set addressRoot(String addressRoot) => _addressRoot = addressRoot;

  Future<http.Response> get(String addressPath) async {
    String address = '$HTTP_PROTOCOL://$_addressRoot/$API_VERSION/$addressPath';
    print("GET $address");
    http.Response response = await http.get(Uri.parse(address));
    if (response.statusCode == 200) {
      return response;
    } else {
      throw Exception('Failed to load from GET method on $address');
    }
  }
}