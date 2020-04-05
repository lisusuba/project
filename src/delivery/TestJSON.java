package delivery;
//测试类
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestJSON
{
	public static void test1() 
	{
		Student pojo = new Student(20180001, "邵发", true,"13800012345");
		JSONObject jobj = new JSONObject(pojo); //  必须给POJO添加好Getter/Setter
		String jstr = jobj.toString(2); // 2为缩进宽度
		System.out.println(jstr);
	}
	
	public static void test2()
	{
		ArrayList<Student> students = new ArrayList();
		students.add(new Student(20180001, "shao", true,"13802323235"));
		students.add(new Student(20180002, "wang", true,"13233434446"));
		students.add(new Student(20180003, "li",   true,"13434340292"));
		
		JSONArray jarray = new JSONArray(students);
		System.out.println(jarray.toString(2));
	}

//	Deliveryer(int goods_num,String date,String sender_id,String sender_name,
//			String sender_phone,String sender_address,String recipient_name,
//			String recipient_phone,String recipient_address,String goods_description,int brokerage)
	public static ArrayList<Deliveryer> test3()
	{
		ArrayList<Deliveryer> data = new ArrayList<Deliveryer>();
		//data.add(new Deliveryer(1,"36273-3-2","fd","猪八戒","23432","水帘洞","孙悟空","2342","花果山","一箱牛奶",10,"1"));
		return data;
	}
	public static void main(String[] args)
	{
		List<Deliveryer> row=test3();
		JSONArray result = new JSONArray(row);
		for(Object s:result)
		{
			//System.out.println(s.goods_num+" "+s.sender_id+" "+" "+s.sender_address+" "+s.recipient_address+" " +s.recipient_phone);
		    System.out.println(s);
		}

	}

}
