# scala-tutorials

In this repository, we have classified the tests in 4 categories. 

| Category | Description                                                                                                                                                   |
| -- |---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Unit Tests | Smallest unit of testing, that are not dependent on external tools or services                                                                                |
| Integration Tests | IntegrationTests means those tests that use some automatic setup within our environment like in-memory Mongo, h2 database etc which don't need explicit setup |
| Live Tests | Tests that depends on some external services (like httpbin.org, or some internet-based links)                                                                 |
| Manual Tests | The tests where we need to set up an environment explicitly(for e.g. docker), without which the tests can't be run                                             |


Here is a table describing about the different categories of tests and how they can be executed in this project.

| Category                | Sbt command       | Test class location                    | Test class name format                                                                                                  |
|-------------------------|-------------------|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| Unit Tests              | `sbt ci`           | `src/test/scala` or `src/test/scala-2` | No particular format restriction, but as a standard, filename ends with `Test`                                          |
| Integration Test (Only) | `sbt integrationTests`     | `src/it/scala` or `src/it/scala-2` | No format restriction, but as a standard, filename ends with `IntegrationTest`                                          |
| Unit & Integration Test | `sbt ciFull`     | `src/it/scala` or `src/it/scala-2` | No format restriction, but as a standard, filename ends with `IntegrationTest`. These exclude manual and live tests |
| Live Test               | `sbt liveTests`   | `src/it/scala` or `src/it/scala-2` | Test class name must end with `LiveTest`                                                                                |
| Manual Test             | `sbt manualTests` | `src/it/scala` or `src/it/scala-2` | Test class name must end with `ManualTest`                                                                              |

# Code fomatting

Before creating a PR, make sure the code is correctly formatted running `sbt scalafmt`. 
