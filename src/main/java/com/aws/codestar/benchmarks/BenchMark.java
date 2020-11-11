/*
package com.aws.codestar.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigInteger;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BenchMark {

    @State(Scope.Thread)
    public static class MyValues {
        public static final Logger logger = Logger.getLogger(BenchMark.class.getName());
        public static final BigInteger num = new BigInteger("20");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void test_method(Blackhole bh) {
        BigInteger result = factorial(MyValues.num);
        bh.consume(result);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMark.class.getSimpleName())
                .forks(1)
                .build();
        Collection<RunResult> runResults = new Runner(opt).run();
        MyValues.logger.log(Level.INFO, " Benchmark has run, the run results are: " + formatOutputResult(runResults));
    }

    private BigInteger factorial(BigInteger num) {
        BigInteger total;
        if (num.equals(new BigInteger("1"))) {
            return new BigInteger("1");
        } else {
            total = num.multiply(factorial(num.subtract(new BigInteger("1"))));
        }
        return total;
    }

    private static String formatOutputResult(Collection<RunResult> runResults) {
        StringBuilder output = new StringBuilder();
        output.append("The Results of the Image Rotate Benchmark is: ");
        for(RunResult r: runResults) {
            output.append("The BenchMark Results " + r.getBenchmarkResults().stream().map(BenchmarkResult::getBenchmarkResults));
            output.append("The Aggregated Results " + r.getAggregatedResult().toString());
            output.append("The Params are " + r.getParams().toString());
            output.append("The Primary Result " + r.getPrimaryResult());
            output.append("The Secondary Result " + r.getSecondaryResults());
        }
        return output.toString();
    }
}
*/
