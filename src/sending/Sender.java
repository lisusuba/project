package sending;

public class Sender 
{
	public String sender_id;
	public String sender_name;
	public String sender_phone;
	public String sender_address;
	public String recipient_name;
	public String recipient_phone;
	public String recipient_address;
	public String date;
	public String goods_description;
	public int goods_num;
	public String goods_type;
	public Sender()
	{
		
	}
	public Sender(String sender_id,String sender_name,String sender_phone,
			String sender_address,String recipient_name,String recipient_phone,
			String recipient_address,String date,String goods_description,String goods_type)
	{
		this.sender_id=sender_id;
		this.sender_name=sender_name;
		this.sender_phone=sender_phone;
		this.sender_address=sender_address;
		this.recipient_name=recipient_name;
		this.recipient_phone=recipient_phone;
		this.recipient_address=recipient_address;
		this.date=date;
		this.goods_description=goods_description;
		this.goods_type=goods_type;
	}
	
//	Sender s = new Sender(goods_num,sender_id,sender_name,sender_phone,sender_address,recipient_name,
//			recipient_phone,recipient_address,date,goods_description);
//	public Sender(int goods_num,String sender_id,String sender_name,String sender_phone,String sender_address,
//			String recipient_name,String recipient_phone,String recipient_address,
//			String date,String goods_description)
//	{
//		this.goods_num = goods_num;
//		this.sender_id=sender_id;
//		this.sender_name=sender_name;
//		this.sender_phone=sender_phone;
//		this.sender_address=sender_address;
//		this.recipient_name=recipient_name;
//		this.recipient_phone=recipient_phone;
//		this.recipient_address=recipient_address;
//		this.date=date;
//		this.goods_description=goods_description;
//	}
	public String getId() {
		return sender_id;
	}
	public void setId(String id) {
		this.sender_id = id;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getSender_phone() {
		return sender_phone;
	}
	public void setSender_phone(String sender_phone) {
		this.sender_phone = sender_phone;
	}
	public String getSender_adress() {
		return sender_address;
	}
	public void setSender_adress(String sender_adress) {
		this.sender_address = sender_adress;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getRecipient_phone() {
		return recipient_phone;
	}
	public void setRecipient_phone(String recipient_phone) {
		this.recipient_phone = recipient_phone;
	}
	public String getRecipient_adress() {
		return recipient_address;
	}
	public void setRecipient_adress(String recipient_adress) {
		this.recipient_address = recipient_adress;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	

}
