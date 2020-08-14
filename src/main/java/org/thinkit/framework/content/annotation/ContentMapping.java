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

package org.thinkit.framework.content.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.thinkit.framework.content.rule.ContentResource;

/**
 * 使用するコンテンツファイルへのマッピングを行うアノテーションです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentMapping {

    /**
     * {@link ContentResource} インターフェースを実装したEnumクラスを設定します。
     * <p>
     * 設定するEnum要素は使用するコンテンツファイルまでの相対パスが {@link ContentResource#getPath()}
     * メソッドで取得できるように実装されている必要があります。
     *
     * @return コンテンツ
     */
    Class<? extends Enum<? extends ContentResource>> content();
}