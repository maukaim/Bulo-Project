import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/widgets/server_settings/content/form/field_input.dart';
import 'package:bulo_ui/widgets/server_settings/content/server_details_update_area.dart';
import 'package:flutter/cupertino.dart';

class ServerConfigFormArea extends StatelessWidget {
  final ServerConfig serverConfig;
  final GlobalKey<FormState> formKey;
  final FormControllers controllers;

  const ServerConfigFormArea(
      {super.key,
      required this.serverConfig,
      required this.formKey,
      required this.controllers});

  @override
  Widget build(BuildContext context) {
    return Form(
      autovalidateMode: AutovalidateMode.onUserInteraction,
      key: formKey,
      child: Column(
        children: [
          ServerConfigFieldInput(
            "Name",
            "Only latin characters",
            !serverConfig.isEmbedded(),
            controller: controllers.serverNameController,
            validator: serverNameValidator,
          ),
          Padding(
            padding: const EdgeInsets.only(top: 8.0),
            child: Row(
              textBaseline: TextBaseline.alphabetic,
              crossAxisAlignment: CrossAxisAlignment.baseline,
              children: [
                Flexible(
                  flex: 4,
                  child: ServerConfigFieldInput(
                    "Address",
                    "domain, IPv4 or localhost",
                    !serverConfig.isEmbedded(),
                    controller: controllers.addressController,
                    validator: addressValidator,
                  ),
                ),
                Flexible(
                  flex: 1,
                  child: Padding(
                    padding: const EdgeInsets.only(left: 8.0),
                    child: ServerConfigFieldInput(
                      "Port",
                      "80 to 65535",
                      true,
                      controller: controllers.portController,
                      validator: portValidator,
                    ),
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }

  FormFieldValidator<String> get serverNameValidator => (value) {
        final regexServerNameCoherent = RegExp(r'^[A-Za-z-_ ]+$');

        if (value == null || value.isEmpty || value.trim().isEmpty) {
          return "Server's name can't be blank";
        } else if (!RegExp(r'^[^\s].*').hasMatch(value)) {
          return "Can't start with space(s)";
        } else if (value.length < 3) {
          return "Minimum 3 characters";
        } else if (value.length > 25) {
          return "Maximum 25 characters";
        } else if (!regexServerNameCoherent.hasMatch(value)) {
          return "Only allowed latin characters, spaces, -, _";
        }

        return null;
      };

  FormFieldValidator<String> get addressValidator => (value) {
        final regexMatchAddressFormat = RegExp(
            r'(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})|(([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,})|(localhost)');

        if (value == null || value.isEmpty) {
          return "Address can't be empty";
        } else if (!regexMatchAddressFormat.hasMatch(value)) {
          return 'Enter a valid IP, domain name or "localhost"';
        }

        return null;
      };

  FormFieldValidator<String> get portValidator => (value) {
        final regexMatchPort = RegExp(
            r'^8[0-9]$|^9[0-9]$|^[1-9][0-9]{2}$|^[1-9][0-9]{3}$|^[1-5][0-9]{4}$|^6[0-4][0-9]{3}$|^65[0-5][0-2][0-9]$|^6553[0-5]$');

        if (value == null || value.isEmpty) {
          return "Port can't be empty";
        } else if (!RegExp(r'^\d+$').hasMatch(value)) {
          return "Only digits";
        } else if (!regexMatchPort.hasMatch(value)) {
          return "min: 80, max: 65535";
        }

        return null;
      };
}
