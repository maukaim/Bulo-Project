import 'package:bulo_ui/widgets/server_settings/nav/items/server_config_items.dart';

////
import 'package:bulo_ui/core/connect/providers.dart';
import 'package:bulo_ui/domains/flows/providers.dart';
import 'package:bulo_ui/widgets/main_window/content/flow/dashborad/flow_information_item.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class FlowsDashboard extends ConsumerWidget{
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var asyncAvailableFlows = ref.watch(flowsAvailableProvider(getCurrentServerConnector(ref)));

    return asyncAvailableFlows.when(data: (flows) {
      print("On a ${flows.length} flows !");
      return ListView.builder(
        shrinkWrap: true,
        itemCount: flows.length,
        itemBuilder: (ctx, index) => FlowInformationItem(flow: flows[index]),
      );
    }, error: (error, stack) => Container(color: Colors.red, child: Text("ca marche pas les flows on un bug"),),
      loading: () => const CircularProgressIndicator(),
    );
  }

}


