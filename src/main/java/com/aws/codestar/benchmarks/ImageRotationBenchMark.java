package com.aws.codestar.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
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

public class ImageRotationBenchMark {
    public static String RunResultsForImageRotationBenchmark = new String( "no results yet");

    @State(Scope.Thread)
    public static class ClassValues {
        public static final Logger logger = Logger.getLogger(ImageRotationBenchMark.class.getName());
        static final int scaledWidth = 1024;
        static final int scaledHeight = 1000;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void rotateAndResizeImage(Blackhole bm){
        try {
            // reads in the image
            BufferedImage image = ImageIO.read(new File("image.jpg"));
            // Creates output images
            BufferedImage outputImage = new BufferedImage(ClassValues.scaledWidth, ClassValues.scaledHeight, image.getType());

            //Scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(image, 0,0, ClassValues.scaledWidth,ClassValues.scaledHeight,null);
            g2d.dispose();

            bm.consume(outputImage);
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
        ClassValues.logger.log(Level.INFO, " The image has been processed, the results are" + runResults);
    }
}
