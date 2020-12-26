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

package org.thinkit.framework.content.rule;

/**
 * An interface that abstracts the rules of content.
 * <p>
 * When implementing the {@link Rule} interface, please specify the type of the
 * value returned by the {@link Rule#execute()} method as a generic type.
 *
 * <pre>
 * If a concrete rule object returns String result:
 * <code>
 * public class TestRule implements Rule&lt;String&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @deprecated This class was provided as a layer to process values retrieved
 *             from content, but it was deprecated due to its limited use and
 *             redundancy.
 * @author Kato Shinya
 * @since 1.0.0
 */
@Deprecated
public interface Rule<R> {

    /**
     * Search the content data and return as a result the data of the type specified
     * in the generics.
     * <p>
     * This method returns the value of the generic type that you defined when
     * declaring the {@link Rule} interface.
     *
     * @return The value of the generic type defined when the {@link Rule} interface
     *         was declared.
     */
    public R execute();
}
