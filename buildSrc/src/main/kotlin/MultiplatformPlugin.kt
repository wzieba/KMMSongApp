import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

class MultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
//        https://youtrack.jetbrains.com/issue/KT-43944
            configurations {
                create("androidTestApi")
                create("androidTestDebugApi")
                create("androidTestReleaseApi")
                create("testApi")
                create("testDebugApi")
                create("testReleaseApi")
            }
        }
    }
}