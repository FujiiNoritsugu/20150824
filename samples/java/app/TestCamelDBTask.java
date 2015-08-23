package app;

import java.util.TimerTask;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Person;

public class TestCamelDBTask extends TimerTask {

    ClassPathXmlApplicationContext context;

	public TestCamelDBTask(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");		
	}
	
	@Override
	public void run() {
        CamelContext camelContext = (CamelContext)context.getBean("camelContext1");
        ProducerTemplate pt = camelContext.createProducerTemplate();
        pt.sendBody("direct:makePerson", new Person("fujii","noritsugu"));
        
        //context.close();
	}

}
