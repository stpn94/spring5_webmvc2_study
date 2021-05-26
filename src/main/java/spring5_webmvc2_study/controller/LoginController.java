package spring5_webmvc2_study.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring5_webmvc2_study.spring.AuthInfo;
import spring5_webmvc2_study.spring.AuthService;
import spring5_webmvc2_study.spring.LoginCommand;
import spring5_webmvc2_study.spring.WrongIdPasswordException;

//로그인 요청을 처리
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AuthService authService;

	
	/*
	 * @CookieValue 애노테이션의 value속성은 쿠키이름을 지정 이름이 “REMEMBER”인 쿠키를 Cookie 타입으로 전달 받음
	 * 지정한 이름을 가진 쿠키가 존재하지 않을 수도 있다면 required 속성값을 false로 지정
	 */
	@GetMapping
	public String form(LoginCommand loginCommand, @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
		if (rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "/login/loginForm";
	}

	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors())
			return "/login/loginForm";
		try {
			AuthInfo authInfo = authService.authenicate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo);
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if (loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			return "/login/loginSuccess";
		} catch (WrongIdPasswordException ex) {
			errors.reject("idPasswordNotMatching");
			return "/login/loginForm";
		}
	}

}
