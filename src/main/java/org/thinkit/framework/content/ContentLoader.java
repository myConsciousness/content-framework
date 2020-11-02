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
import org.thinkit.common.Preconditions;
import org.thinkit.common.util.FluentStreamReader;
import org.thinkit.common.util.json.JsonConverter;

import lombok.NonNull;

/**
 * 指定されたコンテンツ定義を基にコンテンツをロードする処理を定義したクラスです。<br>
 * 条件指定なしでコンテンツ情報をロードするための {@link #load(InputStream, List)} メソッドと、
 * 条件指定ありでコンテンツ情報をロードするための {@link #load(InputStream, List, Map)} メソッドを提供しています。
 * <p>
 * コンテンツに定義されたconditionIdの値が空文字列のレコードは無条件でロードされます。<br>
 * コンテンツのconditionIdに値を定義した場合は必ずコンテンツに条件を定義し
 * {@link #load(InputStream, List, Map)} を呼び出してください。
 *
 * <pre>
 * 条件指定なしの使用例:
 * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes);</code>
 * </pre>
 *
 * <pre>
 * 条件指定ありの使用例:
 * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes, conditions);</code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #load(InputStream, List)
 * @see #load(InputStream, List, Map)
 */
final class ContentLoader {

    /**
     * デフォルトコンストラクタ
     */
    private ContentLoader() {
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。<br>
     * コンテンツ定義に取得条件が存在しない場合はこの {@link ContentLoader#load(InputStream, List)}
     * メソッドを使用してください。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * <pre>
     * 使用例:
     * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes);</code>
     * </pre>
     *
     * @param contentStream コンテンツの入力ストリーム
     * @param attributes    定義体から取得する要素名
     * @return コンテンツファイルから取得した要素を格納した配列
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @throws IllegalArgumentException コンテンツ名が空文字列、またはアトリビュートリストが空の場合
     */
    public static List<Map<String, String>> load(@NonNull final InputStream contentStream,
            @NonNull final List<String> attributes) {
        Preconditions.requireNonEmpty(attributes);

        return load(contentStream, attributes, new HashMap<>(0));
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。<br>
     * コンテンツ定義に取得条件が存在しない場合はこの {@link ContentLoader#load(InputStream, List, Map)}
     * メソッドを使用してください。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * <pre>
     * 使用例:
     * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentStream, attributes, conditions);</code>
     * </pre>
     *
     * @param contentStream コンテンツの入力ストリーム
     * @param attributes    定義体から取得する要素名
     * @param conditions    取得条件
     * @return コンテンツファイルから取得した要素を格納した配列
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     * @throws IllegalArgumentException コンテンツ名が空文字列、またはアトリビュートリストが空の場合
     */
    public static List<Map<String, String>> load(@NonNull final InputStream contentStream,
            @NonNull List<String> attributes, @NonNull final Map<String, String> conditions) {
        Preconditions.requireNonEmpty(attributes);

        final Map<String, Object> rawContent = getContent(contentStream);
        final List<Map<String, Object>> conditionNodes = getNodeList(rawContent, ConditionNodeKey.CONDITION_NODES);

        final List<String> conditionIdList = conditionNodes.isEmpty() ? new ArrayList<>(0)
                : getConditionIdList(conditionNodes, conditions);

        return getContentList(attributes, rawContent, conditionIdList);
    }

    /**
     * コンテンツマップから指定された {@link Key} に紐づくノードリストを取得し返却します。
     * <p>
     * ジェネリクスを使用したキャスト処理の際には警告を避けられないため {@link SuppressWarnings} で
     * {@code "unchecked"} をこの {@link #getNodeList(Map, Key)} メソッドへ指定しています。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param content    コンテンツマップ
     * @param contentKey コンテンツキー
     * @return {@link Key} に紐づくノードリスト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getNodeList(@NonNull Map<String, Object> content,
            @NonNull Key contentKey) {
        return (List<Map<String, Object>>) content.get(contentKey.getKey());
    }

    /**
     * コンテンツマップから指定された {@link Key} に紐づくノードマップを取得し返却します。
     * <p>
     * ジェネリクスを使用したキャスト処理の際には警告を避けられないため {@link SuppressWarnings} で
     * {@code "unchecked"} をこの {@link #getNodeList(Map, Key)} メソッドへ指定しています。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param content    コンテンツマップ
     * @param contentKey コンテンツキー
     * @return {@link Key} に紐づくノードマップ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> getNodeMap(@NonNull Map<String, Object> content, @NonNull Key contentKey) {
        return (Map<String, Object>) content.get(contentKey.getKey());
    }

    /**
     * ノードマップから引数として指定されたコンテンツキーを基に文字列型の値を取得し返却します。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param nodeMap    ノードマップ
     * @param contentKey コンテンツキー
     * @return ノードマップに格納されたコンテンツキーに紐づく文字列型の値
     */
    private static String getString(@NonNull Map<String, Object> nodeMap, @NonNull Key contentKey) {
        return getString(nodeMap, contentKey.getKey());
    }

    /**
     * ノードマップから引数として指定されたコンテンツキーを基に文字列型の値を取得し返却します。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param nodeMap    ノードマップ
     * @param contentKey コンテンツキー
     * @return ノードマップに格納されたコンテンツキーに紐づく文字列型の値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private static String getString(@NonNull Map<String, Object> nodeMap, @NonNull String contentKey) {
        return (String) nodeMap.get(contentKey);
    }

    /**
     * コンテンツの入力ストリームからコンテンツ情報を取得し返却します。
     * <p>
     * コンテンツ情報は {@link JsonConverter} に定義されているメソッドを使用して変換を行っています。
     *
     * @param contentStream コンテンツの入力ストリーム
     * @return コンテンツマップ
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     *
     * @see JsonConverter
     */
    private static Map<String, Object> getContent(@NonNull final InputStream contentStream) {
        return JsonConverter.toLinkedHashMap(FluentStreamReader.of(contentStream).toString());
    }

    /**
     * 引数として渡された情報を基にコンテンツリストを生成し返却します。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     * <p>
     * コンテンツ定義に条件IDが設定されているレコードはコンテンツに定義された条件に合致する場合にのみ取得します。<br>
     * コンテンツ定義に条件IDが設定されていない（空文字列）の場合は無条件でレコードの取得を行います。
     *
     * @param attributes      コンテンツから取得する値に紐づくキー
     * @param rawContent      加工されていないコンテンツオブジェクト
     * @param conditionIdList 取得する対象の条件IDが格納されたリスト
     * @return コンテンツリスト
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private static List<Map<String, String>> getContentList(@NonNull List<String> attributes,
            @NonNull Map<String, Object> rawContent, @NonNull List<String> conditionIdList) {

        final List<Map<String, String>> contentList = new ArrayList<>(0);
        final List<Map<String, Object>> selectionNodes = getNodeList(rawContent, SelectionNodeKey.SELECTION_NODES);

        for (Map<String, Object> nodeList : selectionNodes) {
            final Map<String, Object> nodeMap = getNodeMap(nodeList, SelectionNodeKey.NODE);
            final Map<String, String> content = new HashMap<>(0);
            final String conditionId = getString(nodeMap, SelectionNodeKey.CONDITION_ID);

            if (!StringUtils.isEmpty(conditionId) && !conditionIdList.contains(conditionId)) {
                continue;
            }

            for (String attribute : attributes) {
                content.put(attribute, getString(nodeMap, attribute));
            }

            contentList.add(content);
        }

        return contentList;
    }

    /**
     * コンテンツをロードする際に使用する条件IDを取得しリストとして返却します。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param conditionNodes 条件ノードリスト
     * @param conditions     条件の照合時に使用する条件マップ
     * @return 条件IDのリスト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private static List<String> getConditionIdList(@NonNull List<Map<String, Object>> conditionNodes,
            @NonNull Map<String, String> conditions) {

        final List<String> conditionIdList = new ArrayList<>(0);

        for (Map<String, Object> nodeList : conditionNodes) {
            final Map<String, Object> nodeMap = getNodeMap(nodeList, ConditionNodeKey.NODE);
            final List<Map<String, Object>> conditionList = getNodeList(nodeMap, ConditionNodeKey.CONDITIONS);

            if (all(conditionList, conditions)) {
                conditionIdList.add(getString(nodeMap, ConditionNodeKey.CONDITION_ID));
            }
        }

        return conditionIdList;
    }

    /**
     * コンテンツに定義された条件と {@link #load(String, List, Map)} に渡された条件を照合し、
     * 全ての条件を満たしているか判定します。
     * <p>
     * 全ての条件を満たしている場合は {@code true} を返却し、それ以外の場合は {@code false} を返却します。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param contentConditionList コンテンツに定義された条件リスト
     * @param conditions           照合する値を格納したマップ
     * @return 全ての条件を満たしている場合は {@code true} 、それ以外は {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private static boolean all(@NonNull List<Map<String, Object>> contentConditionList,
            @NonNull Map<String, String> conditions) {

        final Set<Entry<String, String>> entrySet = conditions.entrySet();

        for (Map<String, Object> contentCondition : contentConditionList) {
            final String keyName = getString(contentCondition, ConditionNodeKey.KEY_NAME);
            final String value = getString(contentCondition, ConditionNodeKey.VALUE);

            for (Entry<String, String> entry : entrySet) {
                if (Objects.equals(keyName, entry.getKey()) && !Objects.equals(value, entry.getValue())) {
                    return false;
                }
            }
        }

        return true;
    }
}