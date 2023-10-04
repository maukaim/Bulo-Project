import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

const myEventChannel = EventChannel('com.maukaim.bulo/full_screen_state');

final fullscreenStatusProvider = StreamProvider<bool>((ref) {
  return myEventChannel.receiveBroadcastStream().map((event) => event as bool);
});