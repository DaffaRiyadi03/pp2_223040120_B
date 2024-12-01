/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.membershiporm8;

/**
 *
 * @author mdaff
 */

import dao.JenisMemberDao;
import dao.MemberDao;
import view.main.MainFrame;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MembershipORM8 {

     public static void main(String[] args) throws IOException {
        // Menentukan lokasi konfigurasi MyBatis
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        
        // Membangun SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
        // Membuat objek DAO
        JenisMemberDao jenisMemberDao = new JenisMemberDao(sqlSessionFactory);
        MemberDao memberDao = new MemberDao(sqlSessionFactory);
        
        // Membuat dan menampilkan MainFrame
        MainFrame mainFrame = new MainFrame(jenisMemberDao, memberDao);
        
        // Menampilkan GUI pada thread utama
        javax.swing.SwingUtilities.invokeLater(() -> {
            mainFrame.setVisible(true);
        });
    }
}
