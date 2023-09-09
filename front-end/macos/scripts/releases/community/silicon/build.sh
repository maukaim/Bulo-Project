#!/bin/bash
find macos/bundles/resources/ ! -name '.gitignore' -mindepth 1 -delete
cp -r macos/bundles/templates/jdk/silicon/jdk17 macos/bundles/resources/
cp -r macos/bundles/templates/application-standalone-* macos/bundles/resources/

# Rename the app across platforms
APP_NAME="Bulo Community Edition"
flutter pub global run rename --appname "${APP_NAME}" --target macOS

# Build the Flutter app for macOS
flutter build macos

# Package the app
pkgbuild --install-location /Applications --component "build/macos/Build/Products/Release/${APP_NAME}.app" "${APP_NAME}.pkg"

# Rename the .app file. Useful when Local testing of .pkg, avoid OS X to think the app is installed already.
mv "build/macos/Build/Products/Release/${APP_NAME}.app" "build/macos/Build/Products/Release/${APP_NAME}_packaged.app"