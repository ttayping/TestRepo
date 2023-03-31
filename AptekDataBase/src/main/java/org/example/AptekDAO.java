package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AptekDAO {
    private static final String SELECT_APTEK_DB = "select * from aptek";
    private static final String SHOW_STOCK_BY_MED = "SELECT stock from aptek where name=?";
    private static final String DELETE_ELEMENT_BY_ID = "DELETE from aptek where id=?";

    public void deleteElement(int id) throws SQLException {
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ELEMENT_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        ConnectionUtil.closeConnection(connection, preparedStatement);
    }

    public void deleteExpiredMeds() throws SQLException {
        List<Medicine> medicineList = this.selectAll();
        LocalDate date = LocalDate.now();

        for (Medicine med : medicineList) {
            if (med.getLocalDate().isBefore(date)) {
                deleteElement(med.getId());
            }
        }
    }

    //--------------------------------------------------
//        for (Medicine med : medicineList) {
//            if (med.getExpireDate().getYear() < year) {
//                deleteElement(med.getId());
//                if (med.getExpireDate().getMonth() < month) {
//                    deleteElement(med.getId());
//                    if (med.getExpireDate().getDay() < day) {
//                        deleteElement(med.getId());
//                    }
//                }
//            }
//        }
//    }

    public int calculateTotalPrice() throws SQLException {
        List<Medicine> medicineList = this.selectAll();
        int totalPrice = 0;
        for (Medicine med : medicineList) {
            totalPrice += (med.getPrice() * med.getStock());
        }
        return totalPrice;
    }

    public int showStock(String nameMed) throws SQLException {
        int stock = 0;
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_STOCK_BY_MED);
        preparedStatement.setString(1, nameMed);
        ResultSet resultSet = preparedStatement.executeQuery();
        Medicine medicine = new Medicine();
        while (resultSet.next()) {
            medicine.setStock(resultSet.getInt(1));
        }
        ConnectionUtil.closeConnection(connection, preparedStatement, resultSet);
        return medicine.getStock();
    }

    public List<Medicine> selectAll() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APTEK_DB);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Medicine medicine = new Medicine();
            medicine.setId(resultSet.getInt(1));
            medicine.setName(resultSet.getString(2));
            medicine.setStock(resultSet.getInt(3));
            medicine.setPrice(resultSet.getDouble(4));
            medicine.setLocalDate(resultSet.getDate(5).toLocalDate());
            medicineList.add(medicine);
        }
        ConnectionUtil.closeConnection(connection, preparedStatement, resultSet);
        return medicineList;
    }
}
