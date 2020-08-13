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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * {@link Key} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class KeyTest {

    /**
     * 英名の期待値
     */
    private static final String EXPECTED_ENGLISH_SEQUENCE = "testKey";

    /**
     * 和名の期待値
     */
    private static final String EXPECTED_JAPANAESE_SEQUENCE = "テストキー";

    /**
     * テスト用の具象Enumクラスです。
     */
    private enum TestKey implements Key {

        /**
         * テスト用キー要素 (英名)
         */
        TEST_KEY(Key.testKey),

        /**
         * テスト用キー要素 (和名)
         */
        テストキー(Key.テストキー);

        /**
         * キー
         */
        private final Key key;

        /**
         * テスト用コンストラクタ
         *
         * @param key キー
         */
        TestKey(Key key) {
            this.key = key;
        }

        /**
         * テスト用キー
         */
        private enum Key {
            testKey, テストキー;
        }

        @Override
        public String getKey() {
            return this.key.name();
        }
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Key} インターフェースの {@link Key#getKey()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestKey} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestKey#TEST_KEY#getKey()} の返却値が <code>"testKey"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInEnglish() {
        assertEquals(EXPECTED_ENGLISH_SEQUENCE, TestKey.TEST_KEY.getKey());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Key} インターフェースの {@link Key#getKey()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestKey} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestKey#テストキー#getKey()} の返却値が <code>"テストキー"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInJapanese() {
        assertEquals(EXPECTED_JAPANAESE_SEQUENCE, TestKey.テストキー.getKey());
    }
}