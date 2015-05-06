# gradle-slack-plugin

Gradle plugin to send Slack messages according to your build lifecycle. Useful to integrate with a CI server, to notify everyone that some gradle task has failed.

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

## Credits
