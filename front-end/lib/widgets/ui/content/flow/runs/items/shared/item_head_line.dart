import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class ItemHeadLine extends StatelessWidget {
  final String titleText;
  final Widget statusBadge;
  final Duration? duration;
  final DateTime? endTime;

  const ItemHeadLine(
      {super.key,
      required this.titleText,
      required this.statusBadge,
      required this.duration,
      required this.endTime});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Text(
                titleText,
                style: const TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 14,
                ),
              ),
              const SizedBox(
                width: 8,
              ),
              statusBadge,
            ],
          ),
          Row(
            children: [
              Tooltip(
                message: "Duration",
                child: Text(
                  duration == null ? "-" : "${duration!.inSeconds}s",
                  style: const TextStyle(
                      fontSize: 12,
                      fontStyle: FontStyle.italic,
                      fontWeight: FontWeight.bold,
                      color: Colors.grey),
                ),
              ),
              const SizedBox(
                width: 8,
              ),
              Tooltip(
                message: "End time",
                child: Text(
                  endTime == null
                      ? "-"
                      : "Ended at ${DateFormat('kk:mm:ss MM/dd/yyyy').format(endTime!.toLocal())}",
                  style: const TextStyle(
                      fontSize: 12,
                      fontStyle: FontStyle.italic,
                      fontWeight: FontWeight.bold,
                      color: Colors.grey),
                ),
              ),
            ],
          )
        ],
      ),
    );
  }
}
