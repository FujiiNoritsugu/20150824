package app;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        body = body + "だお。";
        exchange.getIn().setBody(body);
    }

}
