package Phantom.AlarmCompute;

import java.util.Timer;

public class AlarmComputeStartUp {
	
	private static final long period = 40*1000;
	
	private static LogisticsCacheIniter tast = new LogisticsCacheIniter();
	
	private static Timer timer = new Timer(); //定时任务

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		timer.schedule(tast, 0, period);

	}

}
