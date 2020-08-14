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

import lombok.NonNull;

/**
 * {@link Content} インターフェースを実装した具象コンテンツクラスの安全な呼び出しを行うコンテンツ起動クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentInvoker<R extends ContentEntity> implements Invokable<R> {

    /**
     * ルール
     */
    private Content<R> content;

    /**
     * デフォルトコンストラクタ
     */
    private ContentInvoker() {
    }

    /**
     * コンストラクタ
     *
     * @param content コンテンツ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentInvoker(@NonNull Content<R> content) {
        this.content = content;
    }

    /**
     * 引数として渡された {@code Content} オブジェクトを基に {@link ContentInvoker}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param content コンテンツ
     * @return {@link ContentInvoker} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static <R extends ContentEntity> ContentInvoker<R> of(@NonNull Content<R> content) {
        return new ContentInvoker<>(content);
    }

    @Override
    public R invoke() {
        try {
            return content.execute();
        } catch (Exception e) {
            throw new ContentHandlingException(e);
        }
    }
}