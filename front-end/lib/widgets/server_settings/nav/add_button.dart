import 'package:bulo_ui/widgets/global/buttons/basic_button.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ServerConfigAddButton extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Center(
      child: CustomButton(
          padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
          hoverColor: Colors.blueGrey.withOpacity(0.075),
          onPressed: () {
            print("Hehe on a click sur add");
          },
          child: const Row(
            children: [
              Padding(
                padding: EdgeInsets.only(right: 4.0),
                child: Icon(
                  Icons.add,
                  size: 16,
                  weight: 800,
                  // color: Colors.blueGrey,
                ),
              ),
              Flexible(
                child: Text(
                  "Add Server",
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(color: Colors.grey, fontSize: 12),
                ),
              ),
            ],
          )),
    );
  }
}
