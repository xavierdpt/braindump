This repository holds unit tests for OpenJDK 8 when installed on Ubuntu

We use JaCoCo in Maven to compute and display the lines covered by unit tests.

This requires a few tricks.

First, we need to make the classes in the compile dependencies available to JaCoCo. To do this, we extract the classes from rt.jar into `target/classes`.

To display the sources, JaCoCo only looks at the project's source directory, and we don't want to default source directory with that, so we set the source directory to `target/sources-for-coverage`, and we extract the OpenJDK sources from src.zip in there.
