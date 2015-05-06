# gradle-slack-plugin

Gradle plugin to send Slack messages according to your build lifecycle. Useful to integrate with a CI server, to notify everyone that some gradle task has failed.

![Build Passing](http://i.imgur.com/eIq9hp1.png)
![Build Failing](http://i.imgur.com/cgf5fHf.png)

## Usage

Note that it's not yet available in Maven Central, but soon will be.

Add it to your buildscript dependencies:

```groovy
buildscript {

    repositories {
        mavenCentral()

        maven {
	        // Temporary maven repository, until it is not available in Maven Central
            url uri('http://joaoprudencio.com/m2/repository')
        }
    }
    
    dependencies {
        classpath 'org.mindera.gradle.slack:gradle-slack-plugin:1.0-SNAPSHOT'
    }
}
```

Apply it:

```groovy
apply plugin: 'org.mindera.gradle.slack'
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

If you also want to send messages on success for some tasks you set a list of tasks for that:
```groovy
slack {
    url 'your WebHook URL'
    dependsOnTasks 'testDebug', 'publishApkRelease'
}
```

## Credits

[Slack WebHook Java API](https://github.com/gpedro/slack-webhook) by [gpedro](https://github.com/gpedro)

## License

gradle-slack-plugin is available under the MIT license. See the LICENSE file for more info.