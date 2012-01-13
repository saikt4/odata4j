package org.odata4j.jersey.consumer.behaviors;

import org.odata4j.core.ODataConstants;
import org.odata4j.jersey.consumer.ODataClientRequest;

public class MethodTunnelingBehavior extends BaseClientBehavior {

  private final String[] methodsToTunnel;

  public MethodTunnelingBehavior(String... methodsToTunnel) {
    this.methodsToTunnel = methodsToTunnel;
  }

  @Override
  public ODataClientRequest transform(ODataClientRequest request) {
    String method = request.getMethod();
    for (String methodToTunnel : methodsToTunnel) {
      if (method.equals(methodToTunnel)) {
        return request.header(ODataConstants.Headers.X_HTTP_METHOD, method).method("POST");
      }
    }
    return request;
  }

}