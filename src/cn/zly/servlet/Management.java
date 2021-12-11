package cn.zly.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.DButil.DatabaseDAO;
import cn.itheima.pojo.TbAddress;
import cn.itheima.pojo.TbBook;
import cn.itheima.pojo.TbOrder;
import cn.itheima.pojo.TbOrderItem;
import cn.itheima.pojo.TbUser;
import cn.test.check_user;

public class Management {
	private Connection con;
	private PreparedStatement ps=null;
	private ResultSet res=null;
	private String sql="";
	
	
	public List<TbOrder> orders_Mangement()
	{
		sql=null;
		List<TbOrder> tbOrders=new ArrayList<TbOrder>();
		con=DatabaseDAO.getConn();
		try {
			sql="SELECT * FROM bookshop.tb_order";
			ps=con.prepareStatement(sql);
			
			res= ps.executeQuery();
			TbOrder order=new TbOrder();
			int inum=0;
			while(res.next())
			{	
				order=new TbOrder();
				
				order.setRid(res.getInt("rid"));
				order.setUserId(res.getString("user_id"));
				order.setOrderId(res.getString("order_id"));
				order.setSta(res.getString("sta"));
				order.setAddressId(res.getInt("address_id"));
				order.setPayment(res.getDouble("payment"));
				order.setPlaced(res.getDate("placed"));
				tbOrders.add(inum, order);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		
		return tbOrders;
	}

	//管理员根据sta来返回订单
	public List<TbOrder> show_Any(String sta)
	{
		sql=null;
		List<TbOrder> tbOrders=new ArrayList<TbOrder>();
		con=DatabaseDAO.getConn();
		try {
			sql="SELECT * FROM bookshop.tb_order where sta=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, sta);
			res= ps.executeQuery();
			TbOrder order=new TbOrder();
			int inum=0;
			while(res.next())
			{	
				order=new TbOrder();
				
				order.setRid(res.getInt("rid"));
				order.setUserId(res.getString("user_id"));
				order.setOrderId(res.getString("order_id"));
				order.setSta(res.getString("sta"));
				order.setAddressId(res.getInt("address_id"));
				order.setPayment(res.getDouble("payment"));
				order.setPlaced(res.getDate("placed"));
				tbOrders.add(inum, order);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		
		return tbOrders;
	}
	
	
	
	
	
	
	
	//处理订单的sta
	public boolean  cope_sta(String order_id , int sta) {
		boolean v= false;
		con=DatabaseDAO.getConn();
		sql=null;
		try {
			sql="UPDATE `bookshop`.`tb_order` SET `sta` = ? WHERE order_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, sta);
			ps.setString(2, order_id);
			int i=ps.executeUpdate();
			if(i!=0)
				v=true;
			else {
				v=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	
	//根据order_item中的product来返回商品的信息，适用范围比较小
		public List<TbBook> order_item_books(List<TbOrderItem> tbOrderItems)
		{
			
			sql=null;
			List<TbBook> tbBooks=new ArrayList<TbBook>();
			TbBook tbBook=new TbBook();
			for(int i=0;i<tbOrderItems.size();i++)
			{	tbBook=new TbBook();
				tbBook=show_book_detail(tbOrderItems.get(i).getProduct());
				tbBooks.add(i, tbBook);
			}
			return tbBooks;
		}
		//根据book的isbn查找book的详细信息
		public TbBook show_book_detail(String Isbn)
		{
			TbBook tbBook=new TbBook();
			con=new DatabaseDAO().getConn();
			sql="select * from tb_book where isbn=?";
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, Isbn);
				res=ps.executeQuery();
				if(res.next()) {
				tbBook.setIsbn(res.getString("isbn"));
				tbBook.setTitle(res.getString("title"));
				tbBook.setAuthor(res.getString("author"));
				tbBook.setPrice(res.getDouble("price"));
				tbBook.setPress(res.getString("press"));
				tbBook.setEdition(res.getInt("edition"));
				tbBook.setPublished(res.getDate("published"));
				tbBook.setPages(res.getInt("pages"));
				tbBook.setWords(res.getInt("words"));
				tbBook.setPackaging(res.getString("packaging"));
				tbBook.setFormat(res.getString("format"));
				tbBook.setForm(res.getString("form"));
				
				}
				else
				{	System.out.println("没有找到");
					return null;
				}
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 new DatabaseDAO().closeAll(res, ps, con);
			
			return tbBook;
				
			
		}
	
	//根据order_id返回orderItem
	public List<TbOrderItem> Order_item_By_order_id(String order_id)
	{
		sql=null;
		List<TbOrderItem> tbOrderItems=new ArrayList<TbOrderItem>();
		con=DatabaseDAO.getConn();
		try {
			sql="SELECT * FROM bookshop.tb_order_item where order_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, order_id);
			res= ps.executeQuery();
			TbOrderItem orderItem=new TbOrderItem();
			int inum=0;
			while(res.next())
			{	
				orderItem=new TbOrderItem();
				
				orderItem.setRid(res.getInt("rid"));
				orderItem.setProduct(res.getString("product"));
				orderItem.setPrice(res.getDouble("price"));
				orderItem.setCount(res.getInt("count"));
				orderItem.setOrderId(res.getString("order_id"));
				
				tbOrderItems.add(inum, orderItem);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		
		return tbOrderItems;
	}
	//获取当前时间
	public String current_time()
	{
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	}
	
	
	//根据rid返回地址的信息
	public TbAddress User_id_address(int rid)
	{
		List<TbAddress> tbAddresses=new ArrayList<TbAddress>();
		con=new DatabaseDAO().getConn();
		TbAddress address=new TbAddress();
		try {
			sql=null;
			sql="SELECT * FROM bookshop.tb_address where rid=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, rid);
			res=ps.executeQuery();
			
			int inum=0;
            while(res.next()){
            	address=new TbAddress();
				address.setRid(res.getInt("rid"));
				address.setUserId(res.getString("user_id"));
				address.setAddress(res.getString("address"));
				address.setAdded(res.getDate("added"));
				address.setReceiver(res.getString("receiver"));
				address.setReceiverPhone(res.getString("receiver_phone"));
				
				tbAddresses.add(inum, address);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;

            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return address;
	}
	
	
	
	//检查管理员账号是否存在
	public TbUser Manage_exist(String username,String password)
	{
		TbUser user=null;
		sql=null;
		con=new DatabaseDAO().getConn();
		try {
			sql="SELECT * FROM bookshop.tb_user where uname=? and upwd=? and role=2";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			res= ps.executeQuery();
			
			if( res.next())
			 {user=new TbUser();
			user.setUserId(res.getInt("user_id"));
			user.setPhone(res.getString("phone"));
			user.setUname(res.getString("uname"));
			user.setUpwd(res.getString("upwd"));
			user.setEmail(res.getString("email"));
			user.setRole(res.getInt("role"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		
		return user;
		
	}
}
