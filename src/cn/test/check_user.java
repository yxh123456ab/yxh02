package cn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.mysql.cj.protocol.Resultset;
import com.sun.org.apache.bcel.internal.generic.IADD;

import cn.DButil.DatabaseDAO;
import cn.itheima.pojo.TbAddress;
import cn.itheima.pojo.TbBook;
import cn.itheima.pojo.TbCartItem;
import cn.itheima.pojo.TbCollect;
import cn.itheima.pojo.TbOrder;
import cn.itheima.pojo.TbOrderItem;
import cn.itheima.pojo.TbUser;

public class check_user {
	private Connection con;
	private PreparedStatement ps=null;
	private ResultSet res=null;
	private String sql="";
	//����user��password �����û�
	public TbUser  check_user_exist(String user,String password) throws SQLException
	{	
		TbUser v=new TbUser();
		con=new DatabaseDAO().getConn();
		sql="select * from tb_user where uname=? and upwd=? and role=1";
		ps=con.prepareStatement(sql);
		ps.setString(1, user);
		ps.setString(2, password);
		
		res= ps.executeQuery();
		
		 if( res.next())
			 {
			v.setUserId(res.getInt("user_id"));
			v.setPhone(res.getString("phone"));
			v.setUname(res.getString("uname"));
			v.setUpwd(res.getString("upwd"));
			v.setEmail(res.getString("email"));
			v.setRole(res.getInt("role"));
			
			 new DatabaseDAO().closeAll(res, ps, con);
			 }
		 else
		 	{ 
			 v=new TbUser();
			 v.setUserId(-1);
			 
			 System.out.println("������");
			 new DatabaseDAO().closeAll(res, ps, con);
			 
			 }
		 return v;
	}
	//�鿴���е��鼮
	public List<TbBook> user_show_all() 
	{
		con=new DatabaseDAO().getConn();
		List<TbBook> tbBooks=new ArrayList<TbBook>();
		sql="select * from tb_book";
		try {
			ps=con.prepareStatement(sql);
			res=ps.executeQuery();
			
			TbBook tbBook=new TbBook();
			int	inum=0;
			while(res.next())
			{
				tbBook=new TbBook();
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
				tbBooks.add(inum, tbBook);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
			 new DatabaseDAO().closeAll(res, ps, con);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return tbBooks;
		
	}
	
	
	//�޸��û�������
	public boolean Change_password(int user_id,String password)
	{
		boolean v=false;
		sql=null;
		sql="UPDATE `bookshop`.`tb_user` SET `upwd` =?  WHERE (`user_id` = ?);";
		con=new DatabaseDAO().getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, user_id);
			ps.executeUpdate();
			v=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return v;
	}
	
	//��������ѯ�鼮���ߣ�������������
	public List<TbBook> User_search(String tiaojian) 
	{
		con=new DatabaseDAO().getConn();
		List<TbBook> tbBooks=new ArrayList<TbBook>();
		sql="SELECT * FROM bookshop.tb_book where author like ? or press like ? or title like ?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+tiaojian+"%");
			ps.setString(2, "%"+tiaojian+"%");
			ps.setString(3,"%"+tiaojian+"%");
			res=ps.executeQuery();
			
			TbBook tbBook=new TbBook();
			int	inum=0;
			while(res.next())
			{
				tbBook=new TbBook();
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
				tbBooks.add(inum, tbBook);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
			 new DatabaseDAO().closeAll(res, ps, con);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tbBooks;
		
	}

	//�����û�id�鿴�����Ƿ���ȷ
	public boolean Check_user_id_password(int user_id,String password)
	{
		boolean v=false;
		con=new DatabaseDAO().getConn();
		sql=null;
		sql="SELECT * FROM bookshop.tb_user where user_id=? and upwd=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2, password);
			res= ps.executeQuery();
			
			 if( res.next())
			 {
				 v=true;
			 }
			 else
				 v=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v;
	}
	
	
	public List<TbBook> queryBooks(String bookIsbn) throws ClassNotFoundException{
        List<TbBook> TbBooks = new ArrayList<TbBook>();
        String sql;
        TbBook tbBook=new TbBook();
        if(bookIsbn.equals("all")){
            sql = "select * from tb_book ";
        }else {
            sql = "select * from tb_book where isbn = "+bookIsbn;
        }

        try {
            Connection conn = DatabaseDAO.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            int inum=0;
            while(res.next()){
            	tbBook=new TbBook();
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
				TbBooks.add(inum, tbBook);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
        System.out.println(TbBooks.get(0).getAuthor());
        return TbBooks;
        
    }
	
	
	//����user_id���ص�ַ1��3
	public List<TbAddress> User_id_address(int user_id)
	{
		List<TbAddress> tbAddresses=new ArrayList<TbAddress>();
		con=new DatabaseDAO().getConn();
		
		try {
			sql=null;
			sql="SELECT * FROM bookshop.tb_address where user_id =? limit 0 , 3";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			TbAddress address=new TbAddress();
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
		return tbAddresses;
	}
	
	//����book��isbn����book����ϸ��Ϣ
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
			{	System.out.println("û���ҵ�");
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
	//�ղ��鼮
	public boolean User_collect_book(int user_id ,String book_isbn)
	{
		boolean v=false;
		
		
		boolean exis=User_collect_book_exist(user_id, book_isbn);
		sql=null;
		sql="insert into tb_collect(user_id,product) values (?,?)";
		if(exis==true)
		{
			v=false;
			System.out.println("�Ѿ��ղص���Ʒ");
			}
		else	
		{
			try {
				con=new DatabaseDAO().getConn();
				ps=con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setString(2, book_isbn);
				System.out.println(user_id+book_isbn);
				ps.executeUpdate();
				v=true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 new DatabaseDAO().closeAll(res, ps, con);
		return v;
		
		
		
		
		
		
	}
	//�鿴�û��ղ���Ʒ�Ƿ��ظ�
	public boolean User_collect_book_exist(int user_id,String book_isbn)
	{
		boolean v=false;
		try {
			con=DatabaseDAO.getConn();
			sql="select * from tb_collect where user_id=? and product=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2, book_isbn);
			res=ps.executeQuery();
			if(res.next())
			{
				v=true;
			}
			else
				v=false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		 new DatabaseDAO().closeAll(res, ps, con);
		return v;
		
	}
	//��ʾ�û��ղص���Ʒ
	public List<TbBook> Show_user_collect(int user_id)
	{
		List<TbBook> collect_books = new ArrayList<TbBook>();
		try {
			con=DatabaseDAO.getConn();
			sql="SELECT tb_book.isbn,title,author,price,press,edition,published,pages,words,packaging,words,packaging,format,form\r\n" + 
					"FROM tb_book ,tb_collect\r\n" + 
					"where tb_book.isbn=tb_collect.product and user_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, String.valueOf(user_id));
			res=ps.executeQuery();
			TbBook tbBook=new TbBook();
			int	inum=0;
			while(res.next())
			{
				tbBook=new TbBook();
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
				collect_books.add(inum, tbBook);
				
				System.out.println(collect_books.get(inum).getTitle());
				inum++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return  collect_books;
		
	}
	//�û�ȡ���ղ���Ʒ
	public void User_collect_remove(int user_id,String book_isbn)
	{
		try {
			con=DatabaseDAO.getConn();
			sql="DELETE FROM `bookshop`.`tb_collect` WHERE user_id=? and product=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2, book_isbn);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		 
	}
	//����ջ���ַ
	public boolean User_add_newAddress(int user_id,String address
			,String receiver,String phone)
	{
		boolean v=false;
		
		java.sql.Date date_sql=new java.sql.Date(System.currentTimeMillis());
		//System.out.println(date_sql+"\t"+date);
		
		try {
			con=new DatabaseDAO().getConn();
			sql="INSERT INTO tb_address "
					+ "(user_id, address,added, receiver, receiver_phone) "
					+ "VALUES (?,?,?,? ,? )";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2,address);
			ps.setDate(3, date_sql);
			
			ps.setString(4, receiver);
			ps.setString(5, phone);
			ps.executeUpdate();
			v=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new DatabaseDAO().closeAll(res, ps, con);
		return v;
		
	}
	//�鿴���ﳵ
	public List<TbCartItem> show_cart(int user_id)
	{
		
		sql=null;
		List<TbCartItem> tbCartItems=new ArrayList<TbCartItem>();
		TbCartItem tbCartItem=new TbCartItem();
		int inum=0;
		sql="SELECT * FROM bookshop.tb_cart_item where user_id=?";
		try {
			con=new DatabaseDAO().getConn();
			ps=con.prepareStatement(sql);
			ps.setString(1, String.valueOf(user_id));
			res=ps.executeQuery();
			while(res.next())
			{
				tbCartItem=new TbCartItem();
				tbCartItem.setUserId(res.getString("user_id"));
				tbCartItem.setProduct(res.getString("product"));
				tbCartItem.setRid(res.getInt("rid"));
				tbCartItem.setCount(res.getInt("count"));
				
				
				tbCartItems.add(inum, tbCartItem);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DatabaseDAO().closeAll(res, ps, con);
		return tbCartItems;
		
	}
	//���ﳵ�����Ʒ
	public void cart_add(int user_id,String product,int count)
	{
		sql=null;
		boolean v= cart_exist_books(user_id, product);
		//System.out.println(v);
		if(v==true)
		{
			sql="update tb_cart_item set count=count+? where user_id=? and product=?;";
			con=new DatabaseDAO().getConn();
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, count);
				ps.setString(2, String.valueOf(user_id));
				ps.setString(3, product);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			sql="INSERT INTO `tb_cart_item` (`user_id`, `product`, `count`) VALUES (?,?,?)" ; 
			con=new DatabaseDAO().getConn();
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, String.valueOf(user_id));
				ps.setString(2, product);
				ps.setInt(3, count);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		new DatabaseDAO().closeAll(res, ps, con);
		
		
	}
	//�жϹ��ﳵ�Ƿ���ڸ���Ʒ
	public boolean cart_exist_books(int user_id,String product)
	{
		sql=null;
		boolean v=false;
		sql="SELECT * FROM bookshop.tb_cart_item where user_id=? and product=?";
		con=new DatabaseDAO().getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2, product);
			res=ps.executeQuery();
			if(res.next())
			{
				v=true;
			}
			else
				v=false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DatabaseDAO().closeAll(res, ps, con);
		return v;
	}
	
	//�����༭�󣬸��¹��ﳵ
	public void Update_cart(List<TbCartItem> tbCartItems)
	{
		int num=tbCartItems.size();
		con=new DatabaseDAO().getConn();
		try {
			for(int i=0;i<num;i++) {
				ps=null;
				sql=null;
				sql="UPDATE `tb_cart_item` SET `count` = ? WHERE user_id=? and product=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, tbCartItems.get(i).getCount());
				ps.setString(2, tbCartItems.get(i).getUserId());
				ps.setString(3, tbCartItems.get(i).getProduct());
				ps.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DatabaseDAO().closeAll(res, ps, con);
	}
	//��������ĳ����Ʒ����������ύ������ͬ�������ύ����ڶ�����Ʒ��һ�����ֱ�ӹ���ֻ��һ����Ʒ��
	//user_id,order_id,address_id,payment
	//isbn,price,count,order_id
	
	
	
	
	public void Buy_One_now(int user_id,int address_id,String order_id,double payment
			)
	{
		sql=null;
		
		java.sql.Date placed_time=new java.sql.Date(System.currentTimeMillis());
		//sta ������״̬
		String sta="0";
		con=DatabaseDAO.getConn();
		try {
			sql="INSERT INTO `tb_order` (`user_id`,`order_id`,`sta`,`address_id`,`payment`,`placed`) "
					+ "VALUES (?,?,?,?,?,?)" ;
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2, order_id);
			ps.setString(3, sta);
			ps.setInt(4, address_id);
			ps.setDouble(5, payment);
			ps.setDate(6, placed_time);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		
	}
	
	//����order_item
	public void Buy_One_now_item(String isbn,double price,int counts,String oreder_id)
	{
		sql=null;
		con=DatabaseDAO.getConn();
		try {
			sql="INSERT INTO `tb_order_item` (`product`, `price`, `count`, `order_id`) "
					+ "VALUES (?, ?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setDouble(2, price);
			ps.setInt(3, counts);
			ps.setString(4, oreder_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
	}
	
	//��ֹ��Ϊˢ�µ��¶����Ķ����ύ������order_id����̨�Ƿ���ڸö���
	
	public  boolean order_id_exits(String order_id)
	{
		sql=null;
		boolean v=true;
		con=DatabaseDAO.getConn();
		try {
			sql="SELECT * FROM bookshop.tb_order where order_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, order_id);
			res= ps.executeQuery();
			if(res.next())
				v=true;
			else
				v=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	
	
	//�ύ����(order)
	//������1.�ύ��Ҫ�������µ�ʱ�䣬�û�id����ַ��������ŵȣ�2.�����е���Ʒ��Ϣ��
	//user_id ��order_id�� sta ��address_id ��payment�� placed(date)
	public void submit_order(int use_id,List<TbCartItem> tbCartItems,int address_id,double payment)
	{
		sql=null;
		//������
		String order_id=Create_order_id();
		//
		//�µ�ʱ��
		java.sql.Date placed_time=new java.sql.Date(System.currentTimeMillis());
		//sta ������״̬
		String sta="0";
		con=new DatabaseDAO().getConn();
		try {
			sql="INSERT INTO `tb_order` (`user_id`,`order_id`,`sta`,`address_id`,`payment`,`placed`) "
					+ "VALUES (?,?,?,?,?,?)" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, String.valueOf(use_id));
			ps.setString(2, order_id);
			ps.setString(3, String.valueOf(sta));
			ps.setInt(4, address_id);
			ps.setDouble(5, payment);
			ps.setDate(6, placed_time);
			ps.executeUpdate();
			/*res=null;
			new DatabaseDAO().closeAll(res, ps, con);*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		submit_order_detail(order_id, tbCartItems);
		
	}
	//�ύ�����굥,������ķ���
	public void submit_order_detail(String order_id,List<TbCartItem> tbCartItems)
	{	//�õ������й������Ʒ������
		int inum=tbCartItems.size();
		//
		con=new DatabaseDAO().getConn();
		sql="INSERT INTO `tb_order_item` (`product`, `price`, `count`, `order_id`)"
				+ " VALUES (?,?,?,?)";
		try {
		for(int i=0;i<inum;i++)
		{
				ps=con.prepareStatement(sql);
				ps.setString(1, tbCartItems.get(i).getProduct());
			//	ps.setDouble(2, tbCartItems.get(i).get);
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//�û��ղ���Ʒ
	public void User_collect(int User_id,String isbn)
	{	
		
		sql=null;
		sql="INSERT INTO `tb_collect` (`user_id`, `product`) VALUES (?,?)";
		con=new DatabaseDAO().getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, String.valueOf(User_id));
			ps.setString(2, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
	}
	//�жϸ���Ʒ�û��Ƿ��ղأ�trueΪ�ղ� ,falseΪδ�ղ�
	public boolean User_collect_exist(int User_id,String isbn)
	{
		boolean v=false;
		sql=null;
		sql="SELECT * FROM bookshop.tb_collect where user_id=? and product=?";
		con=new DatabaseDAO().getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, String.valueOf(User_id));
			ps.setString(2, isbn);
			res=ps.executeQuery();
			if(res.next())
				v=true;
			else
				v=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return v;
		
		
	}
	
	//����ķ���������û��ֱ���õ������������ķ����ڲ����ã�
	//����product��A001��,���ظ���Ʒȫ����Ϣ
	public TbBook Tbcart_link_TbBook(String product_id)
	{
		sql=null;
		sql="SELECT * FROM bookshop.tb_book where isbn=?";
		TbBook tbBook=new TbBook();
		con=DatabaseDAO.getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, product_id);
			res=ps.executeQuery();
			while(res.next()) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DatabaseDAO().closeAll(res, ps, con);
		return tbBook;
		
	}
	
	//1��4���Ժ�д�ķ���
	//�����û���id�����û�������Ϣ()
	//����������������new_Dont_tKnow����һ�𱻵��õģ�Ӧ���ڶ�������ʾ��
	public List<TbOrderItem> Order_item_By_user_id(int user_id)
	{
		sql=null;
		List<TbOrderItem> tbOrderItems=new ArrayList<TbOrderItem>();
		con=DatabaseDAO.getConn();
		try {
			sql="SELECT tb_order_item.rid,product,price,count,tb_order_item.order_id FROM bookshop.tb_order_item,tb_order\r\n" + 
					"where user_id=? and tb_order.order_id=tb_order_item.order_id \r\n" + 
					"group by tb_order_item.order_id ;";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
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
	//ȷ���ջ�����
	public boolean sure_receive(String order_id)
	{
		sql=null;
		boolean v=false;
		con=new DatabaseDAO().getConn();
		try {
			sql="UPDATE `bookshop`.`tb_order` SET `sta` = '3' WHERE order_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, order_id);
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
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return v;
	}
	
	
	
	//����order_id����orderItem
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
	
	//
	//���ض������鱾��Ϣ
	public List<TbBook> order_book_detail(String order_id)
	{
		con=new DatabaseDAO().getConn();
		List<TbBook> tbBooks=new ArrayList<TbBook>();
		sql="SELECT tb_book.* FROM bookshop.tb_order_item,tb_book where product=tb_book.isbn and order_id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, order_id);
			res=ps.executeQuery();
			
			TbBook tbBook=new TbBook();
			int	inum=0;
			while(res.next())
			{
				tbBook=new TbBook();
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
				tbBooks.add(inum, tbBook);
				
				//System.out.println(tbBooks.get(inum).getTitle());
				inum++;
			}
			 new DatabaseDAO().closeAll(res, ps, con);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return tbBooks;
	}

	//counts
	public List<Integer> counts(Integer user_id)
	{
		sql="";
		List<Integer> a =new ArrayList<Integer>() ;
		con=new DatabaseDAO().getConn();
		try {
			sql="SELECT tb_order_item.order_id ,count(*) as counts \r\n" + 
					"FROM bookshop.tb_order_item,tb_order \r\n" + 
					"where user_id=? and tb_order.order_id=tb_order_item.order_id group BY order_id;";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next())
				a.add(res.getInt("counts"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	//����order_item�е�product��������Ʒ����Ϣ�����÷�Χ�Ƚ�С
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
	//�����û���id ���ض���
	public List<TbOrder> orders(int user_id)
	{
		sql=null;
		List<TbOrder> tbOrders=new ArrayList<TbOrder>();
		con=DatabaseDAO.getConn();
		try {
			sql="SELECT * FROM bookshop.tb_order where user_id=? ";
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_id);
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
	
	//��ʾ������ϸ�еĵ�ַ,���ݶ�������ʾ��ϸ��ַ
	public TbAddress Order_Info_address(TbOrder order)
	{
		TbAddress address=new TbAddress();
		int address_id=order.getAddressId();
		sql=null;
		con=new DatabaseDAO().getConn();
		try {
			sql="SELECT * FROM bookshop.tb_address where rid=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, address_id);
			res= ps.executeQuery();
			if(res.next())
			{
				address.setRid(res.getInt("rid"));
				address.setUserId(res.getString("user_id"));
				address.setAddress(res.getString("address"));
				address.setAdded(res.getDate("added"));
				address.setReceiver(res.getString("receiver"));
				address.setReceiverPhone(res.getString("receiver_phone"));
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
	
	//����order_id,isbn����order_item
	//
	public TbOrderItem Show_order_item_ByOrderIdAndIsbn(String order_id,String isbn)
	{
		TbOrderItem orderItem=new TbOrderItem();
		sql=null;
		con=new DatabaseDAO().getConn();
		sql="SELECT * FROM bookshop.tb_order_item "
				+ "where product=? and "
				+ "order_id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, order_id);
			res= ps.executeQuery();
			if(res.next())
			{
				orderItem.setRid(res.getInt("rid"));
				orderItem.setProduct(res.getString("product"));
				orderItem.setPrice(res.getDouble("price"));
				orderItem.setCount(res.getInt("count"));
				orderItem.setOrderId(res.getString("order_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return orderItem;
	}

	//����order_id����order_item
		//Ҫ�޸ĵ�
	public List<TbOrderItem> Show_order_item_ByOrderId(String order_id)
		{	
			List<TbOrderItem> orderItems=new ArrayList<TbOrderItem>();
			TbOrderItem orderItem=new TbOrderItem();
			sql=null;
			con=new DatabaseDAO().getConn();
			sql="SELECT * FROM bookshop.tb_order_item where order_id=?";
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, order_id);
				
				res= ps.executeQuery();
				int inum=0;
				
				while(res.next())
				{
					orderItem=new TbOrderItem();
					orderItem.setRid(res.getInt("rid"));
					orderItem.setProduct(res.getString("product"));
					orderItem.setPrice(res.getDouble("price"));
					orderItem.setCount(res.getInt("count"));
					orderItem.setOrderId(res.getString("order_id"));
					
					orderItems.add(inum, orderItem);
					inum++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				new DatabaseDAO().closeAll(res, ps, con);
			}
			return orderItems;
		}

	//����order_id����order
	//
	public TbOrder Show_order_By_order_id(String order_id)
	{
		TbOrder order=new TbOrder();
		sql=null;
		con=new DatabaseDAO().getConn();
		try {
			sql="SELECT * FROM bookshop.tb_order where order_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, order_id);
			res= ps.executeQuery();
			if(res.next())
				{
				order.setRid(res.getInt("rid"));
				order.setUserId(res.getString("user_id"));
				order.setOrderId(res.getString("order_id"));
				order.setSta(res.getString("sta"));
				order.setAddressId(res.getInt("address_id"));
				order.setPayment(res.getDouble("payment"));
				order.setPlaced(res.getDate("placed"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
		return order;
		
	}
	//���ﳵ�������ɾ�����ﳵ�Ѿ��������Ʒ
	public void delete_cart(String isbn, Integer user_id)
	{
		sql=null;
		con=DatabaseDAO.getConn();
		try {
			sql="DELETE FROM `bookshop`.`tb_cart_item` WHERE product=? and user_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setInt(2, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DatabaseDAO().closeAll(res, ps, con);
		}
	}
	
	
	
	//������ɶ�����
	public static String Create_order_id() {
        int first = new Random(10).nextInt(8) + 1;
       // System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//�п����Ǹ���
            hashCodeV = -hashCodeV;
        }
        // 0 ����ǰ�油��0
        // 4 ������Ϊ4
        // d �������Ϊ������
        return first + String.format("%015d", hashCodeV);
    }
	
}
