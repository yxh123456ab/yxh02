package cn.test;

import cn.itheima.pojo.TbCartItem;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DisplayCart")
public class DisplayCart extends HttpServlet {

    HttpSession session;
    DataDAO dd = new DataDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("displayCart")){
            this.displayCart(request,response);
        }
    }

    public void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();

        int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
        System.out.println(user_id);
        String sql = "SELECT user_id as userId,product,count,title,author,price FROM view_cart where user_id=?";
        List<TbCartItem> tbCartItems = new ArrayList<TbCartItem>();
        tbCartItems = dd.getData(sql,TbCartItem.class,user_id);
        for(int i=0;i<tbCartItems.size();i++){
            System.out.println(tbCartItems.get(i));
        }
        request.setAttribute("tbCartItems",tbCartItems);
        request.getRequestDispatcher("cart.jsp").forward(request,response);
    }


}
