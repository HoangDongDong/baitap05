package model;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private final String serverName = "LAPTOP-6KP13OSJ";
    private final String dbName = "LTWebThu6";
    private final String portNumber = "1433";
    private final String instance = "";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName;
        if (instance != null && !instance.trim().isEmpty()) {
            url += "\\" + instance;
        }
        url += ":" + portNumber 
             + ";databaseName=" + dbName 
             + ";integratedSecurity=true;" 
             + "encrypt=true;trustServerCertificate=true";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }



    public static void main(String[] args) {
        try {
            System.out.println(new DBConnection().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
