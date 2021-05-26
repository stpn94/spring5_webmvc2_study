package spring5_webmvc2_study.controller;

//비밀번호 변경 기능 구현 작업
public class ChangePwdCommand {

	private String currentPassword;

	private String newPassword;

	public String getCurrentPassword() {

		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}