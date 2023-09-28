# Bundles for Mac OS

This folder helps to manage the embeded resources that could be added to different versions of the App.

## resources Vs Templates folders

**The resources**' folder is the one marked as embedded in XCode (See Runner.xcodeproj > project.pbxproj). 
Therefore, everything inside will be added to the built app.
By default there is nothing inside this folder, except a .gitignore to avoid bad committing. The build scripts (../release folder)
are aware of this and should move everything they need from templates to resources when they build their version of the app.

**The templates**' folder is where we store all the files to be potentially embedded. They are moved in resources during the release
build process. When some parts are too big (i.e, Java JDKs) there is a specific mechanism to add them. Note that templates/.gitignore is
excluding a jdks folder. If it is the 1st time you consider building the .pkg you may need to follow the steps in the next section.


## Having JDKs Locally

Some version of the app (i.e Community Edition, as of Aug. 2023) have Java embedded and will require you have the jdks folder setup.

To do so, go in your terminal, navigate to the templates folder, and from there run:
```shell
 git lfs clone git@github.com:maukaim/bulo-ui-jdks.git jdks
```
For this clone to work, you will need git lfs. The reaosn is that JDKs usually have long files (>100MB).
in newer git version, a simple git clone is enough, and will handle lfs if any.

Once the project cloned, go in the jdsk folder do `git lfs install` and follow with `git lfs pull` .


## Bonus: How to get git lfs locally  

You should download git lfs from the official website: https://git-lfs.com/
Once done, in order to clone a repository having lfs files, you just need to run first `git lfs install` .
You are now ready to run git lfs clone seen in previous section.