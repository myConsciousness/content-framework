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
 * The interface that defines the processing required by Enum constants to
 * represent the attributes.
 * <p>
 * Whenever you define an Enum constant for attributes, be sure to implement the
 * relevant interface.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface Attribute {

    /**
     * Returns the string representation of the Enum element.
     *
     * @return The string representation of the Enum element
     */
    public String getString();
}
