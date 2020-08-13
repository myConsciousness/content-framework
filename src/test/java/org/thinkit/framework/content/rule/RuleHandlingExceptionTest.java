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

package org.thinkit.framework.content.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * {@link RuleHandlingException} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class RuleHandlingExceptionTest {

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのデフォルトコンストラクタの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(new RuleHandlingException());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の文字列を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link RuleHandlingException#getMessage()} の返却値がインスタンス生成時に渡した文字列と等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testConstructorWithMessage() {
        final String message = "This is a test message.";
        final RuleHandlingException exception = new RuleHandlingException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の文字列と例外情報を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link RuleHandlingException#getCause()} の返却値が {@code null} ではないこと。
     * ・{@link RuleHandlingException#getMessage()} の返却値がインスタンス生成時に渡した文字列と等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testConstructorWithMessageAndException() {
        final String message = "This is a test message.";
        RuleHandlingException exception = null;

        try {
            new ArrayList<>(0).get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = new RuleHandlingException(message, e);
        }

        assertNotNull(exception);
        assertNotNull(exception.getCause());
        assertEquals(message, exception.getMessage());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の例外情報を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link RuleHandlingException#getCause()} の返却値が {@code null} ではないこと。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testConstructorWithException() {

        RuleHandlingException exception = null;

        try {
            new ArrayList<>(0).get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = new RuleHandlingException(e);
        }

        assertNotNull(exception);
        assertNotNull(exception.getCause());
    }
}