# Introduction

In hc4, InternalHttpClient#doExecute method will throw a ProtocolException if arg[0] (HttpHost) is null.  

But in hc5, if HttpHost is null, InternalHttpClient will determine the host from ClassicHttpRequest, if either arg[0] or the result of determineHost is null, InternalHttpClient will throw a ProtocolException.

Skywalking hc5 plugin works as same as hc4 plugin: if the arg[0] is null, skip create the exitSpan. this will cause a bug in hc5: when the HttpHost is null but InternalHttpClient determine the host from ClassicHttpRequest, InternalHttpClient will send the request but Skywalking will not record it.

Use this MRE can easily recur this bug.

1. visit localhost:8080/haveSpan first.
2. then visit localhost:8080/noSpan.

The haveSpan trace has two spans: tomcat and hc5, but the noSpan trace only has tomcat span.

