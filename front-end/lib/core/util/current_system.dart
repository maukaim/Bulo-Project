
import 'dart:io';

System currentSystem = getCurrentSystem();

System getCurrentSystem(){
  if(Platform.isMacOS){
    return System.macOS;
  }else if (Platform.isWindows){
    return System.windows;
  }else{
    return System.unsupported;
  }
}

enum System{
  macOS,
  windows,
  unsupported

}