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
        //�����ϴ��ļ���Ĺ�������
        FileItemFactory factory = new DiskFileItemFactory();
        //����ļ��ϴ��ĺ��������
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



        //�����ϴ��ļ��Ĵ�С�����͡������ʽ��
        upload.setFileSizeMax(10*1024*1024);
        //�����ϴ��ļ����ܴ�С
        upload.setSizeMax(50*1024*1024);
        upload.setHeaderEncoding("gbk");

        //�ж��Ƿ����ļ��ϴ�������
        if (ServletFileUpload.isMultipartContent(request)) {
            //���ϴ����ļ�����ת��Ϊ��Ӧ��List����
            try {
                List<FileItem> list = upload.parseRequest(request);
                //�����ϴ��ļ��ĸ������Ժ�ֵ
                for (FileItem item : list) {
                    //�ж��Ƿ�Ϊ��ͨ�ı�����(��file���͵�)
                    if (item.isFormField()) {
                        //��ȡ��Ӧ��Ԫ�ص�����
                        String name = item.getFieldName();
                        //��ȡ��Ӧ�ؼ���ֵ
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
                    } else //���Ϊ�ϴ��ļ���file�ؼ�
                    {
                        //��ȡ�ϴ��ļ�������
                        String name = item.getName();

                        String folderPath = "E:\\I want\\bookshop\\WebContent\\user\\img\\goods\\"+isbn;

                        File folder = new File(folderPath);
                        if(!folder.exists()){
                            //������ļ��в����ڣ��ʹ���
                            folder.mkdir();
                            folder.setWritable(true);
                            folder.setReadable(true);
                        }

                       // String path = getServletContext().getRealPath(folderPath);

                        //System.out.println("�ļ�Ŀ¼��");
                        //System.out.println("folderPath=============================="+folderPath);
                        //System.out.println("path============================"+path);

                        //����Ҫ�ϴ��ļ��Ķ���
                        File file  = new File(folder, name);
                        System.out.println("%%%%%%%%%%%%%%"+file.getName());
                        //���ļ����浽 ��Ӧ��·��
                        item.write(file);
                        //ɾ���ϴ��в�������ʱ�ļ�
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
                System.out.println("�ɹ������"+flag+"��ͼ��");
                response.sendRedirect("index.jsp");
            }
        } else {

        }

    }

    /**
     * �������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("adsadasdadasd");

    }


    /**
     * ���ͼ��ʱ����֤isbn�Ƿ����
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
