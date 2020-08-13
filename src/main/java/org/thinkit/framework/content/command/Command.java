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

package org.thinkit.framework.content.command;

/**
 * コマンドを抽象化したインターフェースです。
 * <p>
 * {@link Command} インターフェースを実装する際には総称型として {@link Command#run()}
 * メソッドが返却する値の型を指定してください。
 *
 * <pre>
 * 使用例 (String型を返却する場合):
 * <code>
 * public class TestCommand implements Command&lt;String&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Command<R> {

    /**
     * コマンドを実行します。<br>
     * このメソッドは {@link Command} インターフェースの宣言時に定義した総称型の値を返却します。
     *
     * @return {@link Command} インターフェースの宣言時に定義した総称型の値
     */
    public R run();
}