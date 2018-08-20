/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Hero;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.MyConnection;

/**
 *
 * @author USER
 */
public class HeroDAO implements Serializable{
    private Connection cnn =null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;
    private void closeConnnection()
    {
        try {
            if(rs != null) rs.close();
            if(preStm != null) preStm.close();
            if(cnn != null) cnn.close();
        } catch (SQLException e) {
           
        }
    }
    public String login(String username, String password) throws ClassNotFoundException, SQLException, Exception   
    {
        String result = "failed";
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT role FROM Hero WHERE username = ? AND password = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()) 
                result = rs.getString("role");
        } finally {
            closeConnnection();
        }  
        
        return result;
    }
    public Hero getHero(String username) throws ClassNotFoundException, SQLException, Exception
    {
        Hero result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT password,fullname,email,role FROM Hero WHERE username = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if(rs.next())
            {
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String role = rs.getString("role");
                result = new Hero(username, password, fullname, email, role);
            }
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean delete(String username) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "DELETE FROM Hero WHERE username = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, username);
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean update(Hero u) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result=false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "UPDATE Hero SET fullname =?, email = ?, role = ? WHERE username = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, u.getFullname());
            preStm.setString(2, u.getEmail());
            
            preStm.setString(3, u.getRole());
            preStm.setString(4, u.getUsername());
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean changePassword(String username, String password) throws SQLException, Exception
    {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "UPDATE Hero SET password =? WHERE username = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, password);
            preStm.setString(2, username);
            
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean insert(Hero u) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result=false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "INSERT INTO Hero(username,password,fullname,email,role) "
                    + "values(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, u.getUsername());
            preStm.setString(2, u.getPassword());
            preStm.setString(3, u.getFullname());
            preStm.setString(4, u.getEmail());
            preStm.setString(5, u.getRole());
            
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public List<Hero> getAllHeros() throws Exception
    {
        List<Hero> result = null;
        try 
        {
            cnn = MyConnection.getConnection();
            String sql = "SELECT username,password,fullname,email,role "
                    + " FROM Hero";
            preStm = cnn.prepareStatement(sql);
            
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next())
            {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String role = rs.getString("role");
                result.add(new Hero(username, password, fullname, email, role));
            }
        } finally {
            closeConnnection();
        }
        return result;
    }
    public List<String> listAllHeroName() throws SQLException, Exception
    {
        List<String> result = null;
        try 
        {
            cnn = MyConnection.getConnection();
            String sql = "SELECT username "
                    + " FROM Hero WHERE username <> ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "jarvis");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next())
            {
                String username = rs.getString("username");
                
                result.add(username);
            }
        } finally {
            closeConnnection();
        }
        return result;
    }
}
