import Cocoa
import FlutterMacOS
import Foundation

@NSApplicationMain
class AppDelegate: FlutterAppDelegate {
  var javaProcess: Process?
  var hasServerEmbedded: Bool = true

  override func applicationShouldTerminateAfterLastWindowClosed(_ sender: NSApplication) -> Bool {
    return true
  }

  override func applicationDidFinishLaunching(_ aNotification: Notification) {
      guard let embeddedAppPath = Bundle.main.path(forResource: "resources/application-standalone-1.0.0", ofType: "jar") else {
        print("No Embedded Server to start, we are in Light mode.")
        hasServerEmbedded = false
        return
      }
      startEmbeddedServer(embeddedAppPath)
  }
  func startEmbeddedServer(_ jarFilePath: String) {
      let jdkFolderName = "jdk"
      let javaPathInJdk = "Contents/Home/bin/java"

      var javaExecutablePath = "/usr/bin/java"
      if let resourcePath = Bundle.main.path(forResource: "resources/\(jdkFolderName)/\(javaPathInJdk)", ofType: nil) {
        javaExecutablePath = resourcePath
      }else {
        print("No Embedded Java JDK, will try and use the standard path on the Client Machine.")
      }

      let task = Process()
      task.executableURL = URL(fileURLWithPath: javaExecutablePath)
      task.arguments = ["-jar", jarFilePath]
      do {
          try task.run()
           javaProcess = task
      } catch {
          print("Error launching Java backend:", error)
      }
  }

  override func applicationWillTerminate(_ aNotification: Notification) {
      stopJavaBackend()
  }

  func stopJavaBackend() {
      javaProcess?.terminate()
      javaProcess = nil
  }
}
