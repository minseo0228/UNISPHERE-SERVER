package org.unisphere.unisphere;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class StreamLambdaHandler implements RequestStreamHandler {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

	static {
		try {
			handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
					.defaultProxy()
					.asyncInit()
					.springBootApplication(UnisphereApplication.class)
					.buildAndInitialize();
		} catch (ContainerInitializationException e) {
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	@Override
	public void handleRequest(java.io.InputStream input, java.io.OutputStream output,
			com.amazonaws.services.lambda.runtime.Context context) throws java.io.IOException {
		handler.proxyStream(input, output, context);
	}
}
