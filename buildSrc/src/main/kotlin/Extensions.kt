import org.gradle.api.Project

inline val Project.deps: Map<String, String>
    get() = this.properties["libraries"] as Map<String, String>
