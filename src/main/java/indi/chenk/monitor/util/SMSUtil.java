package indi.chenk.monitor.util;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.cloopen.rest.sdk.CCPRestSDK;


public class SMSUtil {

	private static CCPRestSDK restAPI;
	private static String templateId;

	private static Logger logger = Logger.getLogger(SMSUtil.class);

	public static void init(String account, String token, String appId,
			String templateId, String ip, String port) {
		if (null != restAPI) {
			return;
		}
		restAPI = new CCPRestSDK();
		restAPI.init(ip, port);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(account, token);// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(appId);// 初始化应用ID
		SMSUtil.templateId = templateId;
	}

	/**
	 * 向指定号码发送信息
	 * 
	 * @param to
	 *            接收的手机号
	 * @param datas
	 *            发送的信息，对应模板里面的变量，按顺序对应
	 
	 * @return
	 */
	public static boolean sendSMS(String to, String... datas) {
		HashMap<String, Object> result = restAPI.sendTemplateSMS(to,
				templateId, datas);
		if ("000000".equals(result.get("statusCode"))) {
			return true;
		} else {
			logger.error("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
			return false;
		}
	}
}
