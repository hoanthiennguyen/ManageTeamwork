/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.MissionDetail;
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
public class MissionDetailDAO implements Serializable {

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

    public List<MissionDetail> getAllMissionDetails(String missionName) throws Exception {
        List<MissionDetail> result = null;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT heroName,subtask,status "
                    + "FROM missionDetails WHERE missionName = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, missionName);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String subtask = rs.getString("subtask");
                String status = rs.getString("status");
                result.add(new MissionDetail(heroName, missionName, subtask, status));
            }
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean insertWholeMissionDetail(String missionName, List<String> members, List<String> subtasks) throws SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "INSERT INTO MissionDetails(missionName,heroName,subtask) "
                    + "VALUES(?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, missionName);
            for(int i = 0; i <members.size(); i++)
            {
                preStm.setString(2, members.get(i));
                preStm.setString(3, subtasks.get(i));
                preStm.executeUpdate();
            }
            result = true;
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean update(MissionDetail u) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "UPDATE MissionDetails SET status =? "
                    + " WHERE missionName = ? AND heroName = ? AND subtask = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, u.getStatus());
            preStm.setString(2, u.getMissionName());
            preStm.setString(3, u.getHeroName());
            preStm.setString(4, u.getSubtask());

            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean insert(MissionDetail u) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "INSERT INTO MissionDetails(missionName,heroName,subtask,status) "
                    + "VALUES(?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, u.getMissionName());
            preStm.setString(2, u.getHeroName());
            preStm.setString(3, u.getSubtask());
            preStm.setString(4, u.getStatus());

            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean delete(String missionName, String heroName, String subtask) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "DELETE FROM MissionDetails WHERE missionName = ? AND heroName= ? AND subtask = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, missionName);
            preStm.setString(2, heroName);
            preStm.setString(3, subtask);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnnection();
        }
        return result;
    }

    public boolean isDedicated(String missionName, String heroName, String subtask) throws ClassNotFoundException, SQLException, Exception {
        boolean result = false;
        try {
            cnn = MyConnection.getConnection();
            String sql = "SELECT subtask FROM MissionDetails WHERE missionName = ? AND heroName= ? AND subtask = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, missionName);
            preStm.setString(2, heroName);
            preStm.setString(3, subtask);
            rs = preStm.executeQuery();
            result = rs.next();
        } finally {
            closeConnnection();
        }
        return result;
    }
}
