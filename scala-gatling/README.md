### Relevant Articles:
- [Testing With Gatling Using Scala]()

### Gatling Executions
From this module's folder, we first compile tests: `sbt test:compile`

Then run the simulation: `sbt 'Gatling/testOnly com.baeldung.gatling.PeakLoadSimulation'`

**Notes**: in order to spin-up the right API this example uses, we also need to start the server:
`sbt resApi/run` from root folder
