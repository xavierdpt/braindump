This repository holds unit tests for
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.0-rc2</version>
<dependency>
```

We use JaCoCo in Maven to compute and display the lines covered by unit tests.

This requires a few tricks.

First, we need to make the classes in the compile dependencies available to JaCoCo. To do this, we use `maven-dependency-plugin` `unpack-dependencies` to extract the compile dependencies in `target/classes`.

To display the sources, JaCoCo only looks at the project's source directory, and we don't want to default source directory with that, so we set the source directory to `target/sources-for-coverage`, and we use `maven-dependency-plugin` `unpack-dependencies` a second time to extracts the sources of the dependency there (using the `source` qualifier to get those).

But we don't want to compile those, so we bind that part to a phase that occurs after `compile` but before JaCoCo needs them to compute the `report`.

If you want to add your own sources, custom sources, you will have to manually copy them to the tweaked source directory to have them compiled too.
