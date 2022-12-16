# base64-itemstack

ItemStack and Base64 (string) conversion library for Spigot.

## Usage

### ItemStack → String

```java
String base64 = Base64ItemStack.encode(itemStack);
```

### String → ItemStack

```java
ItemStack itemStack = Base64ItemStack.decode(base64);
```

