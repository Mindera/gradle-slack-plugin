package com.mindera.gradle.slack
/**
 * Created by joaoprudencio on 05/05/15.
 */
class SlackPluginExtension {
    String url
    List<Object> dependsOnTasks
    String title
    boolean enabled

    void dependsOnTasks(Object... paths) {
        this.dependsOnTasks = Arrays.asList(paths)
    }
}