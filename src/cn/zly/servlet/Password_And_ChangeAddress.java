package cn.zly.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.I2F;

import cn.itheima.pojo.TbAddress;
import cn.itheima.pojo.TbBook;
import cn.test.check_user;

/**
 * Servlet implementation class Password_And_ChangeAddress
 */
public class Password_And_ChangeAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Password_And_ChangeAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("doget");
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session= request.getSession();
		request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("User_id_And_password")){
        	User_id_And_password(request, response);
        }
        if(method.equals("Change_password"))
        {
        	Change_password(request, response);
        }
        if(method.equals("Add_New_Address"))
        {
        	Add_New_Address(request, response);
        }
        if(method.equals("search"))
        {
        	//System.out.println("125rjiogerjgis");
        	User_Search(request, response);
        }
        if(method.equals("Buy_now"))
        {
        	Buy_now(request,response);
        }
        
		
		
		
		
	}
	
	//立即购买商品
	public void Buy_now(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String isbn = request.getParameter("isbn").toString();
		int count = Integer.valueOf(request.getParameter("count").toString());
		List<Integer> counts=new ArrayList<Integer>();
		counts.add(count);
		int user_id=Integer.valueOf( session.getAttribute("user_id").toString());
		System.out.println(isbn+"````"+counts+"````"+user_id);
		TbBook book=new check_user().show_book_detail(isbn);
		List<TbBook> books=new ArrayList<TbBook>();
		books.add(book);
		List<TbAddress> Any_address=new check_user().User_id_address(user_id);
		
		request.setAttribute("books",books);
		request.setAttribute("Addresses",Any_address);
		request.setAttribute("num",counts);
		session.setAttribute("order_books", books);
		session.setAttribute("order_address", Any_address);
		session.setAttribute("order_num", counts);
		//System.out.println(Any_address.get(1).getReceiverPhone());
		request.getRequestDispatcher("order-confirm.jsp").forward(request,response);
		
		
	}
	
	
	
	//用户搜索商品（可以根据作者，书名，出版社）
	public void User_Search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		//System.out.println("trrwerfwe");
		
		
		String tiaojian = java.net.URLDecoder.decode(request.getParameter("text_search"), "utf-8");
		
		
		System.out.println(tiaojian);
		List<TbBook> tbBooks=new ArrayList<TbBook>();
		tbBooks=new check_user().User_search(tiaojian);
		System.out.println(tbBooks.size());
		//session.setAttribute("search_books",tbBooks);
		request.setAttribute("search_books",tbBooks);
		
		request.getRequestDispatcher("search_result.jsp").forward(request,response);
	}
	
	//添加新的地址
	public void Add_New_Address(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String receiver = request.getParameter("receiver").toString();
		String address = request.getParameter("address").toString();
		String phone=request.getParameter("phone").toString();
		String user_id=request.getParameter("user_id").toString();
		System.out.println(user_id);
		boolean v=false;
		v= new check_user().User_add_newAddress(Integer.valueOf(user_id), address, receiver, phone);
		if(v) {
			response.getWriter().print("yes");
			}
			else
			response.getWriter().print("no");
	}
	//ajks判断user_id和password是否对应
	public void User_id_And_password(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String user_id =session.getAttribute("user_id").toString();
		String upw =request.getParameter("upwd").toString();
		response.setCharacterEncoding("UTF-8");
		System.out.println(user_id+"````"+upw);
		boolean v=new check_user().Check_user_id_password(Integer.valueOf(user_id), upw);
		if(v) {
		response.getWriter().print("yes");
		}
		else
		response.getWriter().print("no");
	}
	//根据user_id修改password
	public void Change_password(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String user_id =request.getParameter("uname").toString();
		String upw =request.getParameter("cpwd").toString();
		
		response.setCharacterEncoding("UTF-8");
		
		boolean v=new check_user().Change_password(Integer.valueOf(user_id), upw);
		System.out.println("v======"+v);
		if(v) {
		response.getWriter().print("yes");
		}
		else
		response.getWriter().print("no");
	
	}
}
