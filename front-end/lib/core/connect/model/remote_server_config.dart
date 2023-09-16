import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/model/server_nature.dart';
import 'package:bulo_ui/core/connect/model/server_type.dart';

class RemoteServer extends ServerConfig {
  RemoteServer(String addressRoot, int port, String serverName)
      : super(
          addressRoot,
          port,
          ServerNature.remote,
          ServerType.standalone,
          serverName,
        );
}
