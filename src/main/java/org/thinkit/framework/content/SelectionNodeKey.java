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

import lombok.RequiredArgsConstructor;

/**
 * The enum constant that manages keys of selection node.
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
enum SelectionNodeKey implements Key {

    /**
     * The selection nodes ({@code "selectionNodes"})
     */
    SELECTION_NODES(Key.selectionNodes),

    /**
     * The node ({@code "node"})
     */
    NODE(Key.node),

    /**
     * The condition id ({@code "conditionId"})
     */
    CONDITION_ID(Key.conditionId);

    /**
     * The key
     */
    private final Key key;

    /**
     * The key constants group
     */
    private enum Key {
        selectionNodes, node, conditionId;
    }

    @Override
    public String getKey() {
        return this.key.name();
    }
}
