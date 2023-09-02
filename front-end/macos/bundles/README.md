#### Long story short, the folder resources is marked as Bundle Resource in XCode.  

```
See Runner.xcodeproj > project.pbxproj
```

It enables us to bundle any resources we want, useful when creating multiple versions of the App.
Use the folder "templates" to store the resources to be bundled in different versions of the application.

Unless doing tests, the bundles/resources folder should be kept empty.