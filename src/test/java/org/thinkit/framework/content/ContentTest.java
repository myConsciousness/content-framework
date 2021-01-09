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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * {@link Content} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentTest implements Content<ContentEntityForTest> {

    /**
     * <pre>
     * ❏ 概要
     * {@link Content} インターフェースの {@link Content#execute()} メソッドの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link Content#execute()} の返却値が <code>"test"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testExecute() {
        assertEquals("test", this.execute().getTest());
    }

    @Override
    public ContentEntityForTest execute() {
        // do nothing
        return new ContentEntityForTest();
    }

    @Override
    public Set<Attribute> getAttributes() {
        // do nothing
        return Set.of();
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        // do nothing
        return List.of();
    }
}
