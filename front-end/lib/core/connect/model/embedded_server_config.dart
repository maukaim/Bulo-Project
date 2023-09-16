import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/connect/model/server_nature.dart';
import 'package:bulo_ui/core/connect/model/server_type.dart';

class EmbeddedServer extends ServerConfig {
  final String javaExecutablePath;

  EmbeddedServer(int port, this.javaExecutablePath)
      : super(
          "localhost",
          port,
          ServerNature.embedded,
          ServerType.standalone,
          "This Computer",
        );
}
