import 'package:bulo_ui/core/connect/model/server_config.dart';
import 'package:bulo_ui/core/log/model/log.dart';
import 'package:bulo_ui/core/log/providers.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class LogsArea extends ConsumerWidget {
  final ServerConfig serverConfig;

  LogsArea(this.serverConfig);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final logsAsyncValue = ref.watch(logsStreamProvider(serverConfig));

    return Container(
      decoration: BoxDecoration(
          color: Color(0xFF262525),
          borderRadius: BorderRadius.circular(8),
          border: Border.all(
            color: Colors.blueGrey.withOpacity(0.4),
            width: 2,
          )),
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          mainAxisSize: MainAxisSize.max,
          children: [
            Flexible(
              child: Row(
                mainAxisSize: MainAxisSize.max,
                children: [
                  logsAsyncValue.when(
                    data: (logs) {
                      print("youhoy");
                      return Flexible(
                        child: SelectableText.rich(
                          TextSpan(
                            children: logs.map((log) {
                              return (TextSpan(
                                children: logToTextSpanList(log),
                                style:
                                getSharedTextStyle(isError: log.isError()),
                              ));
                            }).toList(),
                          ),
                          style: TextStyle(overflow: TextOverflow.clip),
                          textWidthBasis: TextWidthBasis.longestLine,
                          showCursor: true,
                          autofocus: true,
                          cursorWidth: 1.0,
                          cursorColor: Colors.red,
                          selectionControls:
                          DesktopTextSelectionControls(), // Assuming you have this custom control.
                        ),
                      );
                    },
                    loading: () =>
                        Row(
                          children: [
                            Text("Waiting for logs....",
                                style: getSharedTextStyle())
                          ],
                        ),
                    error: (error, stack) =>
                        Row(
                          children: [
                            Text(
                              "Error, can't print logs: $error",
                              style: getSharedTextStyle(),
                            ),
                          ],
                        ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  TextStyle getSharedTextStyle({bool isError = false}) {
    return TextStyle(
      color: isError ? Colors.black : Colors.white,
      fontSize: 10,
      backgroundColor:
      isError ? Colors.red.withOpacity(0.8) : Colors.transparent,
    );
  }

  List<TextSpan> logToTextSpanList(Log log) {
    return [
      TextSpan(
        text: "${log.timeStr}",
        style: TextStyle(color: Colors.green),
      ),
      TextSpan(text: "  "),
      TextSpan(
        text: "${log.logLevel.asPrintable}",
        style: TextStyle(color: log.color),
      ),
      TextSpan(text: "  "),
      TextSpan(text: "${log.msg}"),
      TextSpan(text: "\n"),
    ];
  }
}
