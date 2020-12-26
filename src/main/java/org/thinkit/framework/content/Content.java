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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.thinkit.common.base.precondition.Preconditions;
import org.thinkit.common.catalog.Extension;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.framework.content.catalog.ContentRoot;
import org.thinkit.framework.content.entity.ContentEntity;

import lombok.NonNull;

/**
 * The interface that abstracts the content.
 * <p>
 * When implementing the {@link Content} interface, specify the type of the
 * value returned by the {@link Content#execute()} method as a generic type.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface Content<R extends ContentEntity> {

    /**
     * Returns the Set containing the name of the target attribute to be retrieved
     * from the content file.
     *
     * @return The Set containing the name of the target attribute to be retrieved
     *         from the content file
     */
    public Set<Attribute> getAttributes();

    /**
     * Returns the map containing the conditions for get the content.
     *
     * @return The map containing the conditions for get the content
     */
    public Map<Condition, String> getConditions();

    /**
     * Returns the data got from the content as the data type specified when
     * implementing the {@link Content} interface.
     *
     * @return The data got from the content as the data type specified when
     *         implementing the {@link Content} interface
     */
    public R execute();

    /**
     * Refers to the content file associated with the content object passed as an
     * argument and perform the loading process.
     *
     * @param content The content
     * @return A map containing the content data for the {@code content} specified
     *         as an argument
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     * @throws ContentHandlingException If the return value of the implemented
     *                                  {@link #getAttributes()} method is
     *                                  {@code null} , or if the return value of the
     *                                  {@link #getAttributes()} method is an empty
     *                                  list
     */
    default List<Map<String, String>> loadContent(@NonNull Content<R> content) {

        final Class<?> contentClass = content.getClass();
        final ContentMapping mapping = contentClass.getAnnotation(ContentMapping.class);
        final Set<Attribute> attributes = this.getAttributes();
        final Map<Condition, String> conditions = this.getConditions();

        Preconditions.requireNonNull(mapping);
        Preconditions.requireNonNull(attributes);
        Preconditions.requireNonEmpty(attributes);

        final List<Map<String, String>> contents = ContentLoader.load(
                contentClass.getClassLoader()
                        .getResourceAsStream(ContentRoot.ROOT.getTag() + mapping.content() + Extension.json()),
                attributes.stream().map(Attribute::getString).collect(Collectors.toSet()),
                conditions == null ? new HashMap<>(0)
                        : conditions.entrySet().stream()
                                .collect(Collectors.toMap(e -> e.getKey().getString(), e -> e.getValue())));

        if (contents.isEmpty()) {
            throw new ContentHandlingException(
                    "Could not get a value from the content. Please check the input information or implementation.");
        }

        return contents;
    }
}
