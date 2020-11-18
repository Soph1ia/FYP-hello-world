package com.aws.codestar.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.aws.codestar.benchmarks.BenchMark.printFile;

public class ImageRotationBenchMark {
    @State(Scope.Thread)
    public static class ClassValues {
        public static final Logger logger = Logger.getLogger(ImageRotationBenchMark.class.getName());
        static final int scaledWidth = 1024;
        static final int scaledHeight = 1000;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void rotateAndResizeImage(Blackhole bm) {
        try {
            // reads in the image
            BufferedImage image = ImageIO.read(new File("image.jpg"));
            // Creates output images
            BufferedImage outputImage = new BufferedImage(ClassValues.scaledWidth, ClassValues.scaledHeight, image.getType());

            //Scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(image, 0, 0, ClassValues.scaledWidth, ClassValues.scaledHeight, null);
            g2d.dispose();

            bm.consume(outputImage);
        } catch (Exception e) {
            ClassValues.logger.log(Level.WARNING, " error occurred rotating the image-> " + e);
        }

    }

    public void main() throws Exception {
        Options opt = new OptionsBuilder()
                .include(ImageRotationBenchMark.class.getSimpleName())
                .forks(1)
                .result("imageRotationBenchmarkResult.json")
                .build();
        new Runner(opt).run();

        //prints out the results to the console.
        ClassValues.logger.log(Level.INFO, "RESULTS_OF_BENCHMARK");
        printFile("imageRotationBenchmarkResult.json");
        ClassValues.logger.log(Level.INFO, "Image Rotation BenchMark has been run");
    }


    public static String formatResultsForImageRotation(Collection<RunResult> runResults) {
        StringBuilder output = new StringBuilder();
        output.append("The Results of the Benchmark Class Benchmark is: ");
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
