# Number types

Java defines six primitive types for numbers, `byte`, `short`, `int`, `long`, `float` and `double`.

It also defines the six corresponding classes `Byte`, `Short`, `Integer`, `Long`, `Float` and `Double`.

These classes define the following constants for each type: `MIN_VALUE`, `MAX_VALUE`, `BYTES` and `SIZE`.

`BYTES` is the size of the type in bytes; and `SIZE` is the size of the type in bits.

`byte`, `short`, `int` and `long` are integral types which use two's complement representation for encoding signed
numbers.

`float` and `double` are floating point types which use the IEEE 754 representation.

| Primitive type | Class     | `MIN_VALUE`          | `MAX_VALUE`            | `SIZE` | `BYTES` |
|----------------|-----------|----------------------|------------------------|--------|---------|
| `byte`         | `Byte`    | -128                 | 127                    | 8      | 1       |
| `short`        | `Short`   | -32768               | 32767                  | 16     | 2       |
| `int`          | `Integer` | -2147483648          | 2147483647             | 32     | 4       |
| `long`         | `Long`    | -9223372036854775808 | 9223372036854775807    | 64     | 8       |
| `float`        | `Float`   | 1.4E-45              | 3.4028235E38           | 32     | 4       |
| `double`       | `Double`  | 4.9E-324             | 1.7976931348623157E308 | 64     | 8       |

See [NumberTypesTest](numbers/src/test/java/numbers/NumberTypesTest.java).

# Auto boxing

When a `Byte` is expected, but a `byte` provided, the `byte` is automatically converted to a `Byte`. This is called "
boxing". Similarly, when a `byte` is expected but a `Byte` is provided, the `Byte` is automatically converted to
a `byte`. This is called "unboxing".

If there are two functions with the same name, one of them takes a `byte` and the other takes a `Byte`, boxing or
unboxing is applied.

See [BoxingTest](numbers/src/test/java/numbers/BoxingTest.java).

The same applies to the other number types.

# Number class

All boxed types implement the `Number` interface, which defines a number of function.

When a primitive type is passed to a function which accepts an object, the primitive type is automatically boxed to the
corresponding class type, which implements the Number interface.

See [NumberClassTest](numbers/src/test/java/numbers/NumberClassTest.java).

# Types resulting from addition

This gives us the tools needed to see what happens when we add two numbers of the same type.

Adding two `int`, `long`, `float` and `double` produce a number of the same type.

But adding two `byte` or `short` always produce an `int`.

See [AdditionSameTypeTest](numbers/src/test/java/numbers/AdditionSameTypeTest.java).

# Type resulting from increment

The operator `++` preserve the types.

See [PlusPlusTest](numbers/src/test/java/numbers/PlusPlusTest.java).

# Overflow

Overflow wraps around for `byte`,`short`,`int` and `long`.

For `float` and `double`, it can keep the same value or go to positive infinity, or negative infinity when going the
other way.

See [OverflowTest](numbers/src/test/java/numbers/OverflowTest.java).

# Assigning values to variables

The following suffix are available for some types: "L" for `long`, "F" for `float` and "D" for `double`.

An integer declared without a suffix is an `int`, and a floating point value declared without a suffix is a `double`.

There are no suffix for `byte` and `short`, and they must be declared with an explicit cast, unless the value is
assigned to a variable of that type, or returned from a function, and the value is in the valid range for that type.

See [AssignValuesTest](numbers/src/test/java/numbers/AssignValuesTest.java).

# Explicit and implicit cast

In this list: `byte`, `short`, `int`, `long`, `float`, `double`, casting to a type on the right is implicit, but casting
to a type on the left must be specified explicitly. For example, casting `int` to `short` or `byte` must be specified
explicitly, but casting a `long` to a `float` or `double` is done implicitly.

See [CastTest](numbers/src/test/java/numbers/CastTest.java).

# Cast safety

Explicit cast are always unsafe, because they will lose bits and therefore information.

Implicit casts to `short`, `int`, or `long` are always safe, because they preserve the value.

But implicit casts to `float` and `double` are surprising, because precision loss can occur. In particular, the cast
from `long` to `float` is implicit, but a long is made of 64 bits and a `float` is made only on 32 bits, so most `long`
values cannot be represented as a `float`. Only cast from `int` to `double` is actually safe.

See [DangerousImplicitCastTest](numbers/src/test/java/numbers/DangerousImplicitCastTest.java).

# Down-casting integrals

When promoting a `byte`, `short`, `int`, or `long` to a larger type, sign bit extension is imply to preserve negative
values.

When down-casting to a smaller type, all the extraneous bits are simply ignored.

When down-casting a `double` to a `float`, special rules are applied to obtain miningful values while switching from a
64-bit IEEE 754 representation to a 32-bit IEEE 754 representation.

When down-casting a `double` or a `float` to an integral type, the value assigned to the integral type is the closest
value. The exact way this works is more complicated than for two's complement representation.

See [ExplicitCastTest](numbers/src/test/java/numbers/ExplicitCastTest.java).

# Number interface

The number interface defines the following methods:

- `byte byteValue()`
- `short shortValue()`
- `int intValue()`
- `long longValue()`
- `float floatValue()`
- `double doubleValue()`

which are therefore available on `Byte`, `Short`, `Integer`, `Long`, `Float` and `Double` and are implemented by
implicity or explicits casts, as needed.

See [NumberTest](numbers/src/test/java/numbers/NumberTest.java).

# Equality

Equality on a primitive type is computed by examining the value, but equality on a boxed type is computed by examining
the object reference.

Small and common numbers are kept in a constant pool so that boxing and unboxing is more efficient, but larger numbers
are not part of the pool, so when comparing boxed numbers using `==`, the result with vary.

In practice, it is better to use `==` when comparing primitive types, but `equals()` when comparing boxed types.

See [EqualityTest](numbers/src/test/java/numbers/EqualityTest.java).

# `byte`, `Byte`, `String`

## `byte` to `Byte` and back

To create a `Byte` from a `byte`, there are three possibilities:

- implicit boxing
- Byte constructor
- `valueOf()` static method

Note that the implicit constructor and the static method will use objects from the constant pool, but the constructor is
guaranteed to return a new and different instance.

To create a `byte` from a `Byte`, there are two possibilities:

- implicit boxing
- `byteValue()` defined by the `Number` interface

See [ByteByteTest](numbers/src/test/java/numbers/ByteByteTest.java).

## `Byte` to `String` and back

To convert a `Byte` to a `String`, there is only one possibility:

- `Byte.toString()`

But to convert a  `String` to a `Byte`, there are multiple possibilities:

- XXX
- XXX
- XXX
- XXX

See [ByteStringTest](numbers/src/test/java/numbers/ByteStringTest.java).

-----------------

The `Number` interface extends `Serializable` and declare the four following functions:

- `int intValue()`
- `long longValue()`
- `float floatValue()`
- `double doubleValue()`

A number of classes implement `Number`, among which classes for every numeric primitive type:

This means that it is possible to extract an `int`, a `long`, a `float` or a `double` primitive value from any numbers.
But `Number` offers no direct way to extract a `short` or a `byte`.

Also note that the `char` primitive type does not appear in this list, because `Character` does not implement `Number`.

Each of these classes define a `MIN_VALUE` and `MAX_VALUE` constant, which are defined as the primitive value, a `SIZE`
constant, which contains the size of the type in bits, and a `BYTES` constant, which contains the size of the type in
bytes.

Note that the size of a byte is 1 byte, a short is twice as large as a byte, an integer is twice as large as a short,
and a long is twice as large as an integer. A float is the siwe of an integer, and a double is the size of a long.

For example, `Byte.MIN_VALUE` is of type `byte`, not `Byte`.

See [MinAndMaxTest](MinAndMaxTest) and [SizeTest](SizeTest).

The conversion from primitive type to class type is known as "boxing", and happens most of the type automatically, but
it is possible to have different behaviors for the primitive type and the boxed type using overlading.

In [BoxingTest](BoxingTest), two version of `f` are defined, one for `int` and another for `Integer`, and each version
is called with the corresponding type. But `g` is defined only for `int`, so when called with an `Integer`, the value is
unboxed. And `h` is defined only for `Integer`, so when called with an `int`, the value is boxed.

Equality on primitive type is computed by value, but boxed types are objects, and equality on them is done by reference.

In [EqualityTest](EqualityTest), we define a `box` function which takes an `int` and returns the same value boxed to an
`Integer`. Then we use this to compare by equality and see if the two resulting objects are different or not.

Unfortunately, for small values, Java uses a predefined constant pool, so the boxed value of 5 are always the same
instance of Integer. But this is only accidental, and Java cannot maintain such a pool for any arbitrary Integer value.
So for a large value, such as `Integer.MAX_VALUE-5`, the instances of Integer are really different.

As a rule, always use `==` for comparing primitive values, and always use `equals` for comparing boxed values, but don't
forget that boxed values can also be `null`, so it is better to use a helper function such as `Objects::equals`
that knows about `null` values. See [GoodEqualityTest](GoodEqualityTest).

Arithmetic on bytes and shorts always produces integers, which are truncated to bytes or short as needed. Arithmetic on
long, float and double produces long, float and double respectively. See [ArithmeticTest](ArithmeticTest).

As a result, when performing arithmetic on bytes and shorts, cast are necessary.
See [ArithmeticCastTest](ArithmeticCastTest).

Unary operators preserve the type. See [PlusPlusTest](PlusPlusTest).

Upcasting is implicit, but downcasting must be explicit. See [UpDownCastingTest](UpDownCastingTest). Here are the
required explict downcast. In the following list, going from the type on the right to a type on the left requires an
explicit cast: `byte`, `short`, `int`, `long`, `float`, `double`.

Curiously, moving from a long to a float does not require an explicit cast, even though all longs cannot be represented
exactly as floats, so the implicit cast from long to float is suprising. See [LongToFloatTest](LongToFloatTest).

Overflows do not throw exceptions, and wrap around for `byte`, `short`, `int`, `long`, or keep the same value
for `float`
and `double`. See [OverflowTest](OverflowTest).

Now will look at the Number interfaces functions (`byteValue()`, `shortValue()`, `intValue()`, `longValue()`
, `floatValue()`, `doubleValue()`) on `Byte`, `Short`, `Integer`, `Long`, `Float` and `Double`.

There is not much to say about them actually, because for these types, they are actually equivalent to the corresponding
explicit casts.

But the the explicit cast themselves can be surprising. See [ExplicitCastTest](ExplicitCastTest). This is because
downcasting only keeps the lowest bits of the larger type, and reinterpret them as complement 2 notation.

So for example, `Long.MAX_VALUE` has the most significant bit set to 0, and all the other bits set to 1. Downcasting to
an integer keeps the 32 least significant bits, which are all set to 1, which is -1 in complement 2 notation. For
`Long.MIN_VALUE`, the most significant bit is set to 1, and all the other bits are set to 0, so when keeping the 32
least significant bits and reinterpreting them as an integer, this yields 0.

Downcasting `float` and `double` to integral types is even more confoluted, because `float` and `double` use the
IEEE-754 encoding, which has nothing to do with complement 2 notation. So a transformation procedure is used to produce
the closes integral type. That's all good, but IEEE-754 also defines special values for positive infinity, negative
infinity, not a number.

Not a number (`Float.NaN` and `Double.NaN`) are converted to 0 for every integral type.

Positive infinity is converted to `Long.MAX_VALUE` for cast to `long` and `Integer.MAX_VALUE` for cast to `int`. For
`short` and `byte`, simple truncation is used, which yields -1.

Negative infinity is converted to `Long.MIN_VALUE` for cast to `long` and `Integer.MAX_VALUE` for cast to `int`.
For `short` and `byte`, simple truncation is used, which yieds 0.

Conversion of NaN, positive infinity and negative infinity from float to double and from double to float preserves the
semantics, since these fields can be mapped from one type to another.

The smallest normal value on a `float` is not the same as the smallest normal value of a `double`. The smallest normal
value of a `double` is smaller than the smallest normal value of a `float`. Conversion of the smallest normal value of a
float to a double and back preserves the value. Conversion of the smallest normal value of a double to a float yields 0.




 
