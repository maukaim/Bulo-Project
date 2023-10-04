import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/model/server_nature.dart';
import 'package:bulo_ui/core/connect/model/server_type.dart';

class EmbeddedServerConfig extends ServerConfig {
  final String javaExecutablePath;

  EmbeddedServerConfig(int port, this.javaExecutablePath)
      : super("This Computer", "localhost", port, ServerNature.embedded,
            ServerType.standalone);

  @override
  set addressRoot(String value) {
    throw Exception("Setter not allowed by default.");
  }

  @override
  set serverName(String value) {
    throw Exception("Setter not allowed by default.");
  }
}
