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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.thinkit.common.Precondition;
import org.thinkit.common.catalog.Extension;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.framework.content.entity.ContentEntity;

import lombok.NonNull;

/**
 * コンテンツを抽象化したインターフェースです。
 * <p>
 * {@link Content} インターフェースを実装する際には総称型として {@link Content#execute()}
 * メソッドが返却する値の型を指定してください。
 *
 * <pre>
 * 使用例 (String型を返却する場合):
 * <code>
 * public class TestContent implements Content&lt;Something&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Content<R extends ContentEntity> {

    /**
     * コンテンツファイルに定義されているアトリビュート名を格納したリストを返却します。
     *
     * @return コンテンツファイルに定義されたアトリビュート名を格納したリスト
     * @see #getConditions()
     * @see #loadContent(Content)
     */
    public List<Attribute> getAttributes();

    /**
     * コンテンツファイルから情報を取得する際の条件を格納したマップを返却します。
     *
     * @return コンテンツファイルに定義されたレコード取得条件を格納したマップ
     * @see #loadContent(Content)
     */
    public Map<Condition, String> getConditions();

    /**
     * ルールを実行します。<br>
     * このメソッドは {@link Content} インターフェースの宣言時に定義した総称型の値を返却します。
     *
     * @return {@link Content} インターフェースの宣言時に定義した総称型の値
     */
    public R execute();

    /**
     * 引数として渡されたコンテンツオブジェクトに紐づくコンテンツファイルを参照しロード処理を行います。
     *
     * @param content コンテンツ
     * @return 引数として指定された {@code content} に紐づくコンテンツ情報を格納したマップ
     *
     * @see #getAttributes()
     * @see #getConditions()
     *
     * @exception NullPointerException 引数として指定された {@code content} が {@code null} の場合
     * @throws ContentHandlingException 実装された {@link #getAttributes()} メソッドの返却値が
     *                                  {@code null} の場合、または
     *                                  {@link #getAttributes()} メソッドの返却値が空リストの場合
     */
    default List<Map<String, String>> loadContent(@NonNull Class<? extends Content<R>> content) {

        final ContentMapping mapping = content.getAnnotation(ContentMapping.class);
        final List<Attribute> attributes = this.getAttributes();
        final Map<Condition, String> conditions = this.getConditions();

        Precondition.requireNonNull(mapping);
        Precondition.requireNonNull(attributes);
        Precondition.requireNonEmpty(attributes);

        try (InputStream is = content.getResourceAsStream(String.format("%s.%s", mapping.content(), Extension.json()));
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<Map<String, String>> contents = ContentLoader.load(mapping.content(),
                attributes.stream().map(Attribute::getString).collect(Collectors.toList()),
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
