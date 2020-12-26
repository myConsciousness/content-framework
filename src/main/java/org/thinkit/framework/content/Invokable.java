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
 * The interface that abstracts the invokable objects.
 * <p>
 * When implementing the {@link Invokable} interface, please specify the type of
 * the value returned by the {@link Invokable#invoke()} method as a generic
 * type.
 *
 * <pre>
 * If the invokable object returns the String result:
 * <code>
 * public class TestInvoker implements Invokable&lt;String&gt; {
 *      * // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface Invokable<R> {

    /**
     * Invokes the processing of a concrete class that implements the
     * {@link Invokable#invoke()} method.
     * <p>
     * This method returns the value of the generic type defined when the
     * {@link Invokable} interface was declared.
     *
     * @return The value of the generic type defined when declaring the
     *         {@link Invokable} interface
     */
    public R invoke();
}
