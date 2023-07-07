

Run: 
```
mvn spring-boot:run
```

Exception:
```
java.util.concurrent.ExecutionException: software.amazon.awssdk.core.exception.SdkClientException: Cannot invoke "software.amazon.awssdk.http.SdkHttpRequest.method()" because "httpRequest" is null
        at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:396) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:2073) ~[na:na]
        at com.otelbug.demo.Receiver.receiveMessages(Receiver.kt:34) ~[classes/:na]
        at com.otelbug.demo.Receiver.receiveJob(Receiver.kt:25) ~[classes/:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
        at org.springframework.scheduling.support.ScheduledMethodRunnable.run(ScheduledMethodRunnable.java:84) ~[spring-context-6.0.10.jar:6.0.10]
        at org.springframework.scheduling.support.DelegatingErrorHandlingRunnable.run(DelegatingErrorHandlingRunnable.java:54) ~[spring-context-6.0.10.jar:6.0.10]
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539) ~[na:na]
        at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java:305) ~[na:na]
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:305) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
        at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
Caused by: software.amazon.awssdk.core.exception.SdkClientException: Cannot invoke "software.amazon.awssdk.http.SdkHttpRequest.method()" because "httpRequest" is null
        at software.amazon.awssdk.core.exception.SdkClientException$BuilderImpl.build(SdkClientException.java:111) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.internal.util.ThrowableUtils.asSdkException(ThrowableUtils.java:98) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AsyncExecutionFailureExceptionReportingStage.lambda$execute$0(AsyncExecutionFailureExceptionReportingStage.java:51) ~[sdk-core-2.20.95.jar:na]
        at java.base/java.util.concurrent.CompletableFuture.uniHandle(CompletableFuture.java:934) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture$UniHandle.tryFire(CompletableFuture.java:911) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147) ~[na:na]
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AsyncRetryableStage$RetryingExecutor.lambda$attemptExecute$1(AsyncRetryableStage.java:177) ~[sdk-core-2.20.95.jar:na]
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147) ~[na:na]
        at software.amazon.awssdk.core.internal.http.pipeline.stages.MakeAsyncHttpRequestStage.lambda$null$0(MakeAsyncHttpRequestStage.java:105) ~[sdk-core-2.20.95.jar:na]
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147) ~[na:na]
        at software.amazon.awssdk.core.internal.http.pipeline.stages.MakeAsyncHttpRequestStage.completeResponseFuture(MakeAsyncHttpRequestStage.java:238) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.internal.http.pipeline.stages.MakeAsyncHttpRequestStage.lambda$executeHttpRequest$3(MakeAsyncHttpRequestStage.java:163) ~[sdk-core-2.20.95.jar:na]
        at java.base/java.util.concurrent.CompletableFuture.uniHandle(CompletableFuture.java:934) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture$UniHandle.tryFire(CompletableFuture.java:911) ~[na:na]
        at java.base/java.util.concurrent.CompletableFuture$Completion.run(CompletableFuture.java:482) ~[na:na]
        ... 3 common frames omitted
Caused by: java.lang.NullPointerException: Cannot invoke "software.amazon.awssdk.http.SdkHttpRequest.method()" because "httpRequest" is null
        at io.opentelemetry.instrumentation.awssdk.v2_2.AwsSdkHttpAttributesGetter.getHttpRequestMethod(AwsSdkHttpAttributesGetter.java:31) ~[opentelemetry-aws-sdk-2.2-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.awssdk.v2_2.AwsSdkHttpAttributesGetter.getHttpRequestMethod(AwsSdkHttpAttributesGetter.java:17) ~[opentelemetry-aws-sdk-2.2-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.api.instrumenter.http.HttpCommonAttributesExtractor.onStart(HttpCommonAttributesExtractor.java:48) ~[opentelemetry-instrumentation-api-semconv-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.api.instrumenter.http.HttpClientAttributesExtractor.onStart(HttpClientAttributesExtractor.java:107) ~[opentelemetry-instrumentation-api-semconv-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.api.instrumenter.Instrumenter.doStart(Instrumenter.java:178) ~[opentelemetry-instrumentation-api-1.27.0.jar:1.27.0]
        at io.opentelemetry.instrumentation.api.instrumenter.Instrumenter.start(Instrumenter.java:131) ~[opentelemetry-instrumentation-api-1.27.0.jar:1.27.0]
        at io.opentelemetry.instrumentation.awssdk.v2_2.TracingExecutionInterceptor.createConsumerSpan(TracingExecutionInterceptor.java:338) ~[opentelemetry-aws-sdk-2.2-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.awssdk.v2_2.TracingExecutionInterceptor.afterConsumerResponse(TracingExecutionInterceptor.java:318) ~[opentelemetry-aws-sdk-2.2-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.awssdk.v2_2.TracingExecutionInterceptor.afterExecution(TracingExecutionInterceptor.java:292) ~[opentelemetry-aws-sdk-2.2-1.27.0-alpha.jar:1.27.0-alpha]
        at io.opentelemetry.instrumentation.awssdk.v2_2.autoconfigure.TracingExecutionInterceptor.afterExecution(TracingExecutionInterceptor.java:139) ~[opentelemetry-aws-sdk-2.2-autoconfigure-1.27.0-alpha.jar:1.27.0-alpha]
        at software.amazon.awssdk.core.interceptor.ExecutionInterceptorChain.lambda$afterExecution$10(ExecutionInterceptorChain.java:195) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.interceptor.ExecutionInterceptorChain.reverseForEach(ExecutionInterceptorChain.java:237) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.interceptor.ExecutionInterceptorChain.afterExecution(ExecutionInterceptorChain.java:195) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AfterExecutionInterceptorsStage.execute(AfterExecutionInterceptorsStage.java:26) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.core.internal.http.pipeline.RequestPipelineBuilder$AsyncRequestPipelineWrapper.lambda$execute$0(RequestPipelineBuilder.java:229) ~[sdk-core-2.20.95.jar:na]
        at software.amazon.awssdk.utils.FunctionalUtils.lambda$safeFunction$3(FunctionalUtils.java:92) ~[utils-2.20.95.jar:na]
        at java.base/java.util.concurrent.CompletableFuture$UniApply.tryFire(CompletableFuture.java:646) ~[na:na]
        ... 20 common frames omitted

```