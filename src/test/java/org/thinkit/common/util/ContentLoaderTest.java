/**
 * Project Name : dev-utils<br>
 * File Name : ContentLoaderTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import org.thinkit.common.key.Key;
import org.thinkit.common.key.SelectionNodeKey;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Condition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.thinkit.common.rule.Content;
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
    private static final List<String> TEST_ATTRIBUTE_LIST;

    static {
        final TestContentAttribute[] attributes = TestContentAttribute.values();
        TEST_ATTRIBUTE_LIST = new ArrayList<>(attributes.length);

        for (Attribute attribute : attributes) {
            TEST_ATTRIBUTE_LIST.add(attribute.getString());
        }
    }

    /**
     * {@link ContentLoader#load(String, List)} メソッドのテストメソッドを定義するネストクラスです。
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
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が小規模のコンテンツファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, List)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, List)} から取得したリストが空リストではないこと
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
                    .load(TestContentName.SMALL_SELECTION_NODES.getString(), TEST_ATTRIBUTE_LIST);

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
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が中規模のコンテンツファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, List)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, List)} から取得したリストが空リストではないこと
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
                    .load(TestContentName.MEDIUM_SELECTION_NODES.getString(), TEST_ATTRIBUTE_LIST);

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
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が大規模のコンテンツファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, List)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, List)} から取得したリストが空リストではないこと
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
                    .load(TestContentName.LARGE_SELECTION_NODES.getString(), TEST_ATTRIBUTE_LIST);

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
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの引数として空のコンテンツ名が渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Content name is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenContentNameIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("", new ArrayList<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Content name is required.", exception.getMessage());
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの引数として空のアトリビュートリストが渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Attribute is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenAttributeListIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("test", new ArrayList<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Attribute is required.", exception.getMessage());
        }
    }

    /**
     * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestLoadWithConditions {

        /**
         * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
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
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1");
                conditions.put(TestCondition.testCondition2.getString(), "0");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.SMALL_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "0");
                conditions.put(TestCondition.testCondition2.getString(), "");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.SMALL_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("0", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストであること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1");
                conditions.put(TestCondition.testCondition2.getString(), "");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.SMALL_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
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
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "false");
                conditions.put(TestCondition.testCondition2.getString(), "test");
                conditions.put(TestCondition.testCondition3.getString(), "100");
                conditions.put(TestCondition.testCondition4.getString(), "テスト");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "test");
                conditions.put(TestCondition.testCondition2.getString(), "100");
                conditions.put(TestCondition.testCondition3.getString(), "テスト");
                conditions.put(TestCondition.testCondition4.getString(), "true");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("3", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストであること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "100");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "test");
                conditions.put(TestCondition.testCondition4.getString(), "テスト");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
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
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1000");
                conditions.put(TestCondition.testCondition2.getString(), "false");
                conditions.put(TestCondition.testCondition3.getString(), "true");
                conditions.put(TestCondition.testCondition4.getString(), "100");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.LARGE_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("5", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1000");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "false");
                conditions.put(TestCondition.testCondition4.getString(), "100");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.LARGE_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストであること
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
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "100");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "false");
                conditions.put(TestCondition.testCondition4.getString(), "1000");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの引数として空のコンテンツ名が渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Content name is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenContentNameIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("", new ArrayList<>(0), new HashMap<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Content name is required.", exception.getMessage());
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの引数として空のアトリビュートリストが渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Attribute is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenAttributeListIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("test", new ArrayList<>(0), new HashMap<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Attribute is required.", exception.getMessage());
        }
    }

    /**
     * {@link ContentLoade#getNodeList} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoade#getNodeList} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetNodeList {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getNodeList(List, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツリストを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getNodeList(List, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getNodeList(List, Key)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getNodeList(List, Key)} の返却値と生成した任意の値が等価であること
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
            final List<Map<String, Object>> actualNodeList = this.invoke(content, SelectionNodeKey.SELECTION_NODES);

            assertNotNull(actualNodeList);
            assertTrue(!actualNodeList.isEmpty());
            assertEquals(expectedNodeList, actualNodeList);
        }

        /**
         * 引数の情報を基に {@link ContentLoade#getNodeList} メソッドを呼び出すメソッドです。
         * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため {@link SuppressWarnings}
         * でuncheckedを指定しています。
         * 
         * @param content    コンテンツマップ
         * @param contentKey コンテンツキー
         * @return {@link Key}に紐づくノードリスト
         */
        @SuppressWarnings("unchecked")
        private List<Map<String, Object>> invoke(Map<String, Object> content, Key contentKey) {

            List<Map<String, Object>> nodeList = new ArrayList<>(0);

            try {
                nodeList = (List<Map<String, Object>>) this.getTestMethod().invoke(TEST_CLASS, content, contentKey);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return nodeList;
        }

        /**
         * {@link ContentLoade#getNodeList} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoade#getNodeList} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getNodeList";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, Map.class, Key.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * テスト用コンテンツ名クラス
     */
    private enum TestContentName implements Content {

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
        LARGE_CONDITION_NODES(Name.testContentWithLargeConditionNodes);

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
            testContentWithSmallConditionNodes, testContentWithMediumConditionNodes, testContentWithLargeConditionNodes;
        }

        @Override
        public String getString() {
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
        testCondition1, testCondition2, testCondition3, testCondition4, testCondition5, testCondition6;

        @Override
        public String getString() {
            return this.name();
        }
    }
}