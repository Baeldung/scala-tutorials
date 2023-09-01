# scala-tutorials
This is the main repo for all the sample code used in the scala tutorials.

# Compiling and Running Tests
This repo uses a multi-module build with many sub modules.
To compile the entire module, you may use the command `sbt compile`. However, this loads all the modules and compiles all of them, which might take some time.
If you are interested in only a particular module, you can compile it by starting sbt shell and using the command `<sub-module-name>/compile`.
Similarly, you can run the tests per module as `<module-name>/test`. 

Here are some of the useful commands that can be used within tbe sbt shell

| SBT Command                 | Description                                                                                           | Example            |
|-----------------------------|-------------------------------------------------------------------------------------------------------|--------------------|
| `project <sub-module-name>` | Switch to a particular module. After this, the command `compile`, `test` etc runs only on that module | project scala_core |
| `<sub-module-name>/compile` | Compile only the provided module | scala_core/compile |
| `projects`                  | Lists all the sub modules |                    |
| `<sub-module-name>/run`      | Run the main class within the sub-module | scala_core/run |        

**Note: The project name may not be same as directory name. Instead, it is the `lay val` variable used to define each module**

# Test Naming Standards
In this repository, we have classified the tests in 4 categories. 

| Category | Description                                                                                                                                                   |
| -- |---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Unit Tests | Smallest unit of testing, that are not dependent on external tools or services                                                                                |
| Integration Tests | IntegrationTests means those tests that use some automatic setup within our environment like in-memory Mongo, h2 database etc which don't need explicit setup |
| Live Tests | Tests that depends on some external services (like httpbin.org, or some internet-based links) or require a running component (eg: starting a Spring Boot application)                                                                 |
| Manual Tests | The tests where we need to set up an environment explicitly(for e.g. docker), without which the tests can't be run                                             |


Here is a table describing about the different categories of tests and how they can be executed in this project.
Note that these commands are defined at the root level of the project and hence are accessible only from the root project(not directly within each submodule).

| Category                | Sbt command       | Test class location                    | Test class name format                                                                                                  |
|-------------------------|-------------------|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| Unit Tests              | `sbt ci`           | `src/test/scala` or `src/test/scala-2` | No particular format restriction, but as a standard, filename ends with `Test`                                          |
| Integration Test (Only) | `sbt integrationTests`     | `src/it/scala` or `src/it/scala-2` | No format restriction, but as a standard, filename ends with `IntegrationTest`                                          |
| Unit & Integration Test | `sbt ciFull`     | `src/it/scala` or `src/it/scala-2` | No format restriction, but as a standard, filename ends with `IntegrationTest`. These exclude manual and live tests |
| Live Test               | `sbt liveTests`   | `src/it/scala` or `src/it/scala-2` | Test class name must end with `LiveTest`                                                                                |
| Manual Test             | `sbt manualTests` | `src/it/scala` or `src/it/scala-2` | Test class name must end with `ManualTest`                                                                              |

# Code formatting

Before creating a PR, make sure the code is correctly formatted running `sbt scalafmt`. 
