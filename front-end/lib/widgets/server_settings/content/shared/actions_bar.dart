import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/widgets/global/buttons/basic_button.dart';
import 'package:bulo_ui/widgets/server_settings/content/shared/server_details_area.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

abstract class ActionsBar extends ConsumerWidget {
  final ServerConfig serverConfig;
  final GlobalKey<FormState> formKey;
  final FormControllers controllers;
  final bool? isFormValid;

  const ActionsBar({super.key,
    required this.isFormValid,
    required this.serverConfig,
    required this.formKey,
    required this.controllers});

  Widget getActionButton(String text,
      VoidCallback onPressedAction, {
        bool? isActivated = true,
        Color basicColor = const Color(0xFFB0BEC5),
        Color hoverColor = const Color(0xFF90A4AE),
        Color textColor = Colors.black,
        Color disabledTextColor = Colors.grey,
      }) {
    return CustomButton(
      isEnabled: isActivated ?? false,
      borderRadius: BorderRadius.zero,
      onPressed: onPressedAction,
      color: basicColor,
      hoverColor: hoverColor,
      child: Text(
        text,
        style: TextStyle(
          color: isActivated ?? false ? textColor : disabledTextColor,
          fontSize: 12,
        ),
      ),
    );
  }
}
