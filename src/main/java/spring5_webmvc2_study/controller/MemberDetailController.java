package spring5_webmvc2_study.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import spring5_webmvc2_study.spring.Member;
import spring5_webmvc2_study.spring.MemberDao;
import spring5_webmvc2_study.spring.MemberNotFoundException;

//@PathVariable을 이용한 경로 변수 처리
@Controller
public class MemberDetailController {
	@Autowired
	private MemberDao memberDao;

	@GetMapping("/members/{id}")
	public ModelAndView detail(@PathVariable("id") Long memId) {
		Member member = memberDao.selectById(memId);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.setViewName("member/memberDetail");
		return mav;

	}

	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException() {
		return "member/invalidId";
	}

	@ExceptionHandler(MemberNotFoundException.class)
	public String handeMemberNotFoundException() {
		return "member/noMember";
	}	
}
