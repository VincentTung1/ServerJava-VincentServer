package attr;

import javax.servlet.http.HttpServlet;

/**
 * Created by vincent_tung on 2017/10/12.
 */
public class FileSaveUtil {

    // 上传文件存储目录
    public static final String FILE_UPLOAD_DIRECTORY = "upload";


    /**
     * 获取上传文件目录路径
     * @return
     */
    public static String getUploadSavePath(HttpServlet servlet){
        return servlet.getServletContext().getRealPath("./") + FILE_UPLOAD_DIRECTORY;
    }
}
