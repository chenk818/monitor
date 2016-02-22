package indi.chenk.monitor.notify;

public interface Notify {

	/**
	 * 将通知内容发送给指定的人
	 * @param mesg  通知内容
	 * @param receivers 接收人，可以是多个
	 */
	public void send(String mesg,String[] receivers);
}
