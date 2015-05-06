package org.mindera.gradle.slack

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.tasks.TaskState
import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackAttachment
import net.gpedro.integrations.slack.SlackField
import net.gpedro.integrations.slack.SlackMessage
import org.mindera.gradle.slack.utils.GitUtils

/**
 * Created by joaoprudencio on 05/05/15.
 */
class SlackPlugin implements Plugin<Project> {

    SlackPluginExtension mExtension
    Project mProject

    void apply(Project project) {
        mProject = project
        mExtension = project.extensions.create('slack', SlackPluginExtension)

        project.afterEvaluate {
            if (mExtension.url != null)
                monitorTasksLifecyle(project)
        }
    }

    void monitorTasksLifecyle(Project project) {
        project.getGradle().getTaskGraph().addTaskExecutionListener(new TaskExecutionListener() {
            @Override
            void beforeExecute(Task task) {}

            @Override
            void afterExecute(Task task, TaskState state) {
                boolean shouldSendMessage = state.getFailure() != null || shouldMonitorTask(task);
                boolean success = state.getFailure() == null

                if (shouldSendMessage)
                    sendFormattedSlackMessage(task.getName(), task.getDescription(), success, mExtension.url)
            }
        })
    }

    boolean shouldMonitorTask(Task task) {
        for (dependentTask in mExtension.dependsOnTasks) {
            if (task.getName().equals(dependentTask)) {
                return true
            }
        }
        return false
    }

    void sendFormattedSlackMessage(String taskName, String taskDescription, boolean success, String url) {
        SlackApi api = new SlackApi(url);
        SlackMessage slackMessage = new SlackMessage("Gradle build finished")

        SlackAttachment attachments = new SlackAttachment()
        attachments.setColor(success ? 'good' : 'danger')
        String message = taskDescription
        attachments.setText(message)
        attachments.setFallback(message)

        SlackField taskField = new SlackField()
        taskField.setTitle("Task")
        taskField.setValue(taskName)
        taskField.setShorten(true)
        attachments.addFields(taskField)

        SlackField resultField = new SlackField()
        resultField.setTitle("Task Result")
        resultField.setValue(success ? 'Passed' : 'Failed')
        resultField.setShorten(true)
        attachments.addFields(resultField)

        SlackField branchField = new SlackField()
        branchField.setTitle("Git Branch")
        branchField.setValue(GitUtils.branchName())
        branchField.setShorten(true)
        attachments.addFields(branchField)

        SlackField authorField = new SlackField()
        authorField.setTitle("Git Author")
        authorField.setValue(GitUtils.lastCommitAuthor())
        authorField.setShorten(true)
        attachments.addFields(authorField)

        SlackField commitField = new SlackField()
        commitField.setTitle("Git Commit")
        commitField.setValue(GitUtils.lastCommitMessage())
        commitField.setShorten(true)
        attachments.addFields(commitField)

        slackMessage.addAttachments(attachments)

        api.call(slackMessage);
    }
}
