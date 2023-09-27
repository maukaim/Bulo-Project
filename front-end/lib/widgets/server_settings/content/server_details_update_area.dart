import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/widgets/server_settings/content/form/form_area.dart';
import 'package:flutter/material.dart';

class ServerDetailsUpdateArea extends StatefulWidget {
  final ServerConfig serverConfig;

  ServerDetailsUpdateArea(this.serverConfig);

  @override
  ServerDetailsUpdateAreaState createState() {
    return ServerDetailsUpdateAreaState();
  }
}

class ServerDetailsUpdateAreaState extends State<ServerDetailsUpdateArea> {
  @override
  Widget build(BuildContext context) {
    final _formKey = GlobalKey<FormState>();
    final controllers = FormControllers(widget.serverConfig);
    // Build a Form widget using the _formKey created above.
    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
        child: Column(
          children: [
            ServerConfigFormArea(
              serverConfig: widget.serverConfig,
              formKey: _formKey,
              controllers: controllers,
            ),
            Flexible(
              child: Padding(
                padding: const EdgeInsets.only(top: 16.0),
                child: Container(color: Colors.green),
              ),
            ),
            //TODO: Add buttons area
          ],
        ),
      ),
    );
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
}
