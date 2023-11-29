package org.unisphere.unisphere.auth.domain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unisphere.unisphere.config.CookieConfig;

@Component
@RequiredArgsConstructor
@Slf4j
/*
  쿠키 관리를 담당하는 클래스
 */
public class CookieManager {

	private final CookieConfig cookieConfig;

	/**
	 * 쿠키를 생성하여 HttpServletResponse 객체에 추가한다.
	 *
	 * @param res   클라이언트에게 보낼 응답을 나타내는 HttpServletResponse 객체
	 * @param token 쿠키에 저장될 토큰 값
	 */
	public void createCookie(HttpServletResponse res, String token) {
		log.info("Called setCookie path");
		Cookie cookie = new Cookie(cookieConfig.getName(), token);
		cookie.setPath(cookieConfig.getPath());
		cookie.setMaxAge(cookieConfig.getMaxAge());
		cookie.setHttpOnly(cookieConfig.isHttpOnly());
		cookie.setDomain(cookieConfig.getDomain());
		res.addCookie(cookie);
	}
}
