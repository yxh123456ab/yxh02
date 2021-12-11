package cn.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloForm
 */
@WebServlet("/HelloForm")
public class new_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 设置响应内容类型
		response.setCharacterEncoding("utf-8");

// 要重定向的新位置
		String site = new String("http://www.baidu.com");

		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 获取请求信息(测试时可以通过get方式在URL中添加name)
		// http://localhost:8080/servlet_demo/helloword?name=123
		String name = req.getParameter("name");

		// 过滤器核心代码逻辑
		System.out.println("过滤器获取请求参数:" + name);
		System.out.println("第二个过滤器执行--网站名称：www.runoob.com");
		
		if ("123".equals(name)) {
			// 把请求传回过滤链
			chain.doFilter(req, resp);
		} else {
			// 设置返回内容类型
			resp.setContentType("text/html;charset=GBK");

			// 在页面输出响应信息
			PrintWriter out = resp.getWriter();
			out.print("<b>name不正确，请求被拦截，不能访问web资源</b>");
			System.out.println("name不正确，请求被拦截，不能访问web资源");
		}
	}

}