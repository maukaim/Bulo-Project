import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:flutter/material.dart';

abstract class ServerDetailsArea extends StatefulWidget {
  final ServerConfig serverConfig;

  const ServerDetailsArea({required this.serverConfig});

}

abstract class ServerDetailsAreaState<K extends ServerDetailsArea> extends State<ServerDetailsArea> {
  late GlobalKey<FormState> formKey;
  FormControllers? controllers;
  late ValueNotifier<bool> isFormValidNotifier;

  @override
  Widget build(BuildContext context) {
    formKey = GlobalKey<FormState>();
    controllers = FormControllers(widget.serverConfig);
    isFormValidNotifier = ValueNotifier<bool>(false);
    return buildDetailsArea(context);
  }

  Widget buildDetailsArea(BuildContext context);

  @override
  void dispose() {
    controllers?.dispose();
    super.dispose();
  }
}

class FormControllers {
  final TextEditingController serverNameController;
  final TextEditingController addressController;
  final TextEditingController portController;

  FormControllers(ServerConfig? serverConfig)
      : serverNameController =
            TextEditingController(text: serverConfig?.serverName),
        addressController =
            TextEditingController(text: serverConfig?.addressRoot),
        portController =
            TextEditingController(text: serverConfig?.port.toString());

  void dispose() {
    serverNameController.dispose();
    addressController.dispose();
    portController.dispose();
  }
}
