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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.util.reflection.FluentReflection;
import org.thinkit.framework.content.catalog.ContentRoot;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * {@link ContentLoader} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentLoaderTest {

    /**
     * テスト用アトリビュートリスト
     */
    private static final Set<String> TEST_ATTRIBUTE_SET;

    static {
        final TestContentAttribute[] attributes = TestContentAttribute.values();
        TEST_ATTRIBUTE_SET = new HashSet<>(attributes.length);

        for (Attribute attribute : attributes) {
            TEST_ATTRIBUTE_SET.add(attribute.getString());
        }
    }

    /**
     * {@link ContentLoader#load(String, Set)} メソッドのテストメソッドを定義するネストクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestLoad {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が小規模のコンテンツファイルを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, Set)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, Set)} から取得したリストが空リストではないこと
         * ・キー名 <code>"test1"</code> に紐づく項目の値が <code>"0"</code> であること
         * ・キー名 <code>"test2"</code> に紐づく項目の値が <code>"false"</code> であること
         * ・キー名 <code>"test3"</code> に紐づく項目の値が <code>"0L"</code> であること
         * ・キー名 <code>"test4"</code> に紐づく項目の値が <code>"1.0"</code> であること
         * ・キー名 <code>"test5"</code> に紐づく項目の値が <code>"test"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testSmallSelectionNodes() {

            final List<Map<String, String>> contents = ContentLoader
                    .load(getResourceAsStream(TestContentName.SMALL_SELECTION_NODES.getPath()), TEST_ATTRIBUTE_SET);

            assertNotNull(contents);
            assertTrue(!contents.isEmpty());

            for (Map<String, String> content : contents) {
                assertEquals("0", content.get(TestContentAttribute.test1.getString()));
                assertEquals("false", content.get(TestContentAttribute.test2.getString()));
                assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
                assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
                assertEquals("test", content.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が中規模のコンテンツファイルを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, Set)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, Set)} から取得したリストが空リストではないこと
         * ・キー名 <code>"test1"</code> に紐づく項目の値が <code>"0"</code> であること
         * ・キー名 <code>"test2"</code> に紐づく項目の値が <code>"false"</code> であること
         * ・キー名 <code>"test3"</code> に紐づく項目の値が <code>"0L"</code> であること
         * ・キー名 <code>"test4"</code> に紐づく項目の値が <code>"1.0"</code> であること
         * ・キー名 <code>"test5"</code> に紐づく項目の値が <code>"test"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testMediumSelectionNodes() {

            final List<Map<String, String>> contents = ContentLoader
                    .load(getResourceAsStream(TestContentName.MEDIUM_SELECTION_NODES.getPath()), TEST_ATTRIBUTE_SET);

            assertNotNull(contents);
            assertTrue(!contents.isEmpty());

            for (Map<String, String> content : contents) {
                assertEquals("0", content.get(TestContentAttribute.test1.getString()));
                assertEquals("false", content.get(TestContentAttribute.test2.getString()));
                assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
                assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
                assertEquals("test", content.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が大規模のコンテンツファイルを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, Set)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, Set)} から取得したリストが空リストではないこと
         * ・キー名 <code>"test1"</code> に紐づく項目の値が <code>"0"</code> であること
         * ・キー名 <code>"test2"</code> に紐づく項目の値が <code>"false"</code> であること
         * ・キー名 <code>"test3"</code> に紐づく項目の値が <code>"0L"</code> であること
         * ・キー名 <code>"test4"</code> に紐づく項目の値が <code>"1.0"</code> であること
         * ・キー名 <code>"test5"</code> に紐づく項目の値が <code>"test"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testLargeSelectionNodes() {

            final List<Map<String, String>> contents = ContentLoader
                    .load(getResourceAsStream(TestContentName.LARGE_SELECTION_NODES.getPath()), TEST_ATTRIBUTE_SET);

            assertNotNull(contents);
            assertTrue(!contents.isEmpty());

            for (Map<String, String> content : contents) {
                assertEquals("0", content.get(TestContentAttribute.test1.getString()));
                assertEquals("false", content.get(TestContentAttribute.test2.getString()));
                assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
                assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
                assertEquals("test", content.get(TestContentAttribute.test5.getString()));
            }
        }
    }

    /**
     * {@link ContentLoader#load(String, Set, Map)} メソッドのテストメソッドを定義するネストクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestLoadWithConditions {

        /**
         * {@link ContentLoader#load(String, Set, Map)} メソッドのテストメソッドを定義するネストクラスです。
         * このクラスに定義するテストメソッドは小規模条件ノードを定義したコンテンツファイルを使用してください。
         *
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        @Nested
        final class TestSmallConditionNodes {

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"1"</code> であること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1"</code>
             * ・<code>"testCondition2" : "0"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1");
                conditions.put(TestCondition.testCondition2.getString(), "0");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.SMALL_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"0"</code> であること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "0"</code>
             * ・<code>"testCondition2" : ""</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testAnotherRecordWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "0");
                conditions.put(TestCondition.testCondition2.getString(), "");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.SMALL_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("0", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストであること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1"</code>
             * ・<code>"testCondition2" : ""</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testNoRecordWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1");
                conditions.put(TestCondition.testCondition2.getString(), "");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.SMALL_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * {@link ContentLoader#load(String, Set, Map)} メソッドのテストメソッドを定義するネストクラスです。
         * このクラスに定義するテストメソッドは中規模条件ノードを定義したコンテンツファイルを使用してください。
         *
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        @Nested
        final class TestMediumConditionsNodes {

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"1"</code> であること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "false"</code>
             * ・<code>"testCondition2" : "test"</code>
             * ・<code>"testCondition3" : "100"</code>
             * ・<code>"testCondition4" : "テスト"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "false");
                conditions.put(TestCondition.testCondition2.getString(), "test");
                conditions.put(TestCondition.testCondition3.getString(), "100");
                conditions.put(TestCondition.testCondition4.getString(), "テスト");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.MEDIUM_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"3"</code> であること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "test"</code>
             * ・<code>"testCondition2" : "100"</code>
             * ・<code>"testCondition3" : "テスト"</code>
             * ・<code>"testCondition4" : "true"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testAnotherRecordWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "test");
                conditions.put(TestCondition.testCondition2.getString(), "100");
                conditions.put(TestCondition.testCondition3.getString(), "テスト");
                conditions.put(TestCondition.testCondition4.getString(), "true");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.MEDIUM_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("3", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストであること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "100"</code>
             * ・<code>"testCondition2" : "true"</code>
             * ・<code>"testCondition3" : "test"</code>
             * ・<code>"testCondition4" : "テスト"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testNoRecordWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "100");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "test");
                conditions.put(TestCondition.testCondition4.getString(), "テスト");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.MEDIUM_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * {@link ContentLoader#load(String, Set, Map)} メソッドのテストメソッドを定義するネストクラスです。
         * このクラスに定義するテストメソッドは大規模条件ノードを定義したコンテンツファイルを使用してください。
         *
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        @Nested
        final class TestLargeConditionsNodes {

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"5"</code> であること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1000"</code>
             * ・<code>"testCondition2" : "false"</code>
             * ・<code>"testCondition3" : "true"</code>
             * ・<code>"testCondition4" : "100"</code>
             * ・<code>"testCondition5" : "test"</code>
             * ・<code>"testCondition6" : "10L"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1000");
                conditions.put(TestCondition.testCondition2.getString(), "false");
                conditions.put(TestCondition.testCondition3.getString(), "true");
                conditions.put(TestCondition.testCondition4.getString(), "100");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.LARGE_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("5", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"6"</code> であること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1000"</code>
             * ・<code>"testCondition2" : "true"</code>
             * ・<code>"testCondition3" : "false"</code>
             * ・<code>"testCondition4" : "100"</code>
             * ・<code>"testCondition5" : "test"</code>
             * ・<code>"testCondition6" : "10L"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testAnotherRecordWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1000");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "false");
                conditions.put(TestCondition.testCondition4.getString(), "100");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.LARGE_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, Set, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             *
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, Set, Map)} から取得したリストが空リストであること
             * </pre>
             *
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "100"</code>
             * ・<code>"testCondition2" : "true"</code>
             * ・<code>"testCondition3" : "false"</code>
             * ・<code>"testCondition4" : "1000"</code>
             * ・<code>"testCondition5" : "test"</code>
             * ・<code>"testCondition6" : "10L"</code>
             * </pre>
             *
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testNoRecordWithConditions() {

                final String resultAttribute = "result";
                final Set<String> attributes = new HashSet<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "100");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "false");
                conditions.put(TestCondition.testCondition4.getString(), "1000");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader.load(
                        getResourceAsStream(TestContentName.MEDIUM_CONDITION_NODES.getPath()), attributes,
                        List.of(conditions));

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }
    }

    /**
     * {@link ContentLoader#getNodeList(Map, Key)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getNodeList(Map, Key)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetNodeList {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getNodeList(Map, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツリストを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getNodeList(Map, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getNodeList(Map, Key)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getNodeList(Map, Key)} の返却値と生成した任意の値が等価であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final Map<String, Object> content = new HashMap<>();
            final List<Map<String, String>> expectedNodeList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                final Map<String, String> node = new HashMap<>();
                node.put("testNode1", "something");
                node.put("testNode2", "something");
                node.put("testNode3", "something");

                expectedNodeList.add(node);
            }

            content.put(SelectionNodeKey.SELECTION_NODES.getKey(), expectedNodeList);

            final FluentReflection<List<Map<String, Object>>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(Map.class, content).add(Key.class, SelectionNodeKey.SELECTION_NODES);
            final List<Map<String, Object>> actualNodeList = reflection.invokeStatic("getNodeList");

            assertNotNull(actualNodeList);
            assertTrue(!actualNodeList.isEmpty());
            assertEquals(expectedNodeList, actualNodeList);
        }
    }

    /**
     * {@link ContentLoader#getNodeMap(Map, Key)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getNodeMap(Map, Key)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetNodeMap {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getNodeMap(Map, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツマップを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getNodeMap(Map, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getNodeMap(Map, Key)} の返却値が空マップではないこと
         * ・{@link ContentLoader#getNodeMap(Map, Key)} の返却値と生成した任意の値が等価であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final Map<String, Object> nodes = new HashMap<>();
            final Map<String, String> expectedNodeMap = new HashMap<>();
            expectedNodeMap.put("testNode1", "something");
            expectedNodeMap.put("testNode2", "something");
            expectedNodeMap.put("testNode3", "something");

            nodes.put(ConditionNodeKey.CONDITIONS.getKey(), expectedNodeMap);

            final FluentReflection<Map<String, Object>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(Map.class, nodes).add(Key.class, ConditionNodeKey.CONDITIONS);
            final Map<String, Object> actualNodeMap = reflection.invokeStatic("getNodeMap");

            assertNotNull(actualNodeMap);
            assertTrue(!actualNodeMap.isEmpty());
            assertEquals(expectedNodeMap, actualNodeMap);
        }
    }

    /**
     * {@link ContentLoader#getString(Map, Key)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getString(Map, Key)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetString {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getString(Map, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツ値を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getString(Map, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getString(Map, Key)} の返却値が空文字列ではないこと
         * ・{@link ContentLoader#getString(Map, Key)} の返却値と生成した任意の値が等価であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final String expectedContentValue = "Hello World!";

            final Map<String, Object> node = new HashMap<>();
            node.put(SelectionNodeKey.CONDITION_ID.getKey(), expectedContentValue);

            final FluentReflection<String> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(Map.class, node).add(String.class, SelectionNodeKey.CONDITION_ID.getKey());
            final String actualContentValue = reflection.invokeStatic("getString");

            assertNotNull(actualContentValue);
            assertTrue(!actualContentValue.isEmpty());
            assertEquals(expectedContentValue, actualContentValue);
        }
    }

    /**
     * {@link ContentLoader#getContent(String)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getContent(String)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetContent {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContent(String)} メソッドの返却値を確認する。
         * 期待値として標準のテスト用コンテンツファイルを使用することとする。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContent(String)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContent(String)} の返却値が空リストではないこと
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final Map<String, Object> content = new FluentReflection<Map<String, Object>>(ContentLoader.class)
                    .add(InputStream.class, getResourceAsStream(TestContentName.DEFAULT.getPath()))
                    .invokeStatic("getContent");

            assertNotNull(content);
            assertTrue(!content.isEmpty());
            assertTrue(content instanceof LinkedHashMap);
        }
    }

    /**
     * {@link ContentLoader#getContentList(Set, Map, List)}
     * メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getContentList(Set, Map, List)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetContentList {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContentList(Set, Map, List)} メソッドの返却値を確認する。
         * このテストではコンテンツ定義の全ノードにconditionIdの値が設定されている場合を想定して行う。
         * conditionIdが <code>"1"</code> のレコードを取得しテストを行う。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値のサイズが <code>1</code> であること
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の0番インデックスに紐づくレコードが {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の0番インデックスに紐づくレコードが空マップではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の0番インデックスに紐づくレコードのサイズが <code>3</code> であること
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の0番インデックスに紐づくレコードの値が全て <code>"something1"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithConditionId() {
            final Set<String> attributes = new HashSet<>(3);
            attributes.add(TestContentAttribute.test1.getString());
            attributes.add(TestContentAttribute.test2.getString());
            attributes.add(TestContentAttribute.test3.getString());

            final Map<String, Object> selectionNodes = new HashMap<>(1);
            final List<Map<String, Map<String, String>>> selectionNodesList = new ArrayList<>(2);

            for (int i = 0; i < 2; i++) {
                final Map<String, Map<String, String>> node = new HashMap<>(2);
                final Map<String, String> items = new HashMap<>(3);
                final String itemValue = String.format("something%s", i);

                items.put(SelectionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                items.put(TestContentAttribute.test1.getString(), itemValue);
                items.put(TestContentAttribute.test2.getString(), itemValue);
                items.put(TestContentAttribute.test3.getString(), itemValue);

                node.put(SelectionNodeKey.NODE.getKey(), items);
                selectionNodesList.add(node);
            }

            selectionNodes.put(SelectionNodeKey.SELECTION_NODES.getKey(), selectionNodesList);

            final List<String> conditionIdList = new ArrayList<>(1);
            conditionIdList.add("1");

            final FluentReflection<List<Map<String, String>>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(Set.class, attributes).add(Map.class, selectionNodes).add(List.class, conditionIdList);
            final List<Map<String, String>> actualContentList = reflection.invokeStatic("getContentList");

            final Map<String, String> actualRecord = actualContentList.get(0);

            assertNotNull(actualContentList);
            assertNotNull(actualRecord);
            assertTrue(!actualContentList.isEmpty());
            assertTrue(!actualRecord.isEmpty());
            assertTrue(actualContentList.size() == 1);
            assertTrue(actualRecord.size() == 3);
            assertEquals("something1", actualRecord.get(TestContentAttribute.test1.getString()));
            assertEquals("something1", actualRecord.get(TestContentAttribute.test2.getString()));
            assertEquals("something1", actualRecord.get(TestContentAttribute.test3.getString()));
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContentList(Set, Map, List)} メソッドの返却値を確認する。
         * このテストではコンテンツ定義の全ノードにconditionIdの値が設定されていない場合を想定して行う。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値のサイズが <code>5</code> であること
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードが {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードが空マップではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードのサイズが <code>5</code> であること
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードの値が全て生成した期待値と等価であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithNoConditionId() {
            final Set<String> attributes = new HashSet<>(5);
            attributes.add(TestContentAttribute.test1.getString());
            attributes.add(TestContentAttribute.test2.getString());
            attributes.add(TestContentAttribute.test3.getString());
            attributes.add(TestContentAttribute.test4.getString());
            attributes.add(TestContentAttribute.test5.getString());

            final Map<String, Object> selectionNodes = new HashMap<>(1);
            final List<Map<String, Map<String, String>>> selectionNodesList = new ArrayList<>(5);

            for (int i = 0; i < 5; i++) {
                final Map<String, Map<String, String>> node = new HashMap<>(2);
                final Map<String, String> items = new HashMap<>(5);
                final String itemValue = String.format("something%s", i);

                items.put(SelectionNodeKey.CONDITION_ID.getKey(), StringUtils.EMPTY);
                items.put(TestContentAttribute.test1.getString(), itemValue);
                items.put(TestContentAttribute.test2.getString(), itemValue);
                items.put(TestContentAttribute.test3.getString(), itemValue);
                items.put(TestContentAttribute.test4.getString(), itemValue);
                items.put(TestContentAttribute.test5.getString(), itemValue);

                node.put(SelectionNodeKey.NODE.getKey(), items);
                selectionNodesList.add(node);
            }

            selectionNodes.put(SelectionNodeKey.SELECTION_NODES.getKey(), selectionNodesList);

            final FluentReflection<List<Map<String, String>>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(Set.class, attributes).add(Map.class, selectionNodes).add(List.class, new ArrayList<>(0));
            final List<Map<String, String>> actualContentList = reflection.invokeStatic("getContentList");

            assertNotNull(actualContentList);
            assertTrue(!actualContentList.isEmpty());
            assertTrue(actualContentList.size() == 5);

            for (int i = 0; i < 5; i++) {
                final String expectedItemValue = String.format("something%s", i);
                final Map<String, String> actualRecord = actualContentList.get(i);

                assertNotNull(actualRecord);
                assertTrue(!actualRecord.isEmpty());
                assertTrue(actualRecord.size() == 5);
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test1.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test2.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test3.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test4.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContentList(Set, Map, List)} メソッドの返却値を確認する。
         * このテストではコンテンツ定義のノードにconditionIdの値が設定されているレコードと設定されていないレコードが存在する場合を想定して行う。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の返却値のサイズが <code>7</code> であること
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードが {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードが空マップではないこと
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードのサイズが <code>5</code> であること
         * ・{@link ContentLoader#getContentList(Set, Map, List)} の各インデックスに紐づくレコードの値が全て生成した期待値と等価であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithConditionIdAndNoConditionId() {
            final Set<String> attributes = new HashSet<>(5);
            attributes.add(TestContentAttribute.test1.getString());
            attributes.add(TestContentAttribute.test2.getString());
            attributes.add(TestContentAttribute.test3.getString());
            attributes.add(TestContentAttribute.test4.getString());
            attributes.add(TestContentAttribute.test5.getString());

            final Map<String, Object> selectionNodes = new LinkedHashMap<>(1);
            final List<Map<String, Map<String, String>>> selectionNodesList = new ArrayList<>(10);

            for (int i = 0; i < 10; i++) {
                final Map<String, Map<String, String>> node = new LinkedHashMap<>(2);
                final Map<String, String> items = new LinkedHashMap<>(5);
                final String itemValue = String.format("something%s", i);

                if (i % 2 == 0) {
                    items.put(SelectionNodeKey.CONDITION_ID.getKey(), StringUtils.EMPTY);
                } else {
                    items.put(SelectionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                }

                items.put(TestContentAttribute.test1.getString(), itemValue);
                items.put(TestContentAttribute.test2.getString(), itemValue);
                items.put(TestContentAttribute.test3.getString(), itemValue);
                items.put(TestContentAttribute.test4.getString(), itemValue);
                items.put(TestContentAttribute.test5.getString(), itemValue);

                node.put(SelectionNodeKey.NODE.getKey(), items);
                selectionNodesList.add(node);
            }

            selectionNodes.put(SelectionNodeKey.SELECTION_NODES.getKey(), selectionNodesList);

            final List<String> conditionIdList = new ArrayList<>(2);
            conditionIdList.add("1");
            conditionIdList.add("7");

            final FluentReflection<List<Map<String, String>>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(Set.class, attributes).add(Map.class, selectionNodes).add(List.class, conditionIdList);
            final List<Map<String, String>> actualContentList = reflection.invokeStatic("getContentList");

            assertNotNull(actualContentList);
            assertTrue(!actualContentList.isEmpty());
            assertTrue(actualContentList.size() == 7);

            final List<Integer> expectedRecordIndexes = new ArrayList<>(7);
            expectedRecordIndexes.add(0);
            expectedRecordIndexes.add(1);
            expectedRecordIndexes.add(2);
            expectedRecordIndexes.add(4);
            expectedRecordIndexes.add(6);
            expectedRecordIndexes.add(7);
            expectedRecordIndexes.add(8);

            for (int i = 0, size = expectedRecordIndexes.size(); i < size; i++) {
                final String expectedItemValue = String.format("something%s", expectedRecordIndexes.get(i));
                final Map<String, String> actualRecord = actualContentList.get(i);

                assertNotNull(actualRecord);
                assertTrue(!actualRecord.isEmpty());
                assertTrue(actualRecord.size() == 5);
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test1.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test2.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test3.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test4.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test5.getString()));
            }
        }
    }

    /**
     * {@link ContentLoader#getContentList(Set, Map, List)}
     * メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getContentList(Set, Map, List)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetConditionIdList {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getConditionIdList(List, Map)} メソッドの返却値を確認する。
         * このテストではコンテンツファイルに各条件ノードが1つの条件のみを持っている状態を想定して行う。
         * また、 {@link ContentLoader#getConditionIdList(List, Map)} メソッドを実行した結果、conditionIdが <code>"1"</code> の条件に合致するようにテストを行う。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値のサイズが <code>1</code> であること
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値のリストの0番目に紐づく値が <code>"1"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithOneCondition() {

            final List<Map<String, Object>> conditionNodes = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                final Map<String, Object> nodes = new HashMap<>();
                final Map<String, Object> items = new HashMap<>();

                items.put(ConditionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                items.put(ConditionNodeKey.EXCLUDE.getKey(), "false");

                final List<Map<String, Object>> conditionList = new ArrayList<>();
                final Map<String, Object> condition = new HashMap<>();

                condition.put(ConditionNodeKey.KEY_NAME.getKey(), "testCondition1");
                condition.put(ConditionNodeKey.OPERATOR.getKey(), "=");
                condition.put(ConditionNodeKey.OPERAND.getKey(), String.valueOf(i));
                conditionList.add(condition);

                items.put(ConditionNodeKey.CONDITIONS.getKey(), conditionList);
                nodes.put(ConditionNodeKey.NODE.getKey(), items);
                conditionNodes.add(nodes);
            }

            final Map<String, String> conditions = new HashMap<>();
            conditions.put(TestCondition.testCondition1.getString(), "1");

            final FluentReflection<List<String>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(List.class, conditionNodes).add(List.class, List.of(conditions));
            final List<String> actualConditionIdList = reflection.invokeStatic("getConditionIdList");

            assertNotNull(actualConditionIdList);
            assertTrue(!actualConditionIdList.isEmpty());
            assertTrue(actualConditionIdList.size() == 1);
            assertEquals("1", actualConditionIdList.get(0));
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getConditionIdList(List, Map)} メソッドの返却値を確認する。
         * このテストではコンテンツファイルに各条件ノードが3つの条件を持っている状態を想定して行う。
         * また、 {@link ContentLoader#getConditionIdList(List, Map)} メソッドを実行した結果、conditionIdが <code>"4"</code> の条件に合致するようにテストを行う。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が空リストではない
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値のサイズが <code>1</code> であること
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値のリストの0番目に紐づく値が <code>"4"</code> であるここと
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithThreeConditions() {

            final List<Map<String, Object>> conditionNodes = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                final Map<String, Object> nodes = new HashMap<>();
                final Map<String, Object> items = new HashMap<>();

                items.put(ConditionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                items.put(ConditionNodeKey.EXCLUDE.getKey(), "false");

                final List<Map<String, Object>> conditionList = new ArrayList<>();
                final Map<String, Object> condition = new HashMap<>();

                for (int j = 0; j < 3; j++) {
                    condition.put(ConditionNodeKey.KEY_NAME.getKey(), String.format("testCondition%s", j + 1));
                    condition.put(ConditionNodeKey.OPERATOR.getKey(), "=");
                    condition.put(ConditionNodeKey.OPERAND.getKey(), String.format("testValue%s%s", i, j));
                    conditionList.add(condition);
                }

                items.put(ConditionNodeKey.CONDITIONS.getKey(), conditionList);
                nodes.put(ConditionNodeKey.NODE.getKey(), items);
                conditionNodes.add(nodes);
            }

            final Map<String, String> conditions = new HashMap<>();
            conditions.put(TestCondition.testCondition1.getString(), "testValue40");
            conditions.put(TestCondition.testCondition2.getString(), "testValue41");
            conditions.put(TestCondition.testCondition3.getString(), "testValue42");

            final FluentReflection<List<String>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(List.class, conditionNodes).add(List.class, List.of(conditions));
            final List<String> actualConditionIdList = reflection.invokeStatic("getConditionIdList");

            assertNotNull(actualConditionIdList);
            assertTrue(!actualConditionIdList.isEmpty());
            assertTrue(actualConditionIdList.size() == 1);
            assertEquals("4", actualConditionIdList.get(0));
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getConditionIdList(List, Map)} メソッドの返却値を確認する。
         * このテストではコンテンツファイルに各条件ノードが10の条件を持っている状態を想定して行う。
         * また、 {@link ContentLoader#getConditionIdList(List, Map)} メソッドを実行した結果、conditionIdが <code>"7"</code> の条件に合致するようにテストを行う。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が空リストではない
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値のサイズが <code>1</code> であること
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値のリストの0番目に紐づく値が <code>"7"</code> であるここと
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithTenConditions() {

            final List<Map<String, Object>> conditionNodes = new ArrayList<>();

            for (int i = 0; i < 11; i++) {
                final Map<String, Object> nodes = new HashMap<>();
                final Map<String, Object> items = new HashMap<>();

                items.put(ConditionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                items.put(ConditionNodeKey.EXCLUDE.getKey(), "false");

                final List<Map<String, Object>> conditionList = new ArrayList<>();
                final Map<String, Object> condition = new HashMap<>();

                for (int j = 0; j < 3; j++) {
                    condition.put(ConditionNodeKey.KEY_NAME.getKey(), String.format("testCondition%s", j + 1));
                    condition.put(ConditionNodeKey.OPERATOR.getKey(), "=");
                    condition.put(ConditionNodeKey.OPERAND.getKey(), String.format("testValue%s%s", i, j));
                    conditionList.add(condition);
                }

                items.put(ConditionNodeKey.CONDITIONS.getKey(), conditionList);
                nodes.put(ConditionNodeKey.NODE.getKey(), items);
                conditionNodes.add(nodes);
            }

            final Map<String, String> conditions = new HashMap<>();
            conditions.put(TestCondition.testCondition1.getString(), "testValue70");
            conditions.put(TestCondition.testCondition2.getString(), "testValue71");
            conditions.put(TestCondition.testCondition3.getString(), "testValue72");
            conditions.put(TestCondition.testCondition4.getString(), "testValue73");
            conditions.put(TestCondition.testCondition5.getString(), "testValue74");
            conditions.put(TestCondition.testCondition6.getString(), "testValue75");
            conditions.put(TestCondition.testCondition7.getString(), "testValue76");
            conditions.put(TestCondition.testCondition8.getString(), "testValue77");
            conditions.put(TestCondition.testCondition9.getString(), "testValue78");
            conditions.put(TestCondition.testCondition10.getString(), "testValue79");

            final FluentReflection<List<String>> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(List.class, conditionNodes).add(List.class, List.of(conditions));
            final List<String> actualConditionIdList = reflection.invokeStatic("getConditionIdList");

            assertNotNull(actualConditionIdList);
            assertTrue(!actualConditionIdList.isEmpty());
            assertTrue(actualConditionIdList.size() == 1);
            assertEquals("7", actualConditionIdList.get(0));
        }
    }

    /**
     * {@link ContentLoader#all(List, Map)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#all(List, Map)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestAll {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#all(List, Map)} メソッドの返却値を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#all(List, Map)} の返却値が {@code true} であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePatterns() {
            final List<Integer> counts = new ArrayList<>();
            counts.add(1);
            counts.add(3);
            counts.add(5);
            counts.add(10);
            counts.add(100);
            counts.add(1000);

            for (Integer count : counts) {
                this.testSimplePatterns(count);
            }
        }

        /**
         * {@link ContentLoader#all(List, Map)} の返却値を確認します。<br>
         * 引数として指定された数に応じた条件を生成します。
         *
         * @param count テストする条件の数
         */
        private void testSimplePatterns(final int count) {
            final List<Map<String, Object>> conditionList = new ArrayList<>();
            final Map<String, Object> condition = new HashMap<>();
            final Map<String, String> conditions = new HashMap<>();

            for (int i = 1; i <= count; i++) {
                condition.put(ConditionNodeKey.KEY_NAME.getKey(), String.valueOf(i));
                condition.put(ConditionNodeKey.OPERATOR.getKey(), "=");
                condition.put(ConditionNodeKey.OPERAND.getKey(), String.valueOf(i));
                conditionList.add(condition);

                conditions.put(TestCondition.testCondition1.getString(), String.valueOf(i));
            }

            final FluentReflection<Boolean> reflection = new FluentReflection<>(ContentLoader.class);
            reflection.add(List.class, conditionList).add(Map.class, conditions);
            assertTrue(reflection.invokeStatic("all"));
        }
    }

    private InputStream getResourceAsStream(final String content) {
        return ContentLoaderTest.class.getClassLoader()
                .getResourceAsStream(ContentRoot.ROOT.getTag() + content + Extension.json());
    }

    /**
     * テスト用コンテンツ名クラス
     */
    private enum TestContentName implements ContentResource {

        /**
         * 小規模選択ノードのテスト用コンテンツ
         */
        SMALL_SELECTION_NODES(Name.testContentWithSmallSelectionNodes),

        /**
         * 中規模選択ノードのテスト用コンテンツ
         */
        MEDIUM_SELECTION_NODES(Name.testContentWithMediumSelectionNodes),

        /**
         * 大規模選択ノードのテスト用コンテンツ
         */
        LARGE_SELECTION_NODES(Name.testContentWithLargeSelectionNodes),

        /**
         * 小規模条件ノードのテスト用コンテンツ
         */
        SMALL_CONDITION_NODES(Name.testContentWithSmallConditionNodes),

        /**
         * 中規模条件ノードのテスト用コンテンツ
         */
        MEDIUM_CONDITION_NODES(Name.testContentWithMediumConditionNodes),

        /**
         * 大規模条件ノードのテスト用コンテンツ
         */
        LARGE_CONDITION_NODES(Name.testContentWithLargeConditionNodes),

        /**
         * 標準のテスト用コンテンツ
         */
        DEFAULT(Name.testContent),

        /**
         * 本番仕様のテスト用コンテンツ
         */
        PRODUCTION(Name.content);

        /**
         * コンテンツ名
         */
        private Name contentName;

        /**
         * コンストラクタ
         *
         * @param contentName コンテンツ名
         */
        TestContentName(Name contentName) {
            this.contentName = contentName;
        }

        /**
         * コンテンツ名
         */
        private enum Name {
            testContentWithSmallSelectionNodes, testContentWithMediumSelectionNodes, testContentWithLargeSelectionNodes,
            testContentWithSmallConditionNodes, testContentWithMediumConditionNodes, testContentWithLargeConditionNodes,
            testContent, content;
        }

        @Override
        public String getPath() {
            return this.contentName.name();
        }
    }

    /**
     * テスト用アトリビュートクラス
     */
    private enum TestContentAttribute implements Attribute {
        test1, test2, test3, test4, test5;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * テスト用条件クラス
     */
    private enum TestCondition implements Condition {
        testCondition1, testCondition2, testCondition3, testCondition4, testCondition5, testCondition6, testCondition7,
        testCondition8, testCondition9, testCondition10;

        @Override
        public String getString() {
            return this.name();
        }
    }
}
