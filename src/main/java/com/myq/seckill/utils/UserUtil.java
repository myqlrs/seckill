package com.myq.seckill.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myq.seckill.entity.User;
import com.myq.seckill.vo.RespBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author 孟赟强
 * @date 2021/4/19-22:51
 */
public class UserUtil {
    /**
     * 创建用户
     * @param count
     * @throws Exception
     */
    private static List<User> createUser(int count) throws Exception{
        List<User> users = new ArrayList<User>(count);
        //生成用户
        for(int i=0;i<count;i++) {
            User user = new User();
            user.setId(18855130000L+i);
            user.setNickname("user"+i);
            user.setSalt("19971203");
            user.setPassword(MD5Util.inputPassToDbPass("123456",user.getSalt()));
            user.setLoginCount(1);
            user.setRegisterDate(new Date());
            users.add(user);
        }
        System.out.println("create user");
        return users;
    }

    //插入数据库
    public static void insertDB(List<User> users)  throws Exception{
        Connection conn = DBUtil.getConn();
        String sql = "insert into t_user(login_count,nickname,register_date,salt,password,id)values(?,?,?,?,?," +
                "?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            pstmt.setInt(1, user.getLoginCount());
            pstmt.setString(2, user.getNickname());
            pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
            pstmt.setString(4, user.getSalt());
            pstmt.setString(5, user.getPassword());
            pstmt.setLong(6, user.getId());

            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.close();
        conn.close();
        System.out.println("insert to db");
    }

    /**
     * 生成userTicket同步到文件，编译JMeter压测
     * @param users
     * @throws Exception
     */
    public static void createToken(List<User> users)  throws Exception{
        String urlString = "http://localhost:8080/login/dologin";
        File file = new File("D:/userTicket.txt");
        if(file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection)url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassToFormPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0 ,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            ObjectMapper mapper = new ObjectMapper();
            RespBean respBean = mapper.readValue(response,RespBean.class);
            String userTicket = (String) respBean.getObj();
            System.out.println("create userTicket : " + user.getId());

            String row = user.getId()+","+userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("token create over");
    }


    public static void main(String[] args)throws Exception {
        List<User> userList = createUser(5000);

        //插入数据库
        insertDB(userList);

        //登录，生成userTicket
        createToken(userList);

    }
}
