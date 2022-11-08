package io.github.nathancorghi.mysql.conexao;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexaoMySql {

    public DataSource getDataSource() {

        String url = "jdbc:mysql://localhost:3306/banco_teste_automacao";
        String username = "root";
        String password = "admin";

        try {
            DriverManager.getConnection(url, username,password);

            final MysqlDataSource dataSource = new MysqlDataSource();

            dataSource.setURL(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);

            return dataSource;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
