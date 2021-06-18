The `Number` interface extends `Serializable` and declare the four following functions:

- `int intValue()`
- `long longValue()`
- `float floatValue()`
- `double doubleValue()`

A number of classes implement `Number`, among which classes for every numeric primitive type:

| Class | Primitive type |
|-------|----------------|
| `Byte` | `byte` |
| `Short` | `short` |
| `Integer` | `integer` |
| `Long` | `long` |
| `Float` | `float` |
| `Double` | `double` |

This means that it is possible to extract an `int`, a `long`, a `float` or a `double` primitive value from any numbers.
But `Number` offers no direct way to extract a `short` or a `byte`.

Also note that the `char` primitive type does not appear in this list, because `Character` does not implement `Number`.

Each of these classes define a `MIN_VALUE` and `MAX_VALUE` constant, which are defined as the primitive value, a `SIZE`
constant, which contains the size of the type in bits, and a `BYTES` constant, which contains the size of the type in
bytes.

| Class     | `MIN_VALUE`          | `MAX_VALUE`            | `SIZE` | `BYTES` |
|-----------|----------------------|------------------------|--------|---------|
| `Byte`    | -128                 | 127                    | 8      | 1       |
| `Short`   | -32768               | 32767                  | 16     | 2       |
| `Integer` | -2147483648          | 2147483647             | 32     | 4       |
| `Long`    | -9223372036854775808 | 9223372036854775807    | 64     | 8       |
| `Float`   | 1.4E-45              | 3.4028235E38           | 32     | 4       |
| `Double`  | 4.9E-324             | 1.7976931348623157E308 | 64     | 8       |

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




 