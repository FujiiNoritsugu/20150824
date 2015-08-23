package app;

import org.apache.camel.builder.RouteBuilder;

public class TestRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
        from("stream:in?promptMessage=Enter : ")
        .process(new TestProcessor())
        .process(new TestProcessor())
        .to("stream:out");;
	}

}
