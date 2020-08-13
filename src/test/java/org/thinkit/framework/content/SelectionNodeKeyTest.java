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
 * {@link SelectionNodeKey} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class SelectionNodeKeyTest {

    /**
     * キー名 : selectionNodes
     */
    private static final String KEY_SELECTION_NODES = "selectionNodes";

    /**
     * キー名 : node
     */
    private static final String KEY_NODE = "node";

    /**
     * キー名 : conditionId
     */
    private static final String KEY_CONDITION_ID = "conditionId";

    /**
     * <pre>
     * ❏ 概要
     * {@link SelectionNodeKey} クラスの {@link SelectionNodeKey#getKey()} メソッドの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link SelectionNodeKey#SELECTION_NODES#getKey()} メソッドの返却値が <code>"selectionNodes"</code> であること
     * ・{@link SelectionNodeKey#NODE#getKey()} メソッドの返却値が <code>"node"</code> であること
     * ・{@link SelectionNodeKey#CONDITION_ID#getKey()} メソッドの返却値が <code>"conditionId"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetKey() {
        assertEquals(KEY_SELECTION_NODES, SelectionNodeKey.SELECTION_NODES.getKey());
        assertEquals(KEY_NODE, SelectionNodeKey.NODE.getKey());
        assertEquals(KEY_CONDITION_ID, SelectionNodeKey.CONDITION_ID.getKey());
    }
}