package cn.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.DButil.DatabaseDAO;
import cn.itheima.pojo.TbCartItem;

/**
 * Servlet implementation class CartAddBook
 */
@WebServlet(name = "CartAddBook")
public class CartAddBook extends HttpServlet {
    DatabaseDAO db = new DatabaseDAO();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet res = null;
    String sql = "";
    HttpSession session ;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("cartAddBook")){
            this.cartAddBook(request,response);
        }
        if(method.equals("submitOrder"))
        {
        	submitOrder(request,response);
        }
    }
    //提交订单
    public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前台传递的所有订单的信息整合为一个长字符串
        String orderStr = request.getParameter("data");
        //将整个字符串分割为每个订单的信息
        String[] orderArray = orderStr.split(",");
        //System.out.println(jsonArray.length);
        //订单的数量
        int orderNum = orderArray.length;
        //存放每个订单的isbn和count
        String[][] orders = new String[orderNum][2];
        for (int i = 0; i < orderNum; i++)
            for (int j = 0; j < 2; j++) {
                String[] order = orderArray[i].split("-");
                orders[i][0] = order[0];
                orders[i][1] = order[1];
            }
        //JSONArray jsonArray = JSONArray.fromObject();
        for (int i = 0; i < orderNum; i++)
            for (int j = 0; j < 2; j++)
                System.out.println(orders[i][j]);
    }

    
    
    public void cartAddBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String isbn = request.getParameter("isbn");
        int count = Integer.parseInt(request.getParameter("count"));
        System.out.println(count);
        int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
        System.out.println("aaaaaa----------"+user_id);
        System.out.println("user_id="+user_id);
        System.out.println("isbn="+isbn);
        System.out.println("count="+count);
        cart_add(user_id,isbn,count);
        response.getWriter().print("yes");
       // System.out.println(isbn+"啊啊啊"+count);
    }


    public void cart_add(int user_id,String product,int count)
    {
        sql=null;
        boolean v= cart_exist_books(user_id, product);
        //System.out.println(v);
        if(v==true)
        {
            sql="update tb_cart_item set count=count+? where user_id=? and product=?;";
            con=new DatabaseDAO().getConn();

            try {
                ps=con.prepareStatement(sql);
                ps.setInt(1, count);
                ps.setString(2, String.valueOf(user_id));
                ps.setString(3, product);
                ps.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            sql="INSERT INTO `tb_cart_item` (`user_id`, `product`, `count`) VALUES (?,?,?)" ;

            con=new DatabaseDAO().getConn();

            try {
                ps=con.prepareStatement(sql);
                ps.setString(1, String.valueOf(user_id));
                ps.setString(2, product);
                ps.setInt(3, count);
                ps.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        new DatabaseDAO().closeAll(res, ps, con);


    }
    //判断购物车是否存在该商品
    public boolean cart_exist_books(int user_id,String product)
    {
        sql=null;
        boolean v=false;
        sql="SELECT * FROM bookshop.tb_cart_item where user_id=? and product=?";

        con=new DatabaseDAO().getConn();


        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, product);
            res=ps.executeQuery();
            if(res.next())
            {
                v=true;
            }
            else
                v=false;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new DatabaseDAO().closeAll(res, ps, con);
        return v;
    }

    //经过编辑后，更新购物车
    public void Update_cart(List<TbCartItem> tbCartItems)
    {	
        int num=tbCartItems.size();
        con=new DatabaseDAO().getConn();


        try {
            for(int i=0;i<num;i++) {
                ps=null;
                sql=null;
                sql="UPDATE `tb_cart_item` SET `count` = ? WHERE user_id=? and product=?";
                ps=con.prepareStatement(sql);
                ps.setInt(1, tbCartItems.get(i).getCount());
                ps.setString(2, tbCartItems.get(i).getUserId());
                ps.setString(3, tbCartItems.get(i).getProduct());
                ps.executeUpdate();
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new DatabaseDAO().closeAll(res, ps, con);
    }





}
