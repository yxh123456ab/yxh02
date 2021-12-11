package cn.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itheima.pojo.TbAddress;
import cn.itheima.pojo.TbBook;
import cn.itheima.pojo.TbCartItem;
import cn.itheima.pojo.TbOrder;
import cn.itheima.pojo.TbOrderItem;

public class main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		/*List<TbAddress> m=
		new check_user().User_id_address(1);
		System.out.println(m.get(2).getAddress());*/
		/*List<TbOrderItem> aItems =new check_user().Order_item_By_user_id(1);
		for(int i=0;i<aItems.size();i++)
		System.out.println(aItems.get(i).getRid()+"````"+aItems.get(i).getProduct()+"````"+aItems.get(i).getPrice()+"````"+aItems.get(i).getCount()+"````"+aItems.get(i).getOrderId());
		*/
		
		/*boolean m=new check_user().order_id_exits("60003000766776286");
		System.out.println(m);*/
		/*new check_user().User_collect_book(1,"A005");*/
		/*System.out.println(mm);*/
		/*check_user aCheck_user =new check_user();
		aCheck_user.cart_add(1, "A004", 1);*/
		/*List<TbCartItem> a=aCheck_user.show_cart(1);
		a.get(0).setCount(11);
		aCheck_user.Update_cart(a);*/
		/*create_order_id mm=new create_order_id();
		
		System.out.println(mm.toString());*/
		/*TbBook tbBook=null;
		tbBook=new check_user().Tbcart_link_TbBook("A001");*/
		
		/*System.out.println(tbBook.getIsbn());
		System.out.println(tbBook.getAuthor());
		System.out.println(tbBook.getForm());
		System.out.println(tbBook.getFormat());
		System.out.println(tbBook.getPackaging());
		System.out.println(tbBook.getPress());
		System.out.println(tbBook.getTitle());
		System.out.println(tbBook.getEdition());
		System.out.println(tbBook.getPages());
		System.out.println(tbBook.getPrice());
		System.out.println(tbBook.getPublished());
		System.out.println(tbBook.getWords());*/
		
		/*List<TbOrderItem> aList=new check_user().Show_order_item_ByOrderId("6000002070405491");
		System.out.println(aList.size());
		*/
		
		/*List<TbOrderItem> tbOrderItems =new check_user().Order_item_By_user_id(1);
		System.out.println(tbOrderItems.size());
		//得到根据用户的id的order
		List<TbOrder> tbOrders=new check_user().orders(1);
		System.out.println(tbOrders.size());
		//得到根据用户的订单详细信息返回购买的书
		List<TbBook> Books=new check_user().order_item_books(tbOrderItems);
		System.out.println(Books.size());
		List<Integer> aIntegers=new check_user().counts(1);
		System.out.println(aIntegers.size());*/
		/*List<TbBook> aBooks=new check_user().order_book_detail("6000002070405491");
		System.out.println(aBooks.size());*/
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd");
	    String dateString = formatter.format(currentTime);
	System.out.println(dateString);
	
		
	}

}
