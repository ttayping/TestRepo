import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerDao {
    private static final String SELECT_EMPLOYER = "select * from employerDB";
    private static final String SELECT_EMPLOYER_BY_ID = "select * from employer e where e.id = ?";
    private static final String ADD_EMPLOYER =
            "INSERT INTO employerDB(name, surname, experience, salary) VALUES(?,?,?,?)";
    private static final String UPDATE_SALARY_BY_ID = "UPDATE employerdb set salary=? where id=?";
    private static final String SELECT_EMPLOYEES_BY_EXPERIENCE_RANGE =
            "SELECT * from employerdb where experience BETWEEN ? and ?";
    private static final String SELECT_EMPLOYEES_OVER_SALARY = "SELECT * from employerdb where salary>?";
    private static final String SELECT_EMPLOYEES_UNDER_SALARY = "SELECT * from employerdb where salary<?";

    public List<Employer> underFromAverageSalary() throws SQLException {
        List<Employer> employerList = this.selectAll();
        Double totalSalary= 0.0;
        int count=0;
        for (Employer employer : employerList) {
            totalSalary+=employer.getSalary();
            count++;
        }
        Double averageSalary = totalSalary/count;
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_UNDER_SALARY);
        preparedStatement.setDouble(1,averageSalary);
        ResultSet resultSet = preparedStatement.executeQuery();
        List <Employer> underAverageEmployeesList=new ArrayList<>();
        while (resultSet.next()) {
            Employer employer = new Employer();
            employer.setId(resultSet.getInt(1));
            employer.setName(resultSet.getString(2));
            employer.setSurname(resultSet.getString(3));
            employer.setExperience(resultSet.getInt(4));
            employer.setSalary(resultSet.getDouble(5));
            underAverageEmployeesList.add(employer);
        }
    return underAverageEmployeesList;}


    public List<Employer> showEmployeesBySalary(int salary) throws SQLException {
        List<Employer> employerList = new ArrayList<>();
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_OVER_SALARY);
        preparedStatement.setInt(1, salary);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employer = new Employer();
            employer.setId(resultSet.getInt(1));
            employer.setName(resultSet.getString(2));
            employer.setSurname(resultSet.getString(3));
            employer.setExperience(resultSet.getInt(4));
            employer.setSalary(resultSet.getDouble(5));
            employerList.add(employer);
        }
        ConnectionUtil.closeConnection(connection, preparedStatement, resultSet);
        return employerList;
    }


    public void updateSalary(int experienceFrom, int experienceTill, double salary) throws SQLException {

        List<Employer> employerList = new ArrayList<>();
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_BY_EXPERIENCE_RANGE);
        preparedStatement.setInt(1, experienceFrom);
        preparedStatement.setInt(2, experienceTill);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employer = new Employer();
            employer.setId(resultSet.getInt(1));
            employer.setName(resultSet.getString(2));
            employer.setSurname(resultSet.getString(3));
            employer.setExperience(resultSet.getInt(4));
            employer.setSalary(resultSet.getDouble(5));
            employerList.add(employer);
        }
        PreparedStatement updatePreparedStatement = connection.prepareStatement(UPDATE_SALARY_BY_ID);
        for (Employer employer : employerList) {
            updatePreparedStatement.setDouble(1, employer.getSalary() + salary);
            updatePreparedStatement.setInt(2, employer.getId());
            updatePreparedStatement.executeUpdate();
        }
        ConnectionUtil.closeConnection(connection, preparedStatement, updatePreparedStatement);

    }

    public List<Employer> selectAll() throws SQLException {
        List<Employer> employerList = new ArrayList<>();
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYER);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employer = new Employer();
            employer.setId(resultSet.getInt(1));
            employer.setName(resultSet.getString(2));
            employer.setSurname(resultSet.getString(3));
            employer.setExperience(resultSet.getInt(4));
            employer.setSalary(resultSet.getDouble(5));
            employerList.add(employer);
        }
        ConnectionUtil.closeConnection(connection, preparedStatement, resultSet);
        return employerList;
    }

    public void addEmployer(Employer employer) throws SQLException {
        Connection connection = ConnectionUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYER);
        preparedStatement.setString(1, employer.getName());
        preparedStatement.setString(2, employer.getSurname());
        preparedStatement.setInt(3, employer.getExperience());
        preparedStatement.setDouble(4, employer.getSalary());
        preparedStatement.executeUpdate();
        ConnectionUtil.closeConnection(connection, preparedStatement);
    }
}
