import 'package:bulo_ui/core/connect/model/server_nature.dart';
import 'package:bulo_ui/core/connect/model/server_type.dart';
import 'package:equatable/equatable.dart';
import 'package:uuid/uuid.dart';

abstract class ServerConfig extends Equatable {
  final String _id;
  String _serverName;
  String _addressRoot;
  int _port;
  final ServerNature _serverNature;
  final ServerType _serverType;

  ServerConfig(
    this._serverName,
    this._addressRoot,
    this._port,
    this._serverNature,
    this._serverType,
  ) : _id = const Uuid().v4();

  String get id => _id;

  String get serverName => _serverName;

  String get addressRoot => _addressRoot;

  int get port => _port;

  ServerNature get serverNature => _serverNature;

  ServerType get serverType => _serverType;

  set serverName(String value) {
    _serverName = value;
  }
  set addressRoot(String value) {
    _addressRoot = value;
  }

  set port(int value) {
    _port = value;
  }

  @override
  List<Object?> get props => [_id];

  bool isEmbedded(){
    return serverNature == ServerNature.embedded;
  }

//
// @override
// bool operator ==(Object other) {
//   if (identical(this, other)) return true;
//
//   return other is ServerConfig && other.id == id;
// }
//
// @override
// int get hashCode => _id.hashCode;

}
