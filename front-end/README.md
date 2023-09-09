# Bulo UI - Flutter Edition

Multiplatform interface for Bulo Project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://docs.flutter.dev/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://docs.flutter.dev/cookbook)

For help getting started with Flutter development, view the
[online documentation](https://docs.flutter.dev/), which offers tutorials,
samples, guidance on mobile development, and a full API reference.


## Initial conventions

- Separate UI component from others (lib/ui folder). 
- Separate business logic from core functioning classes (lib/domains)
- Have a specific folder to everything related to technical stuff as connectivity, marshalling, etc (lib/core)

Use riverpod as state management. all files with name providers.dart contains the providers (states, services, etc) of the classes inside this folder (i.e, folders flows, flow_runs, connect, etc..).
Nested providers.dart files could be found if the states it provides are used only in a restrained scope. (i.e ephemeral states, UI rendering toggles, etc...)


## Build & Pkg construction  

The simplest way to build a version of the Bulo UI is to use the scripts in macos/scripts.
Once the app bundle created, ones should run the pkgbuild command to create the easy to distribute application:
```shell
# For Community Edition
pkgbuild --install-location /Applications --component build/macos/Build/Products/Release/bulo-community-edition.app bulo-community-edition.pkg
```

If you want to change the app name you have to install the rename package:
```shell
flutter pub global activate rename  
```
and run the rename command:
```shell
flutter pub global run rename --appname "Bulo Community Edition" --target [macOS, windows, android, ios, linux, web]
```

[//]: # (TODO: Next is to add this rename thing in the scripts.)
[//]: # (Also should use this as variable and pass it to the different command as the .app will have this name too.)
