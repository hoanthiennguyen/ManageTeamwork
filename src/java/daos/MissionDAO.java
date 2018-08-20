/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Mission;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class MissionDAO implements Serializable {

    private Connection cnn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    private void closeConnnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException e) {

        }
    }

    public Mission getMission(String missionName) throws ClassNotFoundException, SQLException, Exception {
        Mission result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT place,fromDate,toDate,status FROM Mission"
                    + " WHERE missionName = ? ";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, missionName);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String place = rs.getString("place");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String status = rs.getString("status");

                result = new Mission(missionName, place, status, fromDate, toDate);
            }
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean delete(String missionName) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "DELETE FROM Mission WHERE missionName = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, missionName);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean insert(Mission u) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "INSERT INTO Mission(missionName,place,fromDate,toDate,status) "
                    + "values(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, u.getMissionName());
            preStm.setString(2, u.getPlace());
            preStm.setDate(3, u.getFromDate());
            preStm.setDate(4, u.getToDate());
            preStm.setString(5, u.getStatus());

            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnnection();
        }
        return result;
    }

    public List<Mission> getAllMissions() throws Exception {
        List<Mission> result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT missionName,place,fromDate,toDate,status "
                    + " FROM Mission ORDER BY toDate DESC";
            preStm = cnn.prepareStatement(sql);

            rs = preStm.executeQuery();
            result = new ArrayList<>();
            Date current = new Date(System.currentTimeMillis());
            while (rs.next()) {
                String missionName = rs.getString("missionName");
                String place = rs.getString("place");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String status = rs.getString("status");
                if (status.equals("To do") && current.compareTo(fromDate) >= 0) {
                    autoUpdateStatus(missionName, "Doing");
                    status = "Doing";
                }
                if (current.compareTo(toDate) >= 0 && (status.equals("To do") || status.equals("Doing"))) {
                    autoUpdateStatus(missionName, "Failed");
                    status = "Failed";
                }
                result.add(new Mission(missionName, place, status, fromDate, toDate));
            }
        } finally {
            closeConnnection();
        }
        return result;
    }

    public List<Mission> searchMissions(String search) throws Exception {
        List<Mission> result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT missionName,place,fromDate,toDate,status "
                    + " FROM Mission WHERE missionName LIKE ?"
                    + " ORDER BY toDate DESC";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            Date current = new Date(System.currentTimeMillis());
            while (rs.next()) {
                String missionName = rs.getString("missionName");
                String place = rs.getString("place");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String status = rs.getString("status");
                if (status.equals("To do") && current.compareTo(fromDate) >= 0) {
                    autoUpdateStatus(missionName, "Doing");
                    status = "Doing";
                }
                if (current.compareTo(toDate) >= 0 && (status.equals("To do") || status.equals("Doing"))) {
                    autoUpdateStatus(missionName, "Failed");
                    status = "Failed";
                }
                result.add(new Mission(missionName, place, status, fromDate, toDate));
            }
        } finally {
            closeConnnection();
        }
        return result;
    }

    public void autoUpdateStatus(String missionName, String status) throws Exception {
        //Do not close connection
        String sql = "UPDATE Mission SET status = ? WHERE missionName = ?";
        preStm = cnn.prepareStatement(sql);
        preStm.setString(1, status);
        preStm.setString(2, missionName);
        preStm.executeUpdate();

    }

    public List<Mission> getAllMissions(String heroName) throws Exception {
        List<Mission> result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT missionName,place,fromDate,toDate,status\n"
                    + "FROM Mission\n"
                    + "WHERE missionName in(\n"
                    + "SELECT missionName\n"
                    + "FROM MissionDetails\n"
                    + "WHERE heroName = ?) "
                    + "ORDER BY toDate DESC";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, heroName);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String missionName = rs.getString("missionName");
                String place = rs.getString("place");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String status = rs.getString("status");
                Date current = new Date(System.currentTimeMillis());
                if (status.equals("To do") && current.compareTo(fromDate) >= 0) {
                    autoUpdateStatus(missionName, "Doing");
                    status = "Doing";
                }
                if (current.compareTo(toDate) >= 0 && (status.equals("To do") || status.equals("Doing"))) {
                    autoUpdateStatus(missionName, "Failed");
                    status = "Failed";
                }
                result.add(new Mission(missionName, place, status, fromDate, toDate));
            }
        } finally {
            closeConnnection();
        }
        return result;
    }

    public List<Mission> searchMissions(String heroName, String search) throws Exception {
        List<Mission> result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT missionName,place,fromDate,toDate,status\n"
                    + "FROM Mission\n"
                    + "WHERE missionName in(\n"
                    + "SELECT missionName\n"
                    + "FROM MissionDetails\n"
                    + "WHERE heroName = ? AND missionName LIKE ?) "
                    + "ORDER BY toDate DESC";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, heroName);
            preStm.setString(2, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String missionName = rs.getString("missionName");
                String place = rs.getString("place");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String status = rs.getString("status");
                Date current = new Date(System.currentTimeMillis());
                if (status.equals("To do") && current.compareTo(fromDate) >= 0) {
                    autoUpdateStatus(missionName, "Doing");
                    status = "Doing";
                }
                if (current.compareTo(toDate) >= 0 && (status.equals("To do") || status.equals("Doing"))) {
                    autoUpdateStatus(missionName, "Failed");
                    status = "Failed";
                }
                result.add(new Mission(missionName, place, status, fromDate, toDate));
            }
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean update(Mission u) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "UPDATE Mission SET place =?, fromDate = ?, toDate =?, status = ?"
                    + " WHERE missionName = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, u.getPlace());
            preStm.setDate(2, u.getFromDate());
            preStm.setDate(3, u.getToDate());
            preStm.setString(4, u.getStatus());
            preStm.setString(5, u.getMissionName());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnnection();
        }
        return result;
    }
}
