import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/model/server_nature.dart';
import 'package:bulo_ui/core/connect/model/server_type.dart';

class RemoteServerConfig extends ServerConfig {
  RemoteServerConfig(String addressRoot, int port, String serverName)
      : super(serverName, addressRoot, port, ServerNature.remote,
            ServerType.standalone);
}
