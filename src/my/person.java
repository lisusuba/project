package my;

public class person 
{
	public String id;
	public String name;
	public String date;
	public String password;
	public String phone;
	///////////////////
	public person()
	{
		
	}
	
	public person(String id, String name, String date, String password,String phone)
	{
		this.id = id;
		this.name = name;
		this.date = date;
		this.password = password;
		this.phone = phone;
	}
	////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
