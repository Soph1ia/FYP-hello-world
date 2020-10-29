package com.aws.codestar.projecttemplates.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.aws.codestar.projecttemplates.GatewayResponse;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.math.BigInteger;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;

/**
 * Handler for requests to Lambda function.
 */
public class HelloWorldHandler implements RequestHandler<Object, Object> {

    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Long startTime = System.currentTimeMillis();

        BigInteger numberToCalculate = new BigInteger("2000");

        BigInteger result = factorial(numberToCalculate);

        Long endTime  = System.currentTimeMillis();

        Long timeTaken = endTime-startTime;

        return new GatewayResponse(new JSONObject().put("Output", "Result of 2000! is: " + result +" time taken : " + timeTaken).toString(), headers, 200);
    }

        public BigInteger factorial(BigInteger num) {
            BigInteger total = new BigInteger("1");
            if( num.equals(new BigInteger("1"))) {
                return new BigInteger("1");
            } else {
                total =  num.multiply(factorial(num.subtract(new BigInteger("1"))));
            }
            return total;
        }


}
