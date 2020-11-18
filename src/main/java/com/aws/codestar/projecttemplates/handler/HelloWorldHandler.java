package com.aws.codestar.projecttemplates.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.codestar.benchmarks.BenchMark;
import com.aws.codestar.benchmarks.ImageRotationBenchMark;
import com.aws.codestar.projecttemplates.GatewayResponse;
import org.json.JSONObject;
import org.openjdk.jmh.runner.RunnerException;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class HelloWorldHandler implements RequestHandler<Object, Object> {


    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // CPU Benchmark - calculates 2000!,3000!,25000!
        BenchMark bm = new BenchMark();
        try {
           bm.main();
        } catch (RunnerException e) {
            e.printStackTrace();
        }

        // RAM Benchmark - rotates and resizes the image
        ImageRotationBenchMark irb = new ImageRotationBenchMark();
        try {
            irb.main();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new GatewayResponse(new JSONObject().put("Output", " Testing the static variables for image rotation class ==>  " /*+ ImageRotationBenchMark.RunResultsForImageRotationBenchmark */).toString(), headers, 200);
    }




}
