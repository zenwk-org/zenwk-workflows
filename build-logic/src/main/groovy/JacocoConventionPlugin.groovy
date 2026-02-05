import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.api.tasks.testing.Test

class JacocoConventionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        // Aplica el plugin oficial de Jacoco
        project.pluginManager.apply(JacocoPlugin)

        // Configuración de Jacoco
        project.jacoco {
            toolVersion = "0.8.11"
        }

        // Configuración de tareas Test
        project.tasks.withType(Test).configureEach {
            useJUnitPlatform()
            finalizedBy("jacocoTestReport")
        }

        // Configuración del reporte
        project.tasks.named("jacocoTestReport", JacocoReport).configure { JacocoReport report ->

            report.dependsOn(project.tasks.named("test"))

            report.reports {
                xml.required.set(true)
                html.required.set(true)
                xml.outputLocation.set(
                        project.layout.buildDirectory.file(
                                "reports/jacoco/test/jacocoTestReport.xml"
                        )
                )
            }

            def exclusions = [
                    '**/config/**',
                    '**/dto/**',
                    '**/common/component/**',
                    '**/common/helper/**',
                    '**/common/validation/**',
                    '**/common/exception/**',
                    '**/controller/**',
                    '**/message*/**',
                    '**/enums/**',
                    '**/exception/**',
                    '**/entity/**',
                    '**/model/**',
                    '**/*ZenwkApplication*',
                    '**/*ApplicationKt*',
                    '**/util/**',
                    '**/constants/**'
            ]

            report.classDirectories.setFrom(
                    project.files(
                            report.classDirectories.files.collect { dir ->
                                project.fileTree(dir: dir, exclude: exclusions)
                            }
                    )
            )
        }
    }
}