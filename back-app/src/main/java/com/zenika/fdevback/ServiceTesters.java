package com.zenika.fdevback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.HttpURLConnection;
import java.net.URL;

class ServiceTesters {
	private static final Logger logger = LogManager.getLogger(ServiceTesters.class);

	static boolean checkService(ServiceCheckRequest request) {
		try {
			URL u = new URL(request.getServiceURI());
			HttpURLConnection huc = (HttpURLConnection)u.openConnection();

			huc.setConnectTimeout(2000);
			// We don't need the body, only the response code
			huc.setRequestMethod("HEAD");

			// Pretend to be a browser, to avoid being rejected
			huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			huc.setInstanceFollowRedirects(true);
			huc.connect();

			int code = huc.getResponseCode();

			logger.trace("Response code is: " + code);
			return code >= 200 && code <= 299;
		} catch (Exception e) {
			return false;
		}
	}
}
