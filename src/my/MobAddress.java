package my;

public class MobAddress 
{
	public String phone;
	public String name;
	public String address;
	public int isdefault;
	public int address_num;
	public int isexpress;
	public MobAddress()
	{
		
	}
	public MobAddress(String name,String phone,String address,int isdefault,int address_num,int isexpress)
	{
		this.name=name;
		this.phone=phone;
		this.address=address;
		this.isdefault=isdefault;
		this.address_num=address_num;
		this.isexpress=isexpress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(int isdefault) {
		this.isdefault = isdefault;
	}
	public int getAddress_num() {
		return address_num;
	}
	public void setAddress_num(int address_num) {
		this.address_num = address_num;
	}
	public int getIsexpress() {
		return isexpress;
	}
	public void setIsexpress(int isexpress) {
		this.isexpress = isexpress;
	}
}
