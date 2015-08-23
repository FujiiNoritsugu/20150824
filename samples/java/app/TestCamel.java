package app;

import org.apache.camel.main.Main;

public class TestCamel {
	public static void main(String  [] args){
		Main main = new Main();
		main.addRouteBuilder(new TestRoute());
		try {
			main.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
