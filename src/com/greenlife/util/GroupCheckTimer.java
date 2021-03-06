package com.greenlife.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class GroupCheckTimer{
	public Timer timer;
	public void timerStart(){
		timer = new Timer();
		/*
		Date datetime=new Date();
		Date midnightDate=new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			midnightDate = sdf2.parse(sdf1.format(datetime)+" 23:00:00");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long in=midnightDate.getTime()-datetime.getTime();*/
		timer.schedule(new GroupCheckTask(), 1000*60, 1000*60*60);
	}
	public void timerStop(){
		if(timer!=null){ 
			timer.cancel();
		}
	}
}