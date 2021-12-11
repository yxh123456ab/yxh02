package cn.test;

import cn.DButil.DatabaseDAO;

/*import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.pojo.TbUser;

*//**
 * Servlet implementation class Login
 *//*
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method.equals("userLogin")) {
			userLogin(request, response);
		}
	}
	
	public void userLogin (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		System.out.println(uname);
		System.out.println(upwd);
		TbUser aTbUser=new TbUser();
		try {
			aTbUser=new check_user().check_user_exist(uname, upwd);
			System.out.println(aTbUser.getUserId());;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbUser a=new TbUser();
		try {
			a=new check_user().check_user_exist(uname, upwd);
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", aTbUser);
		System.out.println(aTbUser);
		//System.out.println(session.getAttribute("user"));
		TbUser mTbUser=(TbUser) session.getAttribute("user");
		System.out.println(mTbUser.getUserId()+mTbUser.getPhone());
		request.getRequestDispatcher("test_01.jsp").forward(request, response);
		
	}

}*/

import cn.itheima.pojo.TbUser;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@WebServlet(name = "Login")
public class Login extends HttpServlet {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet res = null;
    HttpSession session = null;
    String sql = "";
    DataDAO dd = new DataDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        System.out.println("111===1111");
        if(method.equals("userLogin")){
            this.userLogin(request,response);
        }else if(method.equals("userRegister")){
            this.userRegister(request,response);
        }
        else if(method.equals("isNameExist")){
            this.isNameExist(request,response);
        }

    }

    public void isNameExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("uname");
        String sql = "select * from tb_user where uName = ?";
        List<TbUser> user = dd.getData(sql,TbUser.class,uName);
        System.out.println("user.size = "+user.size());
        if(user.size()>0){
            response.getWriter().print("yes");
        }else{
            response.getWriter().print("no");
        }
    }
    
    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        //获取用户的所有信息
        TbUser user = (TbUser)check_user_exist(uname, upwd);

        //判断是否存在这个用户

        session = request.getSession();
        if(user != null){
            //如果用户存在，把用户的信息放入回话
            session.setAttribute("user",user);
            
            int user_id = user.getUserId();
            //id常用，单独放一个
            session.setAttribute("user_id", user_id);
            
            
            
            //跳转到主页面
            response.sendRedirect("displayBooks?method=displayHotBooks");
            //response.sendRedirect("displayBooks?method=displayHotBooks");
            //request.getRequestDispatcher("home.jsp").forward(request,response);
        }else{
        	/*PrintWriter out = response.getWriter();
        	out.println("alert('aaa');");*/
            request.setAttribute("error","yes");
            System.out.println("ok1");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
        
    }

    //根据user，password 返回用户id
    public TbUser check_user_exist(String uname, String password)
    {
        TbUser user = new TbUser();
       
        sql="select * from tb_user where uname=? and upwd=? and role=1";
        try {
            conn=new DatabaseDAO().getConn();
            ps=conn.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, password);

            res= ps.executeQuery();

            if( res.next())
            {
                /*v.setUserId(res.getInt("user_id"));
                v.setPhone(res.getString("phone"));
                v.setUname(res.getString("uname"));
                v.setUpwd(res.getString("upwd"));
                v.setEmail(res.getString("email"));
                v.setRole(res.getInt("role"));*/
                int user_id = res.getInt("user_id");
                String phone = res.getString("phone");
                //String uname = res.getString("uname");
                //String upwd = res.getString("upwd");
                String email = res.getString("email");
                int role = res.getInt("role");
                user = new TbUser(user_id,phone,uname,password,email,role);
                conn.close();
            }
            else
            {
                user = null;
                //user.setUserId(-1);
                
                System.out.println("不存在");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 处理用户注册的函数
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户提交的数据
        String name = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int flag = 0 ;

        String sql = "insert into tb_user(uname,upwd,email,phone,role) values(?,?,?,?,1)";

        try {
            Connection conn = (new DatabaseDAO()).getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,upwd);
            ps.setString(3,email);
            ps.setString(4,phone);
            flag = ps.executeUpdate();
            System.out.println("成功添加了"+flag+"条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("login.jsp");

    }


}

