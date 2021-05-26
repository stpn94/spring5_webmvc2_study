package spring5_webmvc2_study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// 이메일과 비밀번호가 일치하는지 확인해서 AuthInfo 객체를 생성하는 AuthService
@Component
public class AuthService {
	@Autowired
	private MemberDao memberDao;

	public AuthInfo authenicate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new WrongIdPasswordException();
		}
		if (!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
}