// import 'package:flutter/cupertino.dart';

import 'package:bulo_ui/domains/flows/model/flow.dart' as fl;
import 'package:bulo_ui/widgets/global/extensions/neumorphic_extension.dart';
import 'package:bulo_ui/widgets/global/icons/fonticons/bulo_logo_icon_icons.dart';
import 'package:bulo_ui/widgets/ui/content/flow/providers.dart';
import 'package:bulo_ui/widgets/ui/nav/nav_content/flows/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class FlowListItem extends ConsumerWidget {
  final fl.Flow flow;

  FlowListItem(this.flow, {super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    String? flowIdSelected = ref.watch(selectedFlowProvider);
    Widget card = myNormalFlowCard(ref);
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6),
      child: flowIdSelected != flow.flowId
          ? card
          : card.neumorphicPressed(
              borderRadius: BorderRadius.circular(6),
            ),
    );
  }

  Widget myNormalFlowCard(WidgetRef ref) {
    return InkWell(
      borderRadius: BorderRadius.circular(6),
      // rounded corners
      hoverColor: Colors.blueGrey.withOpacity(0.075),
      // hover color with transparency
      onTap: () {
        var previouslySelectedFlowId = ref.read(selectedFlowProvider);
        ref.read(selectedFlowProvider.notifier).state = flow.flowId;

        if (previouslySelectedFlowId != flow.flowId) {
          ref.read(isOnRunModeProvider.notifier).state = true;
        }
      },
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 16),
        child: Row(
          children: [
            const Padding(
              padding: EdgeInsets.symmetric(vertical: 0, horizontal: 4),
              child: Icon(
                size: 12,
                BuloLogoIcon.bulo_logo_alpha,
                color: Colors.cyanAccent,
              ),
            ),
            Flexible(
              child: Text(
                "Hehe ${flow.flowId}",
                overflow: TextOverflow.ellipsis,
                style: const TextStyle(
                    color: Colors.blueGrey,
                    fontWeight: FontWeight.bold,
                    fontSize: 12),
              ),
            )
          ],
        ),
      ),
    );
  }
}
