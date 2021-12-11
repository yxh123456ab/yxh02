package cn.zly.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.pojo.TbAddress;
import cn.itheima.pojo.TbBook;
import cn.itheima.pojo.TbOrder;
import cn.itheima.pojo.TbOrderItem;
import cn.itheima.pojo.TbUser;
import cn.test.check_user;
import cn.test.main;

/**
 * Servlet implementation class Mangement_others
 */
public class Mangement_others extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null; 
	String method;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mangement_others() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session= request.getSession();
		request.setCharacterEncoding("utf-8");
		method=null;
        method = request.getParameter("method").toString();
        if(method.equals("show_all_orders"))
        {
        	show_all_orders(request,response);
        }
        if(method.equals("show_details"))
        {
        	show_detail(request,response);
        }
        if(method.equals("order_print"))
        {
        	order_print(request,response);
        }
        if(method.equals("show_process"))
        {
        	show_process(request,response);
        }
        if(method.equals("cope_sta"))
        {
        	cope_sta(request,response);
        }
        if(method.equals("show_Any"))
        {
        	show_Any(request,response);
        }
        
        
	}
	//订单的分类显示
	public void show_Any(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String sta=request.getParameter("sta").toString();
		List<TbOrder> tbOrders=null;
		tbOrders=new Management().show_Any(String.valueOf(sta));
		request.setAttribute("tborders_all", tbOrders);
		TbUser user=(TbUser) session.getAttribute("management");

		request.getRequestDispatcher("order-list.jsp").forward(request, response);
	}
	
	//订单状态的处理
	public void cope_sta(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String sta=request.getParameter("sta").toString();
		String order_id=request.getParameter("order_id").toString();
		//System.out.println(sta+"`````````"+order_id);
		
		boolean v=new Management().cope_sta(order_id, Integer.valueOf(sta));
		if(v) {
			response.getWriter().print("yes");
			}
			else
			response.getWriter().print("no");
	}
	
	
	//处理订单
	public void show_process(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String order_id=request.getParameter("order_id").toString();
		
		
		List<TbOrderItem> orderItems=new Management().Order_item_By_order_id(order_id);
		
		TbOrder order=new check_user().Show_order_By_order_id(order_id);
		TbAddress address=new check_user().Order_Info_address(order);
		List<TbBook> books=new check_user().order_item_books(orderItems);
		/*System.out.println(orderItems.size());
		System.out.println(books.size());*/
		
		request.setAttribute("order",order);
		request.setAttribute("books",books);
		request.setAttribute("address",address);
		request.setAttribute("orderItems", orderItems);
		request.getRequestDispatcher("order-process.jsp").forward(request,response);
	}
	
	
	
	
	//显示订单的详情
	public void order_print(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String order_id=request.getParameter("order_id").toString();
		
		
		List<TbOrderItem> orderItems=new Management().Order_item_By_order_id(order_id);
		
		TbOrder order=new check_user().Show_order_By_order_id(order_id);
		TbAddress address=new check_user().Order_Info_address(order);
		List<TbBook> books=new check_user().order_item_books(orderItems);
		/*System.out.println(orderItems.size());
		System.out.println(books.size());*/
		
		request.setAttribute("order",order);
		request.setAttribute("books",books);
		request.setAttribute("address",address);
		request.setAttribute("orderItems", orderItems);
		request.getRequestDispatcher("order-print.jsp").forward(request,response);
	}
	
	public void show_detail(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{	
		String order_id=request.getParameter("order_id").toString();
		System.out.println(order_id);
		
		List<TbOrderItem> orderItems=new Management().Order_item_By_order_id(order_id);
		
		TbOrder order=new check_user().Show_order_By_order_id(order_id);
		TbAddress address=new check_user().Order_Info_address(order);
		List<TbBook> books=new check_user().order_item_books(orderItems);
		/*System.out.println(orderItems.size());
		System.out.println(books.size());*/
		
		request.setAttribute("order",order);
		request.setAttribute("books",books);
		request.setAttribute("address",address);
		request.setAttribute("orderItems", orderItems);
		request.getRequestDispatcher("order-detail.jsp").forward(request,response);
		
		
		
	}
	
	
	//管理员显示所有订单
		public void show_all_orders(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
		{
			List<TbOrder> tbOrders=new Management().orders_Mangement();
			request.setAttribute("tborders_all", tbOrders);
			
			TbUser user=(TbUser) session.getAttribute("management");
			System.out.println(user.getUname());
			System.out.println("321");

			
			request.getRequestDispatcher("order-list.jsp").forward(request, response);
			
		}
		
		

}
