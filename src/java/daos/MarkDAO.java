/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Mark;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyConnection;

/**
 *
 * @author USER
 */
public class MarkDAO implements Serializable{
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
    public Mark getMark(int id) throws ClassNotFoundException, SQLException, Exception
    {
        Mark result = null;
        try {
            
            cnn = MyConnection.getConnection();
            String sql = "SELECT dateCreated,status,height,weight FROM Mark WHERE id = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if(rs.next())
            {
                Date d = rs.getDate("dateCreated");
                String status = rs.getString("status");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                result = new Mark(id, d, status, height, weight);
            }
        } finally {
            closeConnnection();
        }
        return result;
    }
    public List<Mark> getAllMark() throws SQLException, Exception
    {
        List<Mark> result = null;
        try 
        {
            cnn = MyConnection.getConnection();
            String sql = "SELECT id,dateCreated,status,height,weight FROM Mark";
            preStm = cnn.prepareStatement(sql);
            
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next())
            {
                int id = rs.getInt("id");
                Date day = rs.getDate("dateCreated");
                String status = rs.getString("status");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                result.add(new Mark(id, day, status, height, weight));
            }
        } finally {
            closeConnnection();
        }
        return  result;
    }
    public boolean insert(Mark u) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result=false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "INSERT INTO Mark(id,dateCreated,status,height,weight) "
                    + "values(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, u.getId());
            preStm.setDate(2, u.getDateCreated());
            preStm.setString(3, u.getStatus());
            preStm.setInt(4, u.getHeight());
            preStm.setInt(5, u.getWeight());
            
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean update(Mark u) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result=false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "UPDATE Mark SET dateCreated =?, status = ?, height = ?, weight =? WHERE id = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setDate(1, u.getDateCreated());
            preStm.setString(2, u.getStatus());
            
            preStm.setInt(3, u.getHeight());
            preStm.setInt(4, u.getWeight());
            preStm.setInt(5, u.getId());
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean update(int id,String status) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result=false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "UPDATE Mark SET status = ? WHERE id = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, status);
            preStm.setInt(2,id);
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public boolean delete(int id) throws ClassNotFoundException, SQLException, Exception
    {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "DELETE FROM Mark WHERE id = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            result = preStm.executeUpdate()>0;
        } finally {
            closeConnnection();
        }
        return result;
    }
    public static void main(String[] args) {
        MarkDAO dao = new MarkDAO();
        try {
            List<Mark> list = dao.getAllMark();
            for(Mark m: list)
                System.out.println(m.getId()+"," + m.getStatus());
        } catch (Exception ex) {
            Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
