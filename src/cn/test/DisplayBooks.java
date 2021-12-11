package cn.test;

import cn.DButil.DatabaseDAO;
import cn.itheima.pojo.TbBook;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DisplayBooks")
public class DisplayBooks extends HttpServlet {

    DatabaseDAO db = new DatabaseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("displayHotBooks")){
            this.displayHotBooks(request,response);
        }else if(method.equals("queryBook")){
            this.queryBook(request,response);
        }
    }

    //鍦ㄤ富椤靛睍绀虹儹閿�鍥句功
    //鐩存帴灞曠ず鐩墠鏁版嵁搴撴墍鏈夌殑鍥句功
    public void displayHotBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TbBook> books;
		try {
			books = queryBooks("all");
			request.setAttribute("books",books);
			System.out.println(books.get(1).getAuthor());
			System.out.println("=====+++");
	        request.getRequestDispatcher("index.jsp").forward(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }


    public void queryBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIsbn = request.getParameter("isbn");
        List<TbBook> book;
       /* List<TbBook> books = (List<TbBook>) request.getAttribute("books");
        System.err.println(books.get(0).getPrice());*/
		try {
			book = queryBooks(bookIsbn);
			request.setAttribute("book",book);
			
			//
			/*List<TbBook> books01 = (List<TbBook>)request.getAttribute("books");
	        System.err.println(books01.get(0).getPrice()); 
	        //List<TbBook> books=request.getAttribute("books");
	        System.out.println(books01.size());
	        for(int i=0;i<books01.size();i++)
	        	System.err.println(books01.get(i).getAuthor());
			//
*/			
	        request.getRequestDispatcher("detail.jsp").forward(request,response);
	        
	       
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    //鏌ヨ鍑烘墍鏈夌殑鍥句功淇℃伅
    //鎴栬�呮煡璇㈡寚瀹氱殑鍥句功
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
            Connection conn = db.getConn();
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
        System.out.println(TbBooks.get(0).getAuthor());
        return TbBooks;
        
    }


}
