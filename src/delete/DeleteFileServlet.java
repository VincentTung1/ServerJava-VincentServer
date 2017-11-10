package delete;

import attr.FileSaveUtil;
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
 * Created by vincent_tung on 2017/10/13.
 */
@WebServlet("/DeleteFile")
public class DeleteFileServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        String path = FileSaveUtil.getUploadSavePath(this);
        File dir = new File(path);
        if (dir.exists()){
            File[] files = dir.listFiles();
            for (File file :
                    files) {
                if (file.getName().equals(fileName)) {
                    file.delete();
                    PrintWriter writer = resp.getWriter();
                    JSONObject obj  =new JSONObject();
                    obj.put("code","0000");
                    writer.write(obj.toString());
                    writer.flush();
                    writer.close();
                }
           }
        }
    }
}
