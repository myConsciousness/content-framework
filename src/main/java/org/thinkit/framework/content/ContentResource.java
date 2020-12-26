/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.framework.content;

/**
 * The interface is an abstraction of content resources.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface ContentResource {

    /**
     * Returns the relative path to the content file.
     *
     * @return The relative path to the content file.
     */
    public String getPath();
}
