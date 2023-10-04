import Cocoa
import FlutterMacOS
import Foundation

@NSApplicationMain
class AppDelegate: FlutterAppDelegate{
    var EVENT_CHANNEL_FULLSCREEN_STATE = "com.maukaim.bulo/full_screen_state";
    var METHOD_CHANNEL_EMBEDDED_SERVER = "com.maukaim.bulo/hasEmbeddedServer";

    var javaProcess: Process?
    var hasServerEmbedded: Bool = false
    var isFullScreen: Bool = false
    var eventSink: FlutterEventSink?
    var methodChannel: FlutterMethodChannel?;
    
    override func applicationShouldTerminateAfterLastWindowClosed(_ sender: NSApplication) -> Bool {
        return true
    }
    
    override func applicationDidFinishLaunching(_ aNotification: Notification) {
        setFullScreenStateEventChannel()
        startEmbeddedServer()
    }
    
    func setFullScreenStateEventChannel(){
        self.mainFlutterWindow.delegate = self
        
        let controller: FlutterViewController = getController();
        let fullscreenChannel = FlutterEventChannel(name: EVENT_CHANNEL_FULLSCREEN_STATE, binaryMessenger: controller.engine.binaryMessenger)
        
        fullscreenChannel.setStreamHandler(self)
    }
    
    func startEmbeddedServer() {
        let controller: FlutterViewController = getController();
        self.methodChannel = FlutterMethodChannel(name: METHOD_CHANNEL_EMBEDDED_SERVER, binaryMessenger: controller.engine.binaryMessenger)
        self.methodChannel?.setMethodCallHandler{ (call, result) in
            switch call.method {
            case "hasEmbeddedServer":
                result(self.hasServerEmbedded);
                return
            default:
                result(FlutterMethodNotImplemented)
            }
        }
        
        guard let jarFilePath = Bundle.main.path(forResource: "resources/application-standalone-1.0.0", ofType: "jar") else {
            print("No Embedded Server to start, we are in Light mode.")
            self.hasServerEmbedded = false
            return
        }
        self.hasServerEmbedded = true
        
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
    
    func getController() -> FlutterViewController {
        return self.mainFlutterWindow?.contentViewController as! FlutterViewController
    }

}

extension AppDelegate: FlutterStreamHandler {
    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        eventSink = events
        eventSink?(self.mainFlutterWindow.styleMask.contains(.fullScreen))
        return nil
    }
    
    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        eventSink = nil
        return nil
    }
}

extension AppDelegate: NSWindowDelegate {
    func windowDidEnterFullScreen(_ notification: Notification) {
        eventSink?(true)
    }
    
    func windowDidExitFullScreen(_ notification: Notification) {
        eventSink?(false)
    }
}
