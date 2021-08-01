package com.salaryspringmvc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest httpServletRequest;
	@Autowired
	HttpServletResponse httpServletResponse;
	
	public Cookie createCookie(String name, String value, int days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(days*60*60*24);
		cookie.setPath("/");
		return cookie;
	}
	
	public Cookie getCookie(String name) {
		Cookie[] cookies = httpServletRequest.getCookies();
		if(null != cookies) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) return cookie;
			}
		}
		return null;
	}
}
