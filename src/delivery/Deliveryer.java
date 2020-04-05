package delivery;

public class Deliveryer 
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
	public int brokerage;
	public String deliveryman_id;
	public String get_date;
	public String send_time;
	public String goods_type;
	public int isget_goods;
	public String phone;
	public int issended;
	public Deliveryer()
	{
		
	}
	public Deliveryer(int goods_num,String date,String sender_id,String sender_name,
			String sender_phone,String sender_address,String recipient_name,
			String recipient_phone,String recipient_address,String goods_description,
			int brokerage,String goods_type,String phone,int issended)
	{
		this.goods_num=goods_num;
		this.date=date;
		this.sender_id=sender_id;
		this.sender_name=sender_name;
		this.sender_phone=sender_phone;
		this.sender_address=sender_address;
		this.recipient_name=recipient_name;
		this.recipient_phone=recipient_phone;
		this.recipient_address=recipient_address;
		this.goods_description=goods_description;
		this.brokerage=brokerage;
		this.goods_type=goods_type;
		this.phone=phone;
		this.issended=issended;
	}
	public Deliveryer(int goods_num,String date,String sender_id,String sender_name,
			String sender_phone,String sender_address,String recipient_name,
			String recipient_phone,String recipient_address,String goods_description,
			int brokerage,String goods_type,String phone)
	{
		this.goods_num=goods_num;
		this.date=date;
		this.sender_id=sender_id;
		this.sender_name=sender_name;
		this.sender_phone=sender_phone;
		this.sender_address=sender_address;
		this.recipient_name=recipient_name;
		this.recipient_phone=recipient_phone;
		this.recipient_address=recipient_address;
		this.goods_description=goods_description;
		this.brokerage=brokerage;
		this.goods_type=goods_type;
		this.phone=phone;
	}
	public Deliveryer(String sender_id,String sender_name,String get_date,
			String sender_phone,String sender_address,String recipient_name,String recipient_phone,
			String recipient_address,String goods_description,int goods_num,int brokerage,
			String send_time,String goods_type,int isget_goods)
	{
		//this.deliveryman_id=deliveryman_id;
		this.sender_id = sender_id;
		this.sender_name=sender_name;
		this.get_date=get_date;
		this.sender_phone=sender_phone;
		this.sender_address=sender_address;
		this.recipient_name=recipient_name;
		this.recipient_phone=recipient_phone;
		this.recipient_address=recipient_address;
		this.goods_description=goods_description;
		this.goods_num=goods_num;
		this.brokerage=brokerage;
		this.send_time=send_time;
		this.goods_type=goods_type;
		this.isget_goods=isget_goods;
	}
	public String getDeliveryman_id() {
		return deliveryman_id;
	}
	public void setDeliveryman_id(String deliveryman_id) {
		this.deliveryman_id = deliveryman_id;
	}
	public String getGet_date() {
		return get_date;
	}
	public void setGet_date(String get_date) {
		this.get_date = get_date;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
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
	public String getSender_address() {
		return sender_address;
	}
	public void setSender_address(String sender_address) {
		this.sender_address = sender_address;
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
	public String getRecipient_address() {
		return recipient_address;
	}
	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
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
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public int getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(int brokerage) {
		this.brokerage = brokerage;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public int getIsget_goods() {
		return isget_goods;
	}
	public void setIsget_goods(int isget_goods) {
		this.isget_goods = isget_goods;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIssended() {
		return issended;
	}
	public void setIssended(int issended) {
		this.issended = issended;
	}
	
}
