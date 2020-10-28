package com.aws.codestar.projecttemplates.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.aws.codestar.projecttemplates.GatewayResponse;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class HelloWorldHandler implements RequestHandler<Object, Object> {

    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

                long start = System.currentTimeMillis();


                int numberToCalculate = 2000;
                long fact = 0;

                for (int i=1; i<=numberToCalculate; i++) {
                    fact = fact * i;
                }
                long end  = System.currentTimeMillis();

                long timeTaken = end-start;


        return new GatewayResponse(new JSONObject().put("Output", "I say - Hello World!, time taken : " + timeTaken).toString(), headers, 200);
    }
}
