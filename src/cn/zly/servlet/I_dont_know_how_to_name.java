package cn.zly.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.pojo.TbUser;
import cn.test.check_user;

/**
 * Servlet implementation class I_dont_know_how_to_name
 */
public class I_dont_know_how_to_name extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null; 
	String method;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I_dont_know_how_to_name() {
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
		method = null;
        method = request.getParameter("method");
        if(method==null)
        {
        	System.out.println("qweertr");
        	System.out.println(method);
        }
        if(method.equals("Change_upwd"))
        {
        	Change_upwd(request,response);
        }
        
        if(method.equals("start"))
        {
        	check_mangement(request,response);
        }
        
        if(method.equals("check_old_pass"))
        {
        	check_old_pass(request,response);
        }
        if(method.equals("change"))
        {
        	change_password_now(request,response);
        }
	}
	//修改密码
	public void change_password_now(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		
		String uid=request.getParameter("uid").toString();
		String pass=request.getParameter("cpwd").toString();
		System.out.println(uid+"··aaa··"+pass);
		boolean v=new check_user().Change_password(Integer.valueOf(uid), pass);
		if(v) {
			response.getWriter().print("yes");
			}
			else
			response.getWriter().print("no");
	}
	
	
	//ajks修改密码检查旧密码
	public void check_old_pass(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String uid=request.getParameter("uid").toString();
		String pass=request.getParameter("upwd").toString();
		System.out.println(uid+"`````"+pass);
		boolean v=false;
		v=new check_user().Check_user_id_password(Integer.valueOf(uid), pass);
		if(v) {
			response.getWriter().print("yes");
			}
			else
			response.getWriter().print("no");
	}
	
	
	//修改密码
	public void Change_upwd(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String user_id=session.getAttribute("mange_id").toString();
		TbUser user =(TbUser)session.getAttribute("management");
		System.out.println(user_id);
		request.getRequestDispatcher("password-change.jsp").forward(request, response);
		
	}
	
	
	//管理员登录确认
	public void check_mangement(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        
        TbUser user=new Management().Manage_exist(uname, upwd);
        if(user==null)
        {
        	request.getRequestDispatcher("login.jsp").forward(request,response);
        }
        else
        {
        	session.setAttribute("management", user);
        	session.setAttribute("mange_id", user.getUserId());
        	request.setAttribute("user", user);
        	request.setAttribute("user_id", user.getUserId());
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
	}

}
