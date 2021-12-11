package cn.zly.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.pojo.TbBook;
import cn.test.DisplayBooks;
import cn.test.check_user;

/**
 * Servlet implementation class Book_collect
 */
public class Book_collect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session ;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book_collect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        System.out.println("11111111"+method);
        if(method.equals("Book_collect")){
            this.book_collect(request,response);
        }
        if(method.equals("Show_collect"))
        {
        	this.Show_collect(request,response);
        }
        
	}
	
	
	public void Show_collect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		int user_id=(Integer) session.getAttribute("user_id");
		System.out.println(user_id);
		List<TbBook> collect_books=new ArrayList<TbBook>();
		
			collect_books = new check_user().Show_user_collect(user_id);
			request.setAttribute("collect_books",collect_books);
			
	        request.getRequestDispatcher("collect.jsp").forward(request,response);
		
	
	
	}
	public void book_collect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String isbn = request.getParameter("isbn");
        String exists = request.getParameter("exits");
        List<TbBook> book;
        try {
			book =  new check_user().queryBooks(isbn);
			request.setAttribute("book",book);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        //
        //i=Integer.valueOf(s).intValue();
        
        //
        int user_id = Integer.valueOf( session.getAttribute("user_id").toString());
        
        System.out.println(user_id);
        System.out.println(isbn);
        if("1".equals(exists.trim()))
        	{new check_user().User_collect_remove(user_id, isbn);}
        if("0".equals(exists.trim()))
        	{new check_user().User_collect_book(user_id, isbn);}
        
        request.getRequestDispatcher("detail.jsp").forward(request,response);
        
		
        
        
        
		
	}

}
