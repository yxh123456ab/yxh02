package cn.test;

import cn.itheima.pojo.TbBook;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DisplayAllBook")
public class BookEdit extends HttpServlet {

    DataDAO dd = new DataDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("displayAllBooks")){
            this.displayAllBooks(request,response);
        }else if(method.equals("showDetail")){
            this.showDetail("view",request,response);
        }else if(method.equals("editBook")){
            this.showDetail("edit",request,response);
        }else if(method.equals("reviseBook")){
            this.reviseBook(request,response);
        }
    }

    /**
     * �����ύ��isbn��price�޸�
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void reviseBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String price = request.getParameter("price");
        String sql = "update tb_book set price = ? where isbn = ?";
        int flag = dd.executeSql(sql,price,isbn);
        System.out.println("reviseBook�޸���"+flag+"������");
        response.getWriter().print("yes");
    }

    /**
     * ����鿴����ʾ��ͼ�����ϸ��Ϣ
     * ��Ϊ�༭Ҳ��Ҫչʾͼ��ĵ���Ϣ������ת��ҳ�治ͬ�����Լ�һ���ַ��������ж�
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showDetail (String balabala,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String sql = "select * from tb_book where isbn = ?";
        List<TbBook> book = new ArrayList<TbBook>();
        book = dd.getData(sql,TbBook.class,isbn);
        request.setAttribute("book",book);
        if(balabala.equals("view")){
            request.getRequestDispatcher("product-detail.jsp").forward(request,response);
        }else if(balabala.equals("edit")){
            request.getRequestDispatcher("product-edit.jsp").forward(request,response);
        }

    }

    /**
     * չʾ���е�ͼ����Ϣ
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void displayAllBooks (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select * from tb_book";
        List<TbBook> books = new ArrayList<TbBook>();
        books = dd.getData(sql,TbBook.class);
        request.setAttribute("books",books);
        request.getRequestDispatcher("product-list.jsp").forward(request,response);
    }

}
