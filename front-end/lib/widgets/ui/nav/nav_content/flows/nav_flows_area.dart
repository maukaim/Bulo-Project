import 'dart:math';

import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/domains/flows/providers.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_content/flows/providers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'flow_list_item.dart';

class NavFlowsArea extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var currentServerDomain = ref.watch(currentServerDomainProvider);
    var availableFlows = ref.watch(flowsAvailableProvider(currentServerDomain));

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
            hoverColor: Colors.black26.withOpacity(0.2),
            onTap: () {
              var currentServerDomain = ref.watch(currentServerDomainProvider);
              ref.refresh(flowsAvailableProvider(currentServerDomain)).whenData((value) =>
                  ref.read(selectedFlowProvider.notifier).state = null);

              print("Refresh button tapped!"); // Add your refresh function here
            },
            child: const Padding(
              padding: EdgeInsets.symmetric(vertical: 4, horizontal: 4),
              child: Icon(
                CupertinoIcons.refresh,
                size: 16,
                color: Colors.black26,
              ),
            ),
          ),
        ],
      ),
      const Divider(
        color: Color.fromRGBO(150, 150, 150, 0.5), // Color of the divider
      ),
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
