package net.client;

import java.util.Calendar;
import java.util.Queue;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class SampleListener extends Listener{
	Queue <Integer> index_queue, middle_queue;

	public SampleListener(Queue <Integer> a_queue, Queue <Integer> b_queue){
		super();
		index_queue = a_queue;
		middle_queue = b_queue;
	}
	
	public void onConnect(Controller controller){
		System.out.println("Connected");
		//controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	}

	int temp_index, temp_middle, counter = 1;

	public void onFrame(Controller controller){
		Frame frame = controller.frame();
		for(Finger finger : frame.fingers()){
			switch (finger.type()) {
			case TYPE_INDEX :
				temp_index += getBendOfFinger(finger);				
				break;
			case TYPE_MIDDLE:
				temp_middle += getBendOfFinger(finger);
			default:
				break;
			}
		}
		
		if(counter == 60){
//System.out.println("temp_index = " + temp_index);
//System.out.println("temp_middle = " + temp_middle);
			index_queue.add(new Integer(temp_index / counter));
			middle_queue.add(new Integer(temp_middle / counter));
System.out.println("index_bend = " + (temp_index / counter));
System.out.println("middle_bend = " + (temp_middle / counter));
//System.out.println("time = " + Calendar.getInstance().getTimeInMillis());
			temp_index = 0;
			temp_middle = 0;
			counter = 0;
		}else{
			counter++;
		}
	}
	
	private int getBendOfFinger(Finger finger){
		Vector mcpPos = finger.jointPosition(Finger.Joint.JOINT_MCP);
		Vector tipPos = finger.jointPosition(Finger.Joint.JOINT_TIP);
		double angle = mcpPos.angleTo(tipPos) * (180 / Math.PI);
		return (int)angle;
	}
	
}
