package com.yzzzzun.openmat.utils;

import javax.servlet.http.HttpSession;

public class SessionUtils {
	private static final String LOGIN_SESSION_KEY = "LOGIN_ID";

	public static void setLoginSession(HttpSession session, String loginId) {
		session.setAttribute(LOGIN_SESSION_KEY, loginId);
	}

	public static String getLoginSession(HttpSession session) {
		return (String)session.getAttribute(LOGIN_SESSION_KEY);
	}
}
