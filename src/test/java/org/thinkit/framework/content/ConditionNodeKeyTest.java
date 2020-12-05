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
 * {@link ConditionNodeKey} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ConditionNodeKeyTest {

    /**
     * キー名 : conditionNodes
     */
    private static final String KEY_CONDITION_NODES = "conditionNodes";

    /**
     * キー名 : node
     */
    private static final String KEY_NODE = "node";

    /**
     * キー名 : conditionId
     */
    private static final String KEY_CONDITION_ID = "conditionId";

    /**
     * キー名 : exclude
     */
    private static final String KEY_EXCLUDE = "exclude";

    /**
     * キー名 : conditions
     */
    private static final String KEY_CONDITIONS = "conditions";

    /**
     * キー名 : condition
     */
    private static final String KEY_CONDITION = "condition";

    /**
     * キー名 : keyName
     */
    private static final String KEY_KEY_NAME = "keyName";

    /**
     * キー名 : operator
     */
    private static final String KEY_OPERATOR = "operator";

    /**
     * キー名 : operand
     */
    private static final String KEY_OPERAND = "operand";

    /**
     * <pre>
     * ❏ 概要
     * {@link ConditionNodeKey} クラスの {@link ConditionNodeKey#getKey()} メソッドの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link ConditionNodeKey#CONDITION_NODES#getKey()} メソッドの返却値が <code>"conditionNodes"</code> であること
     * ・{@link ConditionNodeKey#NODE#getKey()} メソッドの返却値が <code>"node"</code> であること
     * ・{@link ConditionNodeKey#CONDITION_ID#getKey()} メソッドの返却値が <code>"conditionId"</code> であること
     * ・{@link ConditionNodeKey#EXCLUDE#getKey()} メソッドの返却値が <code>"exclude"</code> であること
     * ・{@link ConditionNodeKey#CONDITIONS#getKey()} メソッドの返却値が <code>"conditions"</code> であること
     * ・{@link ConditionNodeKey#CONDITION#getKey()} メソッドの返却値が <code>"condition"</code> であること
     * ・{@link ConditionNodeKey#KEY_NAME#getKey()} メソッドの返却値が <code>"keyName"</code> であること
     * ・{@link ConditionNodeKey#OPERAND#getKey()} メソッドの返却値が <code>"operand"</code> であること
     * ・{@link ConditionNodeKey#VALUE#getKey()} メソッドの返却値が <code>"value"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetKey() {
        assertEquals(KEY_CONDITION_NODES, ConditionNodeKey.CONDITION_NODES.getKey());
        assertEquals(KEY_NODE, ConditionNodeKey.NODE.getKey());
        assertEquals(KEY_CONDITION_ID, ConditionNodeKey.CONDITION_ID.getKey());
        assertEquals(KEY_EXCLUDE, ConditionNodeKey.EXCLUDE.getKey());
        assertEquals(KEY_CONDITIONS, ConditionNodeKey.CONDITIONS.getKey());
        assertEquals(KEY_CONDITION, ConditionNodeKey.CONDITION.getKey());
        assertEquals(KEY_KEY_NAME, ConditionNodeKey.KEY_NAME.getKey());
        assertEquals(KEY_OPERATOR, ConditionNodeKey.OPERATOR.getKey());
        assertEquals(KEY_OPERAND, ConditionNodeKey.OPERAND.getKey());
    }
}