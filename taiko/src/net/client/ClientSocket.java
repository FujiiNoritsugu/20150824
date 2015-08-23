package net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.TimerTask;

public class ClientSocket extends TimerTask{

	Socket socket;
	BufferedReader stream;
	PrintWriter out;
	
	Queue <Integer> index_queue, middle_queue;

	public ClientSocket(Queue <Integer> a_queue, Queue <Integer> b_queue){
		index_queue = a_queue;
		middle_queue = b_queue;

	try{
		socket = new Socket("192.168.1.9", 23);
		stream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	} catch (IOException e) {
		e.printStackTrace();
	}

	}
	
	@Override
	public void run(){
//System.out.println("index_bend = " + index_bend.intValue());
//System.out.println("middle_bend = " + middle_bend.intValue());
		Integer index = index_queue.poll();
		Integer middle = middle_queue.poll();
//System.out.println("index_queue size = " + index_queue.size() + " middle_queue_size = " + middle_queue.size());
//System.out.println("index = " + index + " middle = " + middle);
		if(index != null && middle != null){
			String indexStr = getTargetString("a", index.intValue());
			String middleStr = getTargetString("b", middle.intValue());
			out.print(indexStr+middleStr);
			out.flush();
//System.out.println("indexStr = " + indexStr + " middleStr = " + middleStr);
		}
	}
	
	String getTargetString(String prefix, int value){
		//10Å`30ÇÃê›íËílÇ35Å`115ÇÃä‘Ç…é˚ÇﬂÇÈ
		if(value < 10){value = 10;}
		if(value > 30){value = 30;}
		value = (int) (((double)((value - 10.0)/(30.0 - 10.0))) * (115.0 - 35.0) + 35.0);
		String targetStr;

		if(value < 100){
			targetStr = prefix + "0" + value;
		}else{
			targetStr = prefix + value;
		}		
		
		return targetStr;
	}

	
}
