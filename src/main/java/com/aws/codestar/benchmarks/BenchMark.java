package com.aws.codestar.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchMark {

    @State(Scope.Thread)
    public static class MyValues {
        public static final Logger logger = Logger.getLogger(BenchMark.class.getName());
    }

    @Benchmark
    public void factorial_2000(Blackhole bh) {
        BigInteger result = factorial(new BigInteger("2000"));
        bh.consume(result);
    }
    @Benchmark
    public void factorial_3000(Blackhole bh) {
        BigInteger result = factorial(new BigInteger("3000"));
        bh.consume(result);
    }
    @Benchmark
    public void factorial_4000(Blackhole bh) {
        BigInteger result = factorial(new BigInteger("4000"));
        bh.consume(result);
    }

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMark.class.getSimpleName())
                .forks(1)
                .result("cpuBenchmarkResult.json")
                .build();
         new Runner(opt).run();

         // prints out the results to console.
        MyValues.logger.log(Level.INFO, "RESULTS_OF_BENCHMARK");
        printFile("cpuBenchmarkResult.json");
        MyValues.logger.log(Level.INFO, " The Factorial benchmark has run " );
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

    public static void printFile(String fileName){
        File f = new File(fileName);
        try {
            Scanner reader = new Scanner(f);

            while(reader.hasNext()){
                String data = reader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
