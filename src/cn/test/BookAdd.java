package cn.test;

import cn.itheima.pojo.TbBook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookAdd")
public class BookAdd extends HttpServlet {

    DataDAO dd = new DataDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("checkIsbn")){
            this.checkIsbn(request,response);
        }else if(method.equals("addBook")){
            //this.addBook(request,response);
            this.uploadFile(request,response);
        }
    }

    protected void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建上传文件项的工厂对象
        FileItemFactory factory = new DiskFileItemFactory();
        //获得文件上传的核心类对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        String isbn = null;
        String title = null;
        String author = null;
        String price = null;
        String pages = null;
        String words = null;
        String press = null;
        String edition = null;
        String published = null;
        String format = null;
        String packaging = null;
        String form = null;



        //设置上传文件的大小、类型、编码格式等
        upload.setFileSizeMax(10*1024*1024);
        //设置上传文件的总大小
        upload.setSizeMax(50*1024*1024);
        upload.setHeaderEncoding("gbk");

        //判断是否是文件上传的类型
        if (ServletFileUpload.isMultipartContent(request)) {
            //将上传的文件数据转换为对应的List集合
            try {
                List<FileItem> list = upload.parseRequest(request);
                //遍历上传文件的各个属性和值
                for (FileItem item : list) {
                    //判断是否为普通的表单数据(非file类型的)
                    if (item.isFormField()) {
                        //获取对应表单元素的名字
                        String name = item.getFieldName();
                        //获取对应控件的值
                        String value = item.getString("UTF-8");
                        if(name.equals("isbn")){
                            isbn = value;
                        }else if(name.equals("title")){
                            title = value;
                        }else if(name.equals("author")){
                            author = value;
                        }else if(name.equals("price")){
                            price = value;
                        }else if(name.equals("pages")){
                            pages = value;
                        }else if(name.equals("words")){
                            words = value;
                        }else if(name.equals("press")){
                            press = value;
                        }else if(name.equals("edition")){
                            edition = value;
                        }else if(name.equals("published")){
                            published = value;
                        }else if(name.equals("format")){
                            format = value;
                        }else if(name.equals("packaging")){
                            packaging = value;
                        }else if(name.equals("form")){
                            form = value;
                        }

                        System.out.println("*************"+name+":"+value);
                    } else //如果为上传文件的file控件
                    {
                        //获取上传文件的名字
                        String name = item.getName();

                        String folderPath = "E:\\I want\\bookshop\\WebContent\\user\\img\\goods\\"+isbn;

                        File folder = new File(folderPath);
                        if(!folder.exists()){
                            //如果此文件夹不存在，就创建
                            folder.mkdir();
                            folder.setWritable(true);
                            folder.setReadable(true);
                        }

                       // String path = getServletContext().getRealPath(folderPath);

                        //System.out.println("文件目录：");
                        //System.out.println("folderPath=============================="+folderPath);
                        //System.out.println("path============================"+path);

                        //创建要上传文件的对象
                        File file  = new File(folder, name);
                        System.out.println("%%%%%%%%%%%%%%"+file.getName());
                        //将文件保存到 对应的路径
                        item.write(file);
                        //删除上传中产生的临时文件
                        item.delete();

                    }
                }

            } catch (Exception e) {
                //throw new RuntimeException(e);

            }finally {
                String sql = "insert into tb_book(isbn,title,author,price,pages,words,press,edition," +
                        "                        published,format,packaging,form)" +
                        " values(?,?,?,?,?,?,?,?,?,?,?,?)";
                int flag = dd.executeSql(sql,isbn,title,author,price,pages,words,press,edition,
                        published,format,packaging,form);
                System.out.println("成功添加了"+flag+"本图书");
                response.sendRedirect("index.jsp");
            }
        } else {

        }

    }

    /**
     * 添加新书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("adsadasdadasd");

    }


    /**
     * 添加图书时，验证isbn是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkIsbn (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String sql = "select * from tb_book where isbn = ?";
        List<TbBook> book = new ArrayList<TbBook>();
        book = dd.getData(sql,TbBook.class,isbn);
        if(book.size()==0){
            response.getWriter().print("notExist");
        }else{
            response.getWriter().print("exist");
        }
    }




}
