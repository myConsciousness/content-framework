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

import org.thinkit.framework.content.entity.ContentEntity;

/**
 * テスト用のコンテンツエンティティです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class ContentEntityForTest implements ContentEntity {

    /**
     * テスト用文字列
     */
    private String test = "test";

    /**
     * テスト用文字列を返却します。
     *
     * @return テスト用文字列
     */
    public String getTest() {
        return this.test;
    }
}