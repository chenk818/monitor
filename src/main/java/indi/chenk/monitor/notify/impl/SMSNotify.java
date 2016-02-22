package indi.chenk.monitor.notify.impl;

import indi.chenk.monitor.notify.Notify;
import indi.chenk.monitor.util.SMSUtil;

public class SMSNotify implements Notify {

	@Override
	public void send(String mesg, String[] receivers) {
		for(String to:receivers) {
			SMSUtil.sendSMS(to, mesg);
		}
		
	}

}
