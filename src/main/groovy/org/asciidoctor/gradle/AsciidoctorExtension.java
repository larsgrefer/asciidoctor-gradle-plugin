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

import org.gradle.api.Project;

/**
 * @author Andres Almiray
 */
public class AsciidoctorExtension {
    private String version = "1.5.6";

    private String groovyDslVersion = "1.0.0.Alpha2";

    /**
     * By default the plugin will try to add a default repository to find AsciidoctorJ.
     * For certain cases this approach is not acceptable, the behaviour can be turned off
     * by setting this value to {@code true}
     *
     * @since 1.5.3
     */
    private boolean noDefaultRepositories = false;

    private final Project project;

    AsciidoctorExtension(Project project) {
        this.project = project;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroovyDslVersion() {
        return groovyDslVersion;
    }

    public void setGroovyDslVersion(String groovyDslVersion) {
        this.groovyDslVersion = groovyDslVersion;
    }

    public boolean isNoDefaultRepositories() {
        return noDefaultRepositories;
    }

    public void setNoDefaultRepositories(boolean noDefaultRepositories) {
        this.noDefaultRepositories = noDefaultRepositories;
    }

    public Project getProject() {
        return project;
    }
}
