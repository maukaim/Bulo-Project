
import 'package:bulo_ui/domains/flows/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../global/buttons/basic_button.dart';
import 'flow_list_item.dart';

class NavFlowsArea extends ConsumerWidget{
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var availableFlows = ref.watch(flowsAvailableProvider);

    return Column(children: [
      Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          const Flexible(
            child: Row(
              children: [
                Flexible(
                  child: Text(
                    'Flows Available',
                    overflow: TextOverflow.ellipsis,
                    style: TextStyle(
                        color: Colors.blueGrey,
                        fontWeight: FontWeight.bold,
                        fontSize: 14),
                  ),
                ),
              ],
            ),
          ),
          InkWell(
            borderRadius: BorderRadius.circular(4),
            // rounded corners
            hoverColor: Colors.blueGrey.withOpacity(0.3),
            onTap: () {
              ref.refresh(flowsAvailableProvider);
              print(
                  "Refresh button tapped!"); // Add your refresh function here
            },
            child: const Padding(
              padding: EdgeInsets.symmetric(vertical: 4, horizontal: 4),
              child: Icon(
                CupertinoIcons.refresh,
                size: 16,
                color: Colors.blueGrey,
              ),
            ),
          ),
        ],
      ),
      const Divider(
        color: Color.fromRGBO(150, 150, 150, 0.5), // Color of the divider
      ),
      CustomButton(
        color: Colors.blueGrey,
          onPressed: ()=> print("hihi"), child: Text("hihi")),
      SingleChildScrollView(
        child: availableFlows.when(
            data: (flows) => ListView.builder(
                shrinkWrap: true,
                itemCount: flows.length,
                itemBuilder: (ctx, index) => FlowListItem(flows[index])),
            error: (error, stack) => Text('Error: $error'),
            loading: () => const CircularProgressIndicator()),
      ),
    ]);
  }
}