package indi.chenk.monitor;

import indi.chenk.monitor.notify.Notify;
import indi.chenk.monitor.notify.NotifyFactory;
import indi.chenk.monitor.util.PropertyUtil;

import java.io.*;

import org.nutz.http.Http;
import org.nutz.http.Response;

public class Main {

	private boolean _showUsage = false;

	private String _url;
	private int _timeout = 2000;
	
	
	public static void main(String[] args) {

		if (null == args || args.length == 0) {
			System.out
					.println("Use \"java -jar monitor.jar --help\" for more information.");
		}
		Main main = new Main();
		main.parseArgs(args);
		main.start();
	}
	
	private void parseArgs(String[] args) {
		for (String arg : args) {
			if ("--help".equals(arg) || "-?".equals(arg)) {
				_showUsage = true;
				break;
			}
			if ("--url".startsWith(arg) ) {
				_url = arg.split("=")[1];
			}
			if ("--timeout".startsWith(arg) ) {
				try {
					_timeout = Integer.valueOf(arg.split("=")[1]) * 1000;
				}catch(Exception e) {
				}
			}
			if (arg.startsWith("--config")) {
				PropertyUtil.addProp(arg.split("=")[1]);
				break;
			}
			if (arg.startsWith("--notify")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("notify", kv[1]);
			}
			if (arg.startsWith("--receivers")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("receivers", kv[1]);
			}
			if (arg.startsWith("--sms_account")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("sms_account", kv[1]);
			}
			if (arg.startsWith("--sms_token")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("sms_token", kv[1]);
			}
			if (arg.startsWith("--sms_appid")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("sms_appid", kv[1]);
			}
			if (arg.startsWith("--sms_ip")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("sms_ip", kv[1]);
			}
			if (arg.startsWith("--sms_port")) {
				String[] kv = arg.split("=");
				PropertyUtil.addProp("sms_port", kv[1]);
			}
		}
	}
	
	private void start() {
		if (_showUsage) {
			usage();
		}
		
		Response resp = Http.get(_url, _timeout);
		if(resp.getStatus() != 200) {
			String mesg = "健康检查返回状态码" + resp.getStatus();
			Notify notify = NotifyFactory.getInst(PropertyUtil.getProp("notify"));
			String[] receivers = PropertyUtil.getProp("receivers").split(",");
			notify.send(mesg, receivers);
		}
	}
	
	
	
	private void usage() {
		String usageResource = "indi/chenk/monitor/usage.txt";
		InputStream usageStream = getClass().getClassLoader()
				.getResourceAsStream(usageResource);

		if (usageStream == null) {
			System.err.println("ERROR: detailed usage resource unavailable");
			System.exit(0);
		}

		BufferedReader buf = null;
		try {
			buf = new BufferedReader(
					new InputStreamReader(usageStream, "UTF-8"));
			String line;

			while ((line = buf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
		} finally {
			close(buf);
		}
		System.exit(0);
	}
	
	public static void close(Closeable c) {
		if (c == null) {
			return;
		}
		try {
			c.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
}
