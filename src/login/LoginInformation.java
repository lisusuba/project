package login;

public class LoginInformation 
{
	public String login_id;
	public String login_password;
	///////////////////
	public LoginInformation()
	{
		
	}
	
	public LoginInformation(String login_id, String login_password)
	{
		this.login_id = login_id;
		this.login_password = login_password;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

}
