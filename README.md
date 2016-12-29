# gradle-slack-plugin

Gradle plugin to send Slack messages according to your build lifecycle. Useful to integrate with a CI server, to notify everyone that some gradle task has failed.

![Build Passing](http://i.imgur.com/eIq9hp1.png)
![Build Failing](http://i.imgur.com/cgf5fHf.png)

## Usage

The plugin is available in [JitPack](https://jitpack.io/). Just add the following to your buildscript dependencies:

```groovy
buildscript {

    repositories {
    	....
        maven {
            url "https://jitpack.io"
        }
    }
    
    dependencies {
    	...
        classpath 'com.github.Mindera:gradle-slack-plugin:1.0.7'
    }
}
```

Apply it:

```groovy
apply plugin: 'com.mindera.gradle.slack'
```

## Configuration 

First you need to setup slack to receive incoming messages:

1. Go to *your_team*.slack.com/services/new/incoming-webhook
2. Press Add Incoming WebHooks Integration
3. Grab your WebHook URL

Then in your build.gradle file:
```groovy
slack {
    url 'your WebHook URL'
}
```

By default, everytime a build fails a slack message will be sent to the channel you configured. If a build succeeds nothing happens.

There are more optional fields that enable you to configure the slack integration:

```groovy
slack {
    url 'your WebHook URL'
    dependsOnTasks 'testDebug', 'publishApkRelease'
    title 'my app name'
    enabled = isCDMachine()
}
```

*	dependsOnTasks: let you specify a list of tasks that will trigger a message to slack, in case of error and success;
*	title: the title of the slack message, can be the name of your app for instance;
*	enabled: a boolean to define if the slack integration is active or not, useful to avoid sending messages on your local builds.


## Credits

[Slack WebHook Java API](https://github.com/gpedro/slack-webhook) by [gpedro](https://github.com/gpedro)

## License

gradle-slack-plugin is available under the MIT license. See the LICENSE file for more info.
