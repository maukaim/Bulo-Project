
import 'dart:io';

import 'package:flutter/foundation.dart';

System currentSystem = getCurrentSystem();

System getCurrentSystem(){
  if(kIsWeb){
    return System.web;
  }

  if(Platform.isMacOS){
    return System.macOS;
  }else if (Platform.isWindows){
    return System.windows;
  }else{
    return System.unsupported;
  }
}

enum System{
  macOS(isDesktop: true),
  windows(isDesktop: true),
  web,
  unsupported;

  final bool isDesktop;

  const System({this.isDesktop = false});
}