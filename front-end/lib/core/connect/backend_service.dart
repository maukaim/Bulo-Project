import 'backend_connector.dart';

class BackendService {
  final BackendConnector _connector;

  BackendService(this._connector);

  changeBackendDns(String addressRoot){
    // use testConnection(addressRoot) method,
    // and then change the addressRoot if it was successful
  }
}
