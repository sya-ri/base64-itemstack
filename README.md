# base64-itemstack

[![GitHub License](https://img.shields.io/badge/license-MIT%20License-blue.svg?style=flat)](LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/dev.s7a/base64-itemstack)](https://search.maven.org/artifact/dev.s7a/base64-itemstack)
[![Build status](https://img.shields.io/github/actions/workflow/status/sya-ri/base64-itemstack/build.yml?branch=master&label=Build&logo=github)](.github/workflows/build.yml)

ItemStack and Base64 (string) conversion library for Spigot.

## Installation

### build.gradle.kts

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.s7a:base640-itemstack:1.0.0")
}
```

### build.gradle

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "dev.s7a:base64-itemstack:1.0.0"
}
```

## Usage

### ItemStack → String

```java
String base64 = Base64ItemStack.encode(itemStack);
```

### String → ItemStack

```java
ItemStack itemStack = Base64ItemStack.decode(base64);
```
