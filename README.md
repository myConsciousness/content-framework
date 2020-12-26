![Build](https://img.shields.io/badge/Build-Automated-2980b9.svg?style=for-the-badge)
![Latest Version](https://img.shields.io/badge/Latest_Version-v1.0.5-27ae60.svg?style=for-the-badge)
![License](https://img.shields.io/badge/License-Apache_2.0-e74c3c.svg?style=for-the-badge)</br>
![Java CI with Gradle](https://github.com/myConsciousness/content-framework/workflows/Java%20CI%20with%20Gradle/badge.svg)

# Content Framework

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

**Table of Contents**

- [What is it?](#what-is-it)
- [Benefits](#benefits)
- [How To Use](#how-to-use)
  - [1. Add the dependencies](#1-add-the-dependencies)
- [License](#license)
- [More Information](#more-information)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## What is it?

**_Makes your code more flexible to changes._**</br>
**_This framework can be used like `NoSql database` to just handle master data._**

`Content Framework` was designed and created with the goal of creating a large amount of code that is flexible enough to respond to changes in value and behavioral specifications in the source code.

The `content file` used by `Content Framework` are JSON-based, intuitive and easy to read structures. Content files allow you to define options of values and conditions for retrieving options.

As a framework each procedure is designed to be easy to perform, and the implementation is optimized to be very light and fast.

## Benefits

- Be more flexible code for specification changes
- Possible to reuse common values and behaviors defined as content and rules
- Possible to change the behavior without changing the source code directly
- Light and optimized NoSql database for master data

## How To Use

### 1. Add the dependencies

> **_Note:_**</br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/content-framework/packages).</br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.framework.content</groupId>
  <artifactId>content-framework</artifactId>
  <version>v1.0.5</version>
</dependency>

<servers>
  <server>
    <id>github</id>
    <username>myConsciousness</username>
    <password>xxxxxxxxxxxxxxxxxx</password>
  </server>
</servers>
```

**_Gradle_**

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/myConsciousness/content-framework")
        credentials {
          username = "myConsciousness"
          password = "xxxxxxxxxxxxxxxxxx"
        }
    }
}

dependencies {
    implementation 'org.thinkit.framework.content:content-framework:v1.0.5'
}
```

## License

```license
Copyright 2020 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```

## More Information

`Content Framework` was designed and implemented by Kato Shinya, who works as a freelance developer.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [Creator Website](https://myconsciousness.github.io/)
- [License](https://github.com/myConsciousness/content-framework/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/content-framework/releases)
- [Package](https://github.com/myConsciousness/content-framework/packages)
- [File a Bug](https://github.com/myConsciousness/content-framework/issues)
- [Reference](https://myconsciousness.github.io/content-framework/)
