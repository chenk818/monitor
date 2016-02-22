package indi.chenk.monitor.notify;

import indi.chenk.monitor.notify.impl.SMSNotify;

public class NotifyFactory {

	/**
	 * 现在只支持SMS
	 * @param type
	 * @return
	 */
	public static Notify getInst(String type) {
		return new SMSNotify();
	}
}
