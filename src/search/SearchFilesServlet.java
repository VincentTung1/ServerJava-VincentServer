package search;

import attr.FileSaveUtil;
import util.json.JSONArray;
import util.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vincent_tung on 2017/10/11.
 */
@WebServlet("/SearchFiles")
public class SearchFilesServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 这个路径相对当前应用的目录
        String uploadPath = FileSaveUtil.getUploadSavePath(this);
        File uploadDir = new File(uploadPath);
        if (uploadDir.exists()){
            File[] files = uploadDir.listFiles();
            resp.setCharacterEncoding("utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject data = new JSONObject();
            JSONArray jsonFiles = new JSONArray();
            for (File file :
                    files) {
                String fileName = file.getName();
                if (!fileName.equals(".DS_Store")){  //去除不必要的配置文件
                    jsonFiles.put(file.getName());
                }
            }
            data.put("files",jsonFiles);
            writer.write(data.toString());
        }
    }

}
