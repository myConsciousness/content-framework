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

import org.thinkit.framework.content.entity.ContentEntity;

import lombok.NonNull;

/**
 * The content-initiating class that makes a secure call to the concrete content
 * class that implements the {@link Content} interface.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class ContentInvoker<R extends ContentEntity> implements Invokable<R> {

    /**
     * The content
     */
    private Content<R> content;

    /**
     * Default constructor
     */
    private ContentInvoker() {
    }

    /**
     * Constructor
     *
     * @param content The content
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private ContentInvoker(@NonNull Content<R> content) {
        this.content = content;
    }

    /**
     * Returns the new instance of the {@link ContentInvoker} class based on the
     * {@code Content} object passed as an argument.
     *
     * @param <R>     Data types to return
     * @param content The content
     * @return the new instance of the {@link ContentInvoker} class based on the
     *         {@code Content} object passed as an argument
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    public static <R extends ContentEntity> ContentInvoker<R> of(@NonNull Content<R> content) {
        return new ContentInvoker<>(content);
    }

    @Override
    public R invoke() {
        try {
            return content.execute();
        } catch (Exception e) {
            throw new ContentHandlingException(e);
        }
    }
}
