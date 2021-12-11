package cn.test;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCartItem")
public class DeleteCartItem extends HttpServlet {

    DataDAO dd = new DataDAO();
    DisplayCart dc = new DisplayCart();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("deleteItem")){
            this.deleteItem(request,response);
        }
    }

    public void deleteItem (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String product = request.getParameter("product");
        //System.out.println(product+"abc");
        String sql = "delete from tb_cart_item where product = ?";
        int flag = dd.executeSql(sql,product);
        System.out.println("É¾³ýÁË"+flag+"Ìõ¼ÇÂ¼");
        dc.displayCart(request,response);
    }


}
