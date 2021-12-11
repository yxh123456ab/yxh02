package cn.zly.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import cn.test.check_user;

/**
 * Servlet implementation class new_Don_tKnow
 */
public class new_Don_tKnow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public new_Don_tKnow() {
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
        String method = request.getParameter("method");
        if(method.equals("check_one")){
        	check_one(request,response);
        }
        if(method.equals("check_one_payment"))
        {
        	check_one_payment(request,response);
        }
        if(method.equals("Show_order"))
        {
        	Show_order(request,response);
        }
        if(method.equals("order_info"))
        {
        	order_info(request,response);
        }
        if(method.equals("cartsubmit"))
        {
        	cartsubmit(request,response);
        }
        if(method.equals("sure_receive"))
        {
        	sure_receive(request,response);
        }
	}
	
	public void sure_receive(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String order_id=request.getParameter("order").toString();
		boolean v=new check_user().sure_receive(order_id);
		Show_order(request, response);
		
	}
	
	
	
	public void cartsubmit(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		System.out.println("submit");
	}
	
	//��ת��order_info����
	public void order_info(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		
		String order_id=request.getParameter("order").toString();
		String isbn=request.getParameter("isbn").toString();
		String user_id=session.getAttribute("user_id").toString();
		
		List<TbOrderItem> orderItem=new check_user().Order_item_By_order_id(order_id);
		TbOrder order=new check_user().Show_order_By_order_id(order_id);
		TbAddress address=new check_user().Order_Info_address(order);
		List<TbBook> Books=new check_user().order_book_detail(order_id);
		
		
		request.setAttribute("orderItem", orderItem);
		request.setAttribute("order", order);
		request.setAttribute("address",address);
		request.setAttribute("books",Books);
		
		request.getRequestDispatcher("order-info.jsp").forward(request,response);
		/*System.out.println(orderItem.getPrice());
		System.out.println(address.getAddress());
		System.out.println(order.getPlaced());
		System.out.println(order.getPayment());*/
		
		/*System.out.println(order_id);
		System.out.println(isbn);*/
		//System.out.println(order_id);
		
	}
	
	
	//��ת����������
	public void Show_order(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String user_id=session.getAttribute("user_id").toString();
		
		//�õ������û���id��orderItem
		List<TbOrderItem> tbOrderItems =new check_user().Order_item_By_user_id(Integer.valueOf(user_id));
		//�õ������û���id��order
		List<TbOrder> tbOrders=new check_user().orders(Integer.valueOf(user_id));
		//�õ������û��Ķ�����ϸ��Ϣ���ع������
		List<TbBook> Books=new check_user().order_item_books(tbOrderItems);
		//�õ���������Ʒ����ĸ���
		List<Integer> counts=new check_user().counts(Integer.valueOf(user_id));
		
		
		//System.out.println(tbOrderItems.size()+"```"+tbOrderItems.size()+"```"+Books.size()+"```"+counts.size());
		request.setAttribute("Order_items", tbOrderItems);
		request.setAttribute("tborders", tbOrders);
		request.setAttribute("Books",Books);
		request.setAttribute("counts", counts);
		request.getRequestDispatcher("order.jsp").forward(request,response);
	}
	
	
	//���������֧��
	public void check_one_payment(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String user_id=request.getParameter("user_id").toString();
		String address_id=request.getParameter("address_id").toString();
		String zj=request.getParameter("zj").toString();
		String order_id=request.getParameter("order_id").toString();
		
		//
		List<TbBook> books=(List<TbBook>) session.getAttribute("order_books");
		List<String> isbns=new ArrayList<String>();
		List<Integer> counts=(List<Integer>) session.getAttribute("order_num");
		/*String isbn=request.getParameter("isbn").toString();

		String count=request.getParameter("counts").toString();*/
		//��ֹ��Ϊˢ�µ��¶����Ķ����ύ������order_id����̨�Ƿ���ڸö���
		boolean m=true;
		m=new check_user().order_id_exits(order_id);
		System.out.println(books.size()+"qqqqqqq"+counts.size());
		if(m==true)
		{
			//ʲô�������ͺ��ˣ���������Щ���ĵ�����ˢ����֧���ɹ�ҳ����û��ǣ��Ҷ����Ǳ���΢Ц�ͺ���		-���԰����ǵĿ���
		}
		else
		{	new check_user().Buy_One_now(Integer.valueOf(user_id),
				Integer.valueOf(address_id), order_id, Double.parseDouble(zj));
			for(int i=0;i<counts.size();i++)
			{
				
				new check_user().Buy_One_now_item(books.get(i).getIsbn(), books.get(i).getPrice()*counts.get(i), counts.get(i),order_id);
				new check_user().delete_cart(books.get(i).getIsbn(), Integer.valueOf(user_id));
			}
			
		}
		
		request.setAttribute("zj", zj);
		request.setAttribute("order_id",order_id);
		request.getRequestDispatcher("pay-success.jsp").forward(request,response);
		
		
	}
	
	
	public void check_one(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		//String isbn = request.getParameter("isbn").toString();
		String address_id=request.getParameter("address").toString();
		String zj=request.getParameter("zj").toString();
		String user_id=request.getParameter("user_id").toString();
		//String counts=request.getParameter("counts").toString();
		String order_id=check_user.Create_order_id();
		
		
		
		
		//request.setAttribute("isbn",isbn);
		request.setAttribute("address_id",address_id);
		request.setAttribute("zj",zj);
		request.setAttribute("user_id",user_id);
		request.setAttribute("order_id",order_id);
		
		request.getRequestDispatcher("payment.jsp").forward(request,response);
		
		
		
		/*System.out.println(isbn);
		System.out.println(address_id);
		System.out.println(zj);
		System.out.println(user_id);*/
	}

}
