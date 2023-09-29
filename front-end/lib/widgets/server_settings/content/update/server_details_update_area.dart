import 'package:bulo_ui/widgets/server_settings/content/shared/form/form_area.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/logs/logs_area.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/server_details_area.dart';
import 'package:bulo_ui/widgets/server_settings/content/update/actions_bar.dart';
import 'package:flutter/material.dart';

class ServerDetailsUpdateArea extends ServerDetailsArea {
  ServerDetailsUpdateArea({required super.serverConfig});

  @override
  ServerDetailsUpdateAreaState createState() {
    return ServerDetailsUpdateAreaState();
  }
}

class ServerDetailsUpdateAreaState
    extends ServerDetailsAreaState<ServerDetailsUpdateArea> {
  @override
  Widget buildDetailsArea(BuildContext context) {
    WidgetsBinding.instance.addPostFrameCallback((_) {
      if (formKey.currentState != null) {
        formKey.currentState!.validate();
      }
    });

    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
        child: Column(
          children: [
            ServerConfigFormArea(
              onFormChanged: () {
                isFormValidNotifier.value = formKey.currentState!.validate();
              },
              serverConfig: widget.serverConfig,
              formKey: formKey,
              controllers: controllers!,
            ),
            Flexible(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 12.0),
                child: LogsArea(widget.serverConfig),
              ),
            ),
            ValueListenableBuilder<bool>(
              valueListenable: isFormValidNotifier,
              builder: (context, isValid, child) {
                return UpdateAreaActionsBar(
                  isFormValid: isValid,
                  serverConfig: widget.serverConfig,
                  formKey: formKey,
                  controllers: controllers!,
                );
              },
            )
          ],
        ),
      ),
    );
  }
}
