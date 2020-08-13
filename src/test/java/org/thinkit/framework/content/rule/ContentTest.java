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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * {@link Content} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentTest {

    /**
     * 英名の期待値
     */
    private static final String EXPECTED_ENGLISH_SEQUENCE = "TEST_CONTENT";

    /**
     * 和名の期待値
     */
    private static final String EXPECTED_JAPANAESE_SEQUENCE = "テストコンテンツ";

    /**
     * テスト用の具象Enumクラスです。
     */
    private enum TestContent implements Content {

        /**
         * テスト用コンテンツ要素 : 英名
         */
        TEST_CONTENT,

        /**
         * テスト用コンテンツ要素 : 和名
         */
        テストコンテンツ;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Content} インターフェースの {@link Content#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestContent} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestContent#TEST_CONTENT#getString()} の返却値が <code>"TEST_CONTENT"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInEnglish() {
        assertEquals(EXPECTED_ENGLISH_SEQUENCE, TestContent.TEST_CONTENT.getString());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Content} インターフェースの {@link Content#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestContent} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestContent#テストコンテンツ#getString()} の返却値が <code>"テストコンテンツ"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInJapanese() {
        assertEquals(EXPECTED_JAPANAESE_SEQUENCE, TestContent.テストコンテンツ.getString());
    }
}