package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AptekDAO aptekDAO = new AptekDAO();
        //  System.out.println(aptekDAO.showStock("saglamol"));
        //   System.out.println(aptekDAO.calculateTotalPrice());
        aptekDAO.deleteExpiredMeds(2023,2,20);
        // System.out.println(aptekDAO.selectAll());

    }
}