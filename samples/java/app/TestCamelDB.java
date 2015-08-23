package app;

import java.util.Timer;

public class TestCamelDB {
	public static void main(String [] args){
		Timer timer = new Timer();
		timer.schedule(new TestCamelDBTask(), 1000, 1000);
	}
}
