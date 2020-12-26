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
 * The enum constant that manages keys of condition node.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@RequiredArgsConstructor
enum ConditionNodeKey implements Key {

    /**
     * The condition nodes ({@code "conditionNodes"})
     */
    CONDITION_NODES(Key.conditionNodes),

    /**
     * The node ({@code "node"})
     */
    NODE(Key.node),

    /**
     * The condition id ({@code "conditionId"})
     */
    CONDITION_ID(Key.conditionId),

    /**
     * The exclude ({@code "exclude"})
     */
    EXCLUDE(Key.exclude),

    /**
     * The conditions ({@code "conditions"})
     */
    CONDITIONS(Key.conditions),

    /**
     * The condition ({@code "condition"})
     */
    CONDITION(Key.condition),

    /**
     * The key name ({@code "keyName"})
     */
    KEY_NAME(Key.keyName),

    /**
     * The operator ({@code "operator"})
     */
    OPERATOR(Key.operator),

    /**
     * The operand ({@code "operand"})
     */
    OPERAND(Key.operand);

    /**
     * The key
     */
    private final Key key;

    /**
     * The key constant group
     */
    private enum Key {
        conditionNodes, node, conditionId, exclude, conditions, condition, keyName, operand, dataType, operator;
    }

    @Override
    public String getKey() {
        return this.key.name();
    }
}
