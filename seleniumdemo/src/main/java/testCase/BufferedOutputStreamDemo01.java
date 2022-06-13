package testCase;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BufferedOutputStreamDemo01 {

    public static final int SIZE=1024;
    public static final String DRIVERNAME="oracle.jdbc.driver.OracleDriver";
    public static final String DBURL="jdbc:oracle:thin:@168.63.235.167:1521:orcl";
    //public static final String DBURL="jdbc:oracle:thin:@10.150.1.133:1525/gt2p1";
    public static final String USERNAME="gtosadmin";
    public static final String PASSWORD="gtosadmin";

    static{
        try{
            // 加载驱动程序
            Class.forName(DRIVERNAME);
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        // 变量声明
        File f=null;
        OutputStream output=null;
        BufferedOutputStream bos=null;
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        StringBuilder strBuild=null;

        try{
            String sql=" SELECT * FROM GMS_VEHICLE_READING WHERE PREGATE_ID LIKE '%XB7457A%' ";

            con=new BufferedOutputStreamDemo01().getConnection();
            // 获得数据库操作类
            pst=new BufferedOutputStreamDemo01().getPst(con,sql);
            // 获得结果集
            rs=pst.executeQuery();

            f=new File("D:"+File.separator+"tmp.txt");
            output=new FileOutputStream(f,false);
            bos=new BufferedOutputStream(output,SIZE*4);

            while(rs.next()){
                strBuild=new StringBuilder();
                strBuild.append(",");
                strBuild.append(rs.getString("seq_id"));
                strBuild.append(",");

                strBuild.append(rs.getString("pregate_id"));
                strBuild.append(",");

                strBuild.append(rs.getString("gate_lane_id"));
                strBuild.append(",");

                strBuild.append(rs.getString("direction_c"));
                strBuild.append(",");

                strBuild.append(rs.getString("vis_eqpt_id"));
                strBuild.append(",");

                strBuild.append(rs.getString("vehicle_n"));
                strBuild.append(",");

                strBuild.append(rs.getString("vis_eqpt_conf_level_n"));
                strBuild.append(",");

                strBuild.append(rs.getString("vis_img_path_m"));
                strBuild.append(",");

                strBuild.append(rs.getString("created_dt"));
                strBuild.append("\n");
                System.out.println(strBuild);
                bos.write(strBuild.toString().getBytes("utf-8"));
            }

        }catch(IOException ex1){
            ex1.printStackTrace();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                if(bos!=null){
                    bos.flush();
                    bos.close();
                }
                // 关闭流
                if(output!=null){
                    output.close();
                }

                //关闭数据库连接
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     **获得数据库连接
     **
     **/
    public static Connection getConnection(){
        Connection con=null;
        try{
            // 获得数据库连接
            con=DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return con;
    }

    /**
     **获得数据库操作类
     **/
    public static PreparedStatement getPst(Connection con,String sql){
        PreparedStatement pst=null;
        try{
            pst=con.prepareStatement(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return pst;
    }
}

