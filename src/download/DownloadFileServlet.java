package download;

import attr.FileSaveUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by vincent_tung on 2017/10/12.
 */
@WebServlet("/DownloadFile")
public class DownloadFileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        // 这个路径相对当前应用的目录
        String uploadPath = FileSaveUtil.getUploadSavePath(this);
        File dir = new File(uploadPath);
        if (dir.exists()){
            File[] files = dir.listFiles();
            for (File file :
                    files) {
                if (file.getName().equals(fileName)){
                    if(file.exists()){
                        FileInputStream fis = new FileInputStream(file);
                        String filename= URLEncoder.encode(file.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
                        byte[] b = new byte[fis.available()];
                        fis.read(b);
                        resp.setCharacterEncoding("utf-8");
                        resp.setHeader("Content-Disposition","attachment; filename="+filename+"");
                        //获取响应报文输出流对象
                        ServletOutputStream out =resp.getOutputStream();
                        //输出
                        out.write(b);
                        out.flush();
                        out.close();
                    }
                }
            }
        }

    }
}
