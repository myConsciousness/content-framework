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

import org.thinkit.framework.content.Invokable;

import lombok.NonNull;

/**
 * A rule invoking class that makes a safe call to the concrete rule class that
 * implements the {@link Rule} interface.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class RuleInvoker<R> implements Invokable<R> {

    /**
     * The content rule
     */
    private Rule<R> rule;

    /**
     * Default constructor
     */
    private RuleInvoker() {
    }

    /**
     * Constructor
     *
     * @param rule The content rule
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private RuleInvoker(@NonNull Rule<R> rule) {
        this.rule = rule;
    }

    /**
     * Returns the new instance of the {@link RuleInvoker} class based on the
     * {@code Rule} object specified as an argument.
     *
     * @param <R>  Data types to return
     * @param rule The content rule
     * @return The new instance of the {@link RuleInvoker} class based on the
     *         {@code Rule} object specified as an argument
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    public static <R> RuleInvoker<R> of(@NonNull Rule<R> rule) {
        return new RuleInvoker<>(rule);
    }

    @Override
    public R invoke() {
        try {
            return rule.execute();
        } catch (Exception e) {
            throw new RuleHandlingException(e);
        }
    }
}
