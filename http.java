package classes;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.JDBCUTILS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class http {
    public static String getURLContent (String urlStr){

        //请求的url
        URL url = null;

        //建立的http链接
        HttpURLConnection httpConn = null;

        //请求的输入流
        BufferedReader in = null;

        //输入流的缓冲
        StringBuffer sb = new StringBuffer();

        try {
            url = new URL(urlStr);

            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String str = null;

            //一行一行进行读入
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ex) {

        } finally {
            try {
                if (in != null) {
                    in.close(); //关闭流
                }
            } catch (IOException ex) {

            }
        }
        String result = sb.toString();
        return result;
    }



    public static <data> void main(String[] args) {


        String strJson = getURLContent("http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey=") ;
        try {


            JSONObject jsonObject = JSONObject.fromObject(strJson);
            String data = jsonObject.getString("returnData");
            System.out.println(data);
            JSONArray jsonArray = new JSONArray();
            jsonArray=jsonArray.fromObject(data);
            for (int j = 0; j <jsonArray.size() ; j++) {
                JSONObject item = jsonArray.getJSONObject(j); //每条记录又由几个Object对象组成
                int xh = item.getInt("xh");     			  // 获取对象对应的值
                String xm = item.getString("xm");
                String xb = item.getString("xb");
                System.out.println("Json解析结果"+"学号 = " + xh + "; 姓名 = " + xm + "; 性别 ="+ xb);
                int i=0;
                Connection conn = JDBCUTILS.getConnection();
                String sql = "insert into students (stuId,xh,xm) values(?,?,?)";
                PreparedStatement pstmt;
                try {
                    pstmt = (PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setObject(1,xh);
                    pstmt.setObject(2,xm);
                    pstmt.setObject(3,xb);
                    i = pstmt.executeUpdate();
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }}


        } catch (Exception e) {

        }


    }
}
