import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

apply(from = "../dependencies.gradle")

inline val Project.deps: Map<String, String>
    get() = this.properties["libraries"] as Map<String, String>

dependencies {
    implementation(deps.getValue("kgp"))
    implementation(deps.getValue("agp"))
}
