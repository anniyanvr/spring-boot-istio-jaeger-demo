package me.chanjar.istio_jaeger.foo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class IndexController {

  @Autowired
  private BarGreetingService barGreetingService;

  @RequestMapping("/")
  public String index(@RequestHeader HttpHeaders headers) {

    return headers(headers) + barGreetingService.greeting();
  }

  private String headers(HttpHeaders headers) {

    StringBuilder sb = new StringBuilder();
    sb.append("Foo Request headers:\n");

    Set<String> headerNames = headers.keySet();
    for (String headerName : headerNames) {
      List<String> headerValues = headers.getValuesAsList(headerName);
      sb.append('\t')
          .append(headerName).append(": ")
          .append(headerValues.toString()).append('\n');
    }

    return sb.toString();

  }

}