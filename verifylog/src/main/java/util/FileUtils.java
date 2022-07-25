package util;

import ch.ethz.ssh2.*;
import com.mysql.jdbc.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class FileUtils {

    Logger logger = Logger.getLogger(FileUtils.class.getName());

    /*
     * 根据http路径下载文件保存到指定路径
     * urlString：文件路径
     * fileName：保存到本地的文件名称
     * filePath：本地要保存的指定路径
     * */
    public boolean downloadFile(String urlString, String fileName, String filePath) {
        boolean bool = false;

        InputStream is = null;
        FileOutputStream os = null;
        try {
            logger.info("文件路径：" + urlString);
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            //判断指定目录是否存在，不存在则先创建目录
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();
            //fileName如果不包含文件后缀，则需要加上后缀，如：fileName + ".jpg";fileName + ".txt";
            os = new FileOutputStream(filePath + fileName, false);//false：覆盖文件,true:在原有文件后追加
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

            bool = true;
           logger.info("文件保存成功：" + fileName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 完毕，关闭所有链接
            if (null != os){
                try {
                    os.flush();
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bool;
    }

    //远程下载服务器文件
    public boolean copyFile(String ip,int port,String userName,String password,String sourceFile,String targetFile,String targetFileName){
        boolean bool = false;
        Connection conn = null;
        Session session = null;
        try {
            if (StringUtils.isNullOrEmpty(ip) || StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password) ||
                    StringUtils.isNullOrEmpty(sourceFile) || StringUtils.isNullOrEmpty(targetFile)){
                return bool;
            }
            conn = new Connection(ip,port);
            conn.connect();
            boolean isAuth = conn.authenticateWithPassword(userName,password);
            if (!isAuth){
                logger.info("算法主机连接失败");
                return bool;
            }
            //执行命令
            session = conn.openSession();

            //执行命令并打印执行结果
            session.execCommand("df -h");
            InputStream staout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(staout));
            String line = null;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();

            //下载文件到本地
            SCPClient scpClient = conn.createSCPClient();
            SCPInputStream scpis = scpClient.get(sourceFile);

            //判断指定目录是否存在，不存在则先创建目录
            File file = new File(targetFile);
            if (!file.exists())
                file.mkdirs();

            FileOutputStream fos = new FileOutputStream(targetFile + targetFileName);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = scpis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            fos.close();
            bool = true;
            //SFTP
            /*SFTPv3Client sftPClient = new SFTPv3Client(conn);
            sftPClient.createFile("");
            sftPClient.close();*/
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            logger.info("保存失败：" + sourceFile);
        }finally {
            if (null != session){
                session.close();
            }
            if (null != conn) {
                conn.close();
            }
        }

        return bool;
    }


}