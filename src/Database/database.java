package Database;

import java.net.http.HttpResponse;
import java.sql.*;

public class database {

    private Connection connection;

    public database() {
        connect();

        // Import Data ke database
//        execute(
//                "INSERT INTO user " + "(id, username, email, password) VALUES  (?, ?, ?, ?);",
//                3, "kevin", "kevin@gmail.com", "kevin123"
//                );

        // Get data from database
//        try (Result res = getResult("SELECT * FROM user WHERE username = ?;", "kevin")){
//            ResultSet set = res.getResultSet();
//            while (set.next()){
//                System.out.println(set.getString("email"));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        // Delete data from database
//        execute(
//                "DELETE FROM user WHERE id = ?;", 3);

        // Update data from database
//        execute(
//                "UPDATE user SET password = ? WHERE username = ?;", "paswordku", "hanel"
//        );

    }


    public Result getResult(String sql, Object ...args){
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i+1, args[i]);
            }

            return new Result(statement, statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean execute(String sql, Object ...args){
        try (PreparedStatement statement = connection.prepareStatement(sql)){

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i+1, args[i]);
            }

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void connect(){
        // Alamat url ke database SQL Xampp
        String url = "jdbc:mysql://localhost:3306/final_project";
        try {
            // Hubungkan ke koneksi SQL Xmpp
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Berhasil dikoneksikan");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal terkoneksi");
        }
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        new database();
    }

}
