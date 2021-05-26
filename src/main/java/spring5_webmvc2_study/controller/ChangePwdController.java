package spring5_webmvc2_study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring5_webmvc2_study.spring.AuthInfo;
import spring5_webmvc2_study.spring.ChangePasswordService;
import spring5_webmvc2_study.spring.WrongIdPasswordException;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	@Autowired
	private ChangePasswordService changePasswordService;

	@GetMapping
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCommand) {
		return "edit/changePwdForm";
	}
	
//	changePwdForm.jsp
//	<form:form> 으로 modelAttribute를 설정하지 않기
//	때문에 “command”를 기본값으로 사용

	@PostMapping
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCommand, Errors errors, HttpSession session) {
		new ChangePwdCommandValidator();
		if (errors.hasErrors())
			return "edit/changePwdForm";
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		try {
			changePasswordService.changePassword(authInfo.getEmail(), pwdCommand.getCurrentPassword(),
					pwdCommand.getNewPassword());
			return "edit/changedPwd";
		} catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
