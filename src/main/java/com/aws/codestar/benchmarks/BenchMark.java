package com.aws.codestar.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
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
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void test_method(Blackhole bh) {
        BigInteger result = factorial(MyValues.num);
        bh.consume(result);
    }

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMark.class.getSimpleName())
                .forks(1)
                .build();
        Collection<RunResult> runResults = new Runner(opt).run();
        MyValues.logger.log(Level.INFO, " Benchmark has run, the run results are: " + runResults);
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
}
