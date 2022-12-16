# base64-itemstack

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
