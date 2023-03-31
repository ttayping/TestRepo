import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployerDao employerDao = new EmployerDao();
//        employerDao.updateSalary(1,3,300);
//        employerDao.updateSalary(3,5,600);
//        employerDao.updateSalary(5,100,1000);
  //      System.out.println(employerDao.showEmployeesBySalary(2000));
        System.out.println(employerDao.underFromAverageSalary());
    }
}