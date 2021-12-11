package cn.test;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.pojo.TbAddress;
import cn.itheima.pojo.TbBook;
import cn.itheima.pojo.TbCartItem;
import cn.itheima.pojo.TbOrderItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditCart")
public class EditCart extends HttpServlet {

    HttpSession session;
    DataDAO dd = new DataDAO();
    DisplayCart dc = new DisplayCart();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	session= request.getSession();
    	request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("numAdd")){
            this.numEdit("add",request,response);
        }else if(method.equals("numMinus")){
            this.numEdit("minus",request,response);
        }else if(method.equals("submitOrder")) {
        	submitOrder(request,response);
        }else if(method.equals("numJudge")){
            this.numJudge(request,response);
        }
    }

    
    
    public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前台传递的所有订单的信息整合为一个长字符串
    	
        String orderStr = request.getParameter("data");
        //将整个字符串分割为每个订单的信息
        String[] orderArray = orderStr.split(",");
        //System.out.println(jsonArray.length);
        //订单的数量
        List<TbOrderItem> orderItems=new ArrayList<TbOrderItem>();
        TbOrderItem orderItem=new TbOrderItem();
        int orderNum = orderArray.length;
        int user_id=Integer.valueOf(session.getAttribute("user_id").toString());
        System.out.println("user_id"+user_id);
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
            
        {	orderItem=new TbOrderItem();
        	orderItem.setProduct(orders[i][0]);
           	orderItem.setCount(Integer.valueOf(orders[i][1]));
           	orderItems.add(orderItem);
        }
        //
        List<TbBook> books =new ArrayList<TbBook>();
        List<Integer> counts=new ArrayList<Integer>();
        for(int i=0;i<orderItems.size();i++)
        {	
        	TbBook book=new check_user().show_book_detail(orderItems.get(i).getProduct());
        	counts.add(orderItems.get(i).getCount());
        	//System.out.println(book.getAuthor()+"````````````"+book.getTitle());
        	books.add(book);
        }
        List<TbAddress> Any_address=new check_user().User_id_address(user_id);
        
        System.out.println(counts.size()+"````````"+books.size()+"``````"+Any_address.size());
        
        request.setAttribute("books",books);
		request.setAttribute("Addresses",Any_address);
		request.setAttribute("num",counts);
		session.setAttribute("order_books", books);
		session.setAttribute("order_address", Any_address);
		session.setAttribute("order_num", counts);
		//System.out.println(Any_address.get(1).getReceiverPhone());
		request.getRequestDispatcher("order-confirm.jsp").forward(request,response);
        
        
        
        
        /*System.out.println(orderItems.size());
        System.out.println(orderItems.get(0).getProduct()+"````"+orderItems.get(0).getCount());     
        System.out.println(orderItems.get(1).getProduct()+"````"+orderItems.get(1).getCount()); */       
    }
    
    public void numJudge (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");
        String sql = "select * from tb_cart_item where product = ?";
        List<TbCartItem> list = new ArrayList<TbCartItem>();
        list = dd.getData(sql,TbCartItem.class,product);
        int count = list.get(0).getCount();
        if(count>1){
            response.getWriter().print("ok");
        }else{
            response.getWriter().print("no");
        }

    }
    
    
    /**
     * 对购物车中商品的添加和删除，在一个函数中进行处理
     * @param method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void numEdit(String method,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        String user_id = session.getAttribute("user_id").toString();
        String product = request.getParameter("product");
        String sql = "";
        if(method.equals("add")){
            sql = "update tb_cart_item set count = count+1 where user_id=? and product =?";
        }else if(method.equals("minus")){
            sql = "update tb_cart_item set count = count-1 where user_id=? and product =?";
        }

        System.out.println(sql+user_id+product);
        dd.executeSql(sql, user_id, product);
        System.out.println("here");
        response.getWriter().print("yes");
        //dc.displayCart(request, response);
    }


}
