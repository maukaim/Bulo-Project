import 'package:bulo_ui/widgets/server_settings/content/create/actions_bar.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/form/form_area.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/server_details_area.dart';
import 'package:flutter/material.dart';

class ServerDetailsCreateArea extends ServerDetailsArea {
  ServerDetailsCreateArea({required super.serverConfig});

  @override
  ServerDetailsCreateAreaState createState() {
    return ServerDetailsCreateAreaState();
  }
}

class ServerDetailsCreateAreaState
    extends ServerDetailsAreaState<ServerDetailsCreateArea> {
  @override
  Widget buildDetailsArea(BuildContext context) {

    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(bottom:8.0),
              child: ServerConfigFormArea(
                onFormChanged: () {
                  isFormValidNotifier.value = formKey.currentState!.validate();
                },
                serverConfig: widget.serverConfig,
                formKey: formKey,
                controllers: controllers!,
              ),
            ),
            ValueListenableBuilder<bool>(
              valueListenable: isFormValidNotifier,
              builder: (context, isValid, child) {
                return CreateAreaActionsBar(
                  isFormValid: isValid,
                  serverConfig: widget.serverConfig,
                  formKey: formKey,
                  controllers: controllers!,
                );
              },
            ),
            Flexible(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 12.0),
                child: Container(color: Colors.green),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
