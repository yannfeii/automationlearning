import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

public class FtpUtils {
    /**
     * 连接ftp服务器
     * @param ip  ftp地址
     * @param port  端口
     * @param username 账号
     * @param password 密码
     * @return
     * @throws IOException
     */
    public static FTPClient ftpConnection(String ip, String port, String username, String password){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, Integer.parseInt(port));
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                System.out.println("--ftp连接失败--");
                System.exit(1);
            }
            ftpClient.enterLocalPassiveMode();//这句最好加告诉对面服务器开一个端口
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    /**
     * 断开FTP服务器连接
     * @param ftpClient  初始化的对象
     * @throws IOException
     */
    public static void close(FTPClient ftpClient) throws IOException {
        if(ftpClient!=null && ftpClient.isConnected()){
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * 下载ftp服务器文件方法
     * @param ftpClient FTPClient对象
     * @param newFileName 新文件名
     * @param fileName 原文件（路径＋文件名）
     * @param downUrl  下载路径
     * @return
     * @throws IOException
     */
    public static boolean downFile(FTPClient ftpClient, String newFileName, String fileName, String downUrl) throws IOException {
        boolean isTrue = false;
        OutputStream os=null;
        File localFile = new File(downUrl + "/" + newFileName);
        if (!localFile.getParentFile().exists()){//文件夹目录不存在创建目录
            localFile.getParentFile().mkdirs();
            localFile.createNewFile();
        }
        os = new FileOutputStream(localFile);
        isTrue = ftpClient.retrieveFile(new String(fileName.getBytes(),"UTF-8"), os);
        os.close();
        return isTrue;
    }
    @Test
    public void GetEngineLogFromNode3(){
        FTPClient ftpClient = ftpConnection("10.150.1.14","22","tibcoadm","Passw0rd1");
        boolean flag = false;
        try {
            flag = downFile(ftpClient,"greplog.sh","/home/tibcoadm/yanfei/greplog.sh","D:\\WorkFiles\\02-PSA\\04-FunctionTest\\MNL_ARIV\\20211001");
            close(ftpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(flag);//flag=true说明下载成功
    }

    public static void main(String[] args){
        FTPClient ftpClient = ftpConnection("10.150.1.7","22","tibcoadm","Passw0rd1");
        boolean flag = false;
        try {
            flag = downFile(ftpClient,"default-engine-for-com.psa.gtosplus.ops.gms.sb.gtosplus_ops_gms_sb.log",
                    "/opt/psa/data/gtosplus_ops/f2_1/gms/sb/C.s_gtosplus_ops_gms_sb_app_f2_1/logs/default-engine-for-com.psa.gtosplus.ops.gms.sb.gtosplus_ops_gms_sb.log",
                    "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\MNL_ARIV\\20211001");
            close(ftpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(flag);//flag=true说明下载成功
    }

}
