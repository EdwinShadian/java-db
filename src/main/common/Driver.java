package main.common;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.github.cdimascio.dotenv.Dotenv.configure;

public class Driver {
    public Connection connection;
    private Dotenv config;

    public Driver(){
        this.loadConfig();
        String dbURL = "jdbc:mysql://";
        dbURL += this.config.get("DB_HOST")
                + ":" + this.config.get("DB_PORT")
                + "/" + this.config.get("DB_DATABASE");
        try {
            this.connection = DriverManager.getConnection(
                    dbURL,
                    this.config.get("DB_USER"),
                    this.config.get("DB_PASSWORD")
            );
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    private void loadConfig(){
        this.config = configure().load();
    }
}
