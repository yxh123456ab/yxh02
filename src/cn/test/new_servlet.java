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
// ������Ӧ��������
		response.setCharacterEncoding("utf-8");

// Ҫ�ض������λ��
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

		// ��ȡ������Ϣ(����ʱ����ͨ��get��ʽ��URL�����name)
		// http://localhost:8080/servlet_demo/helloword?name=123
		String name = req.getParameter("name");

		// ���������Ĵ����߼�
		System.out.println("��������ȡ�������:" + name);
		System.out.println("�ڶ���������ִ��--��վ���ƣ�www.runoob.com");
		
		if ("123".equals(name)) {
			// �����󴫻ع�����
			chain.doFilter(req, resp);
		} else {
			// ���÷�����������
			resp.setContentType("text/html;charset=GBK");

			// ��ҳ�������Ӧ��Ϣ
			PrintWriter out = resp.getWriter();
			out.print("<b>name����ȷ���������أ����ܷ���web��Դ</b>");
			System.out.println("name����ȷ���������أ����ܷ���web��Դ");
		}
	}

}