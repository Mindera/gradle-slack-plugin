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
    }
    
    dependencies {
    	// ...
        classpath 'org.mindera.gradle.slack:gradle-slack-plugin:1.0-SNAPSHOT'
    }
}
```

Apply it:

```groovy
apply plugin: 'org.mindera.gradle.slack'
```

## Configuration 

You just need to setup your url for incoming webook in Slack:
```groovy
slack {
    url 'http://someurl'
}
```

## Author

Joao Prudencio, joao.prudencio@mindera.com

## Credits

[Slack WebHook Java API](https://github.com/gpedro/slack-webhook) by [gpedro](https://github.com/gpedro)

## License

gradle-slack-plugin is available under the MIT license. See the LICENSE file for more info.