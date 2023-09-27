import 'package:flutter/material.dart';

class ServerConfigFieldInput extends StatelessWidget {
  final FormFieldValidator<String> validator;
  final TextEditingController controller;
  final bool isEnabled;
  final String hintText;
  final String label;

  ServerConfigFieldInput(this.label, this.hintText, this.isEnabled,
      {super.key, required this.validator, required this.controller});

  @override
  Widget build(BuildContext context) {
    return Row(
      textBaseline: TextBaseline.alphabetic,
      crossAxisAlignment: CrossAxisAlignment.baseline,
      children: [
        Text(
          "${label}: ",
          style: TextStyle(fontSize: 12),
        ),
        Flexible(
          child: TextFormField(
            style: TextStyle(fontSize: 10),
            enabled: isEnabled,
            controller: controller,
            validator: validator,
            decoration: InputDecoration(
              isDense: true,
              hintText: hintText,
              contentPadding: EdgeInsets.symmetric(horizontal: 6, vertical: 10),
              border: OutlineInputBorder(
                borderSide: BorderSide(
                  color: Colors.blueGrey.shade100,
                  width: 1,
                ),
              ),
              enabledBorder: OutlineInputBorder(
                borderSide: BorderSide(
                  color: Colors.blueGrey.shade200,
                  width: 1,
                ),
              ),
              disabledBorder: const OutlineInputBorder(
                borderSide: BorderSide.none,
              ),
              errorBorder: const OutlineInputBorder(
                borderSide: BorderSide(
                  color: Colors.red,
                  width: 1,
                ),
              ),
              fillColor: Colors.grey.shade200,
              filled: !isEnabled,
            ),
          ),
        ),
      ],
    );
  }
}
