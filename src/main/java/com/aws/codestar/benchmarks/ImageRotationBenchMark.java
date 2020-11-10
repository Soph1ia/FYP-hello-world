package com.aws.codestar.benchmarks;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.process.ImageProcessingOperations;
import org.apache.catalina.startup.ClassLoaderFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageRotationBenchMark {
    public static String RunResultsForImageRotationBenchmark = new String( "no results yet");

    @State(Scope.Thread)
    public static class ClassValues {
        public static final Logger logger = Logger.getLogger(ImageRotationBenchMark.class.getName());
        static final ImageProcessingOperations op = new ImageProcessingOperations();

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void rotateAndResizeImage(Blackhole bm){
        try {
            BufferedImage image = ImageIO.read(new File("image.jpg"));
            BufferedImage rotatedImage = JDeli.process(ClassValues.op.rotate(90), image);
            BufferedImage resizedImage = JDeli.process(ClassValues.op.scale(1), rotatedImage);
            bm.consume(resizedImage);
        } catch (Exception e) {
            ClassValues.logger.log(Level.WARNING, " error occurred rotating the image-> " + e);
        }

    }

    public void  main() throws Exception {
        Options opt = new OptionsBuilder()
                .include(ImageRotationBenchMark.class.getSimpleName())
                .forks(1)
                .build();

        Collection<RunResult> runResults = new Runner(opt).run();
        RunResultsForImageRotationBenchmark = runResults.toString();
    }
}
