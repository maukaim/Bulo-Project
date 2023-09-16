import 'package:bulo_ui/core/connect/model/server_nature.dart';
import 'package:bulo_ui/core/connect/model/server_type.dart';
import 'package:equatable/equatable.dart';
import 'package:uuid/uuid.dart';

abstract class ServerConfig extends Equatable {
  final String id;
  final String serverName;
  final String addressRoot;
  final int port;
  final ServerNature serverNature;
  final ServerType serverType;

  ServerConfig(this.addressRoot, this.port, this.serverNature, this.serverType,
      this.serverName)
      : id = const Uuid().v4();

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;

    return other is ServerConfig && other.id == id;
  }

  @override
  int get hashCode => id.hashCode;

  @override
  List<Object?> get props => [id];
}
