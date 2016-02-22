package indi.chenk.monitor.notify;

import indi.chenk.monitor.notify.impl.SMSNotify;
import indi.chenk.monitor.util.PropertyUtil;
import indi.chenk.monitor.util.SMSUtil;

public class NotifyFactory {

	/**
	 * 现在只支持SMS
	 * @param type
	 * @return
	 */
	public static Notify getInst(String type) {
		String account = PropertyUtil.getProp("sms_account");
		String token = PropertyUtil.getProp("sms_token");
		String appId = PropertyUtil.getProp("sms_appid");
		String templateId = PropertyUtil.getProp("sms_tempid");
		String ip = PropertyUtil.getProp("sms_ip");
		String port = PropertyUtil.getProp("sms_port");
		SMSUtil.init(account,token , appId, templateId, ip, port);
		return new SMSNotify();
	}
}
