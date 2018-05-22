/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asciidoctor.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.artifacts.dsl.DependencyHandler;

/**
 * @author Noam Tenne
 * @author Andres Almiray
 * @author Patrick Reimers
 * @author Markus Schlichting
 * @author Schalk W. Cronjé
 */
public class AsciidoctorPlugin implements Plugin<Project> {
    static final String ASCIIDOCTOR = "asciidoctor";
    static final String ASCIIDOCTORJ = "asciidoctorj";
    static final String ASCIIDOCTORJ_CORE_DEPENDENCY = "org.asciidoctor:asciidoctorj:";
    static final String ASCIIDOCTORJ_GROOVY_DSL_DEPENDENCY = "org.asciidoctor:asciidoctorj-groovy-dsl:";

    public void apply(Project project) {
        project.getPlugins().apply("base");

        AsciidoctorExtension extension = project.getExtensions().create(ASCIIDOCTORJ, AsciidoctorExtension.class, project);

        project.afterEvaluate(p -> {
            if(!extension.isNoDefaultRepositories()) {
                project.getRepositories().jcenter();
            }
        });

        Configuration configuration = project.getConfigurations().maybeCreate(ASCIIDOCTOR);
        project.getLogger().info("[Asciidoctor] asciidoctorj: {}", extension.getVersion());
        project.getLogger().info("[Asciidoctor] asciidoctorj-groovy-dsl: {}", extension.getGroovyDslVersion());


        configuration.getIncoming().beforeResolve(resolvableDependencies -> {
            DependencyHandler dependencyHandler = project.getDependencies();
            DependencySet dependencies = configuration.getDependencies();
            dependencies.add(dependencyHandler.create(ASCIIDOCTORJ_CORE_DEPENDENCY + extension.getVersion()));
            dependencies.add(dependencyHandler.create(ASCIIDOCTORJ_GROOVY_DSL_DEPENDENCY + extension.getGroovyDslVersion()));
        });

        project.getTasks().create(ASCIIDOCTOR, AsciidoctorTask.class, task -> {
            task.setGroup("Documentation");
            task.setDescription("Converts AsciiDoc files and copies the output files and related resources to the build directory.");
            task.setClasspath(configuration);
        });
    }
}
