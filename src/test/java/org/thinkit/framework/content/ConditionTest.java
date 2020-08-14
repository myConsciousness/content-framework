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
 * {@link Condition} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ConditionTest {

    /**
     * 英名の期待値
     */
    private static final String EXPECTED_ENGLISH_SEQUENCE = "TEST_CONDITION";

    /**
     * 和名の期待値
     */
    private static final String EXPECTED_JAPANAESE_SEQUENCE = "テストコンディション";

    /**
     * テスト用の具象Enumクラスです。
     */
    private enum TestCondition implements Condition {

        /**
         * テスト用コンディション要素 (英名)
         */
        TEST_CONDITION,

        /**
         * テスト用コンディション要素 (和名)
         */
        テストコンディション;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Condition} インターフェースの {@link Condition#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestCondition} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestCondition#TEST_CONDITION#getString()} の返却値が <code>"TEST_CONDITION"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInEnglish() {
        assertEquals(EXPECTED_ENGLISH_SEQUENCE, TestCondition.TEST_CONDITION.getString());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Condition} インターフェースの {@link Condition#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestCondition} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestCondition#テストコンディション#getString()} の返却値が <code>"テストコンディション"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInJapanese() {
        assertEquals(EXPECTED_JAPANAESE_SEQUENCE, TestCondition.テストコンディション.getString());
    }
}