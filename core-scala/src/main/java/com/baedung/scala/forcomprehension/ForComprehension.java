package com.baedung.scala.forcomprehension;

import java.util.Arrays;
import java.util.List;

public class ForComprehension {
    public static void main(String[] args) {
        final List<TestResult> results =
                Arrays.asList(
                        new TestResult("test 1",10, 10),
                        new TestResult("test 2",2, 6)
                );
        int totalPassedAssertsInSucceededTests = 0;
        for (int i = 0; i < results.size(); i++) {
            final TestResult result = results.get(i);
            if (result.isSucceeded()) {
                totalPassedAssertsInSucceededTests += result.getSuccessfulAsserts();
            }
        }

        final long totalPassedAssertsInSucceededTests1 =
                results.stream()
                        .filter(TestResult::isSucceeded)
                        .mapToInt(TestResult::getSuccessfulAsserts)
                        .sum();
    }

    private static class TestResult {
        private String id;
        private boolean succeeded;
        private int successfulAsserts;
        private int totalAsserts;

        public TestResult(String id, int successfulAsserts, int totalAsserts) {
            this.id = id;
            this.succeeded = successfulAsserts == totalAsserts;
            this.successfulAsserts = successfulAsserts;
            this.totalAsserts = totalAsserts;
        }

        public String getId() {
            return id;
        }

        public boolean isSucceeded() {
            return succeeded;
        }

        public int getSuccessfulAsserts() {
            return successfulAsserts;
        }

        public int getTotalAsserts() {
            return totalAsserts;
        }
    }
}
