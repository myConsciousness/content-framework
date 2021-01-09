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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.base.precondition.Preconditions;
import org.thinkit.common.util.FluentStreamReader;
import org.thinkit.common.util.json.JsonConverter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The class that defines the process of loading the content data based on the
 * specified content definition.
 * <p>
 * It provides a {@link #load(InputStream, Set)} method to load content data
 * without conditions, and a {@link #load(InputStream, Set, List)} method to
 * load content data with conditions.
 * <p>
 * If the value of {@code "conditionId"} defined in the content is an empty
 * string, the record will be loaded unconditionally. If you have defined a
 * value for the conditionId of the content, be sure to define a condition for
 * the content and call {@link #load(InputStream, Set, List)}.
 *
 * <pre>
 * If the condition is not specified:
 * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes);</code>
 * </pre>
 *
 * <pre>
 * If the condition is specified:
 * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes, conditions);</code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContentLoader {

    /**
     * Gets each element defined in the content file specified as an argument and
     * return it as List.
     * <p>
     * Use this {@link ContentLoader#load(InputStream, Set)} method if there are no
     * fetch conditions in the content definition.
     *
     * <pre>
     * Get the content data without conditions:
     * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes);</code>
     * </pre>
     *
     * @param contentStream The stream of content file
     * @param attributes    The Attribute names to be acquired
     * @return The List containing the elements retrieved from the content file
     *
     * @exception NullPointerException     If {@code null} is passed as an argument
     * @exception IllegalArgumentException If the attribute list is empty
     */
    public static List<Map<String, String>> load(@NonNull final InputStream contentStream,
            @NonNull final Set<String> attributes) {
        Preconditions.requireNonEmpty(attributes);

        return load(contentStream, attributes, new ArrayList<>(0));
    }

    /**
     * Gets each element defined in the content file specified and return it as a
     * list.
     * <p>
     * Use this {@link ContentLoader#load(InputStream, Set, List)} method if there
     * are no acquisition conditions in the content definition.
     *
     * <pre>
     * Get the content data with conditions:
     * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes, conditions);</code>
     * </pre>
     *
     * @param contentStream The stream of content file
     * @param attributes    The Attribute names to be acquired
     * @param conditions    The conditional list to use when getting data from the
     *                      content file
     * @return The List containing the elements retrieved from the content file
     *
     * @exception NullPointerException     If {@code null} is passed as an argument
     * @exception IllegalArgumentException If the attribute list is empty
     */
    public static List<Map<String, String>> load(@NonNull final InputStream contentStream,
            @NonNull Set<String> attributes, @NonNull final List<Map<String, String>> conditions) {
        Preconditions.requireNonEmpty(attributes);

        final Map<String, Object> rawContent = getContent(contentStream);
        final List<Map<String, Object>> conditionNodes = getNodeList(rawContent, ConditionNodeKey.CONDITION_NODES);

        final List<String> conditionIdList = conditionNodes.isEmpty() ? new ArrayList<>(0)
                : getConditionIdList(conditionNodes, conditions);

        return getContentList(attributes, rawContent, conditionIdList);
    }

    /**
     * Returns the list of nodes associated with the specified {@link Key} from the
     * content map.
     * <p>
     * Because it's impossible to avoid warnings when casting with generics, we
     * specify {@link SuppressWarnings} with {@code "unchecked"} to this
     * {@link #getNodeList(Map, Key)} method.
     *
     * @param content    The content map
     * @param contentKey The content key
     * @return A list of nodes associated with {@link Key}
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getNodeList(@NonNull Map<String, Object> content,
            @NonNull Key contentKey) {
        return (List<Map<String, Object>>) content.get(contentKey.getKey());
    }

    /**
     * Returns the node map associated with the specified {@link Key} from the
     * content map.
     * <p>
     * Because it's impossible to avoid warnings when casting with generics, we
     * specify {@link SuppressWarnings} with {@code "unchecked"} to this
     * {@link #getNodeMap(Map, Key)} method.
     *
     * @param content    The content map
     * @param contentKey The content key
     * @return A list of nodes associated with {@link Key}
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> getNodeMap(@NonNull Map<String, Object> content, @NonNull Key contentKey) {
        return (Map<String, Object>) content.get(contentKey.getKey());
    }

    /**
     * Returns a value of type string based on the content key specified as an
     * argument from the node map.
     *
     * @param nodeMap    The node map
     * @param contentKey The content key
     * @return A String value tied to a content key stored in the node map
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private static String getString(@NonNull Map<String, Object> nodeMap, @NonNull Key contentKey) {
        return getString(nodeMap, contentKey.getKey());
    }

    /**
     * Returns a value of type string based on the content key specified as an
     * argument from the node map.
     *
     * @param nodeMap    The node map
     * @param contentKey The content key
     * @return A String value tied to a content key stored in the node map
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private static String getString(@NonNull Map<String, Object> nodeMap, @NonNull String contentKey) {
        return (String) nodeMap.get(contentKey);
    }

    /**
     * Returns content data from the input stream of content file.
     *
     * @param contentStream The stream of content file
     * @return The content data from the input stream of content
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private static Map<String, Object> getContent(@NonNull final InputStream contentStream) {
        return JsonConverter.toLinkedHashMap(FluentStreamReader.of(contentStream).toString());
    }

    /**
     * Returns the content list based on the information passed as arguments.
     * <p>
     * A record with a condition ID in the content definition will be fetched only
     * if it matches the condition defined in the content. If there is no condition
     * ID in the content definition, the record will be fetched unconditionally.
     *
     * @param attributes      The list of keys associated to the values to be
     *                        fetched from the content
     * @param rawContent      The unprocessed content objects
     * @param conditionIdList A list containing the condition ID to be fetched
     * @return The content list
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private static List<Map<String, String>> getContentList(@NonNull Set<String> attributes,
            @NonNull Map<String, Object> rawContent, @NonNull List<String> conditionIdList) {

        final List<Map<String, String>> contentList = new ArrayList<>(0);
        final List<Map<String, Object>> selectionNodes = getNodeList(rawContent, SelectionNodeKey.SELECTION_NODES);

        for (Map<String, Object> nodeList : selectionNodes) {
            final Map<String, Object> nodeMap = getNodeMap(nodeList, SelectionNodeKey.NODE);
            final String conditionId = getString(nodeMap, SelectionNodeKey.CONDITION_ID);

            if (!StringUtils.isEmpty(conditionId) && !conditionIdList.contains(conditionId)) {
                continue;
            }

            final Map<String, String> content = new HashMap<>(0);

            for (String attribute : attributes) {
                content.put(attribute, getString(nodeMap, attribute));
            }

            contentList.add(content);
        }

        return contentList;
    }

    /**
     * Gets the condition ID used to load the content and returns it as a list.
     *
     * @param conditionNodes The list containing the conditional nodes
     * @param conditions     The conditional map to use when matching conditions
     * @return The List of condition IDs obtained as a result of matching
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private static List<String> getConditionIdList(@NonNull List<Map<String, Object>> conditionNodes,
            @NonNull List<Map<String, String>> conditions) {

        final List<String> conditionIdList = new ArrayList<>(0);

        conditions.forEach(condition -> {
            conditionNodes.forEach(nodeList -> {

                final Map<String, Object> nodeMap = getNodeMap(nodeList, ConditionNodeKey.NODE);
                final List<Map<String, Object>> conditionList = getNodeList(nodeMap, ConditionNodeKey.CONDITIONS);

                if (all(conditionList, condition)) {
                    conditionIdList.add(getString(nodeMap, ConditionNodeKey.CONDITION_ID));
                }
            });
        });

        return conditionIdList;

    }

    /**
     * Tests the conditions defined in the content against those passed to
     * {@link #load(InputStream, Set, List)} and determine if all conditions are
     * met.
     *
     * @param contentConditionList The List of conditions defined in the content
     * @param conditions           The conditional map to use when matching
     *                             conditions
     * @return If all conditions are met {@code true} , otherwise {@code false}
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private static boolean all(@NonNull List<Map<String, Object>> contentConditionList,
            @NonNull Map<String, String> conditions) {

        final Set<Entry<String, String>> entrySet = conditions.entrySet();

        for (Map<String, Object> contentCondition : contentConditionList) {
            final String keyName = getString(contentCondition, ConditionNodeKey.KEY_NAME);
            final String value = getString(contentCondition, ConditionNodeKey.OPERAND);

            for (Entry<String, String> entry : entrySet) {
                if (Objects.equals(keyName, entry.getKey()) && !Objects.equals(value, entry.getValue())) {
                    return false;
                }
            }
        }

        return true;
    }
}
