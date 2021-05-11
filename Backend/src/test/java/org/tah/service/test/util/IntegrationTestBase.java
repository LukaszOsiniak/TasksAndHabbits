package org.tah.service.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tah.model.TaskStatusEnum;
import org.tah.persistence.DbConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public abstract class IntegrationTestBase {

    private static final Logger LOG = LogManager.getLogger(IntegrationTestBase.class);

    public void runSqlDataloadScript(String sqlFileName) throws SQLException {
        DbConnection dbConnection = new DbConnection();

        try (
                Connection connection = dbConnection.getConnection();
                Statement statement = connection.createStatement();
        ) {
            InputStream inputStream = this.getClass().getResourceAsStream(sqlFileName);

            String sqlScriptContent = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            LOG.info(sqlScriptContent);

            statement.executeUpdate(sqlScriptContent);
            TaskStatusEnum e = TaskStatusEnum.DEFINED;
            TaskStatusEnum.valueOf("DEFINED");
            TaskStatusEnum.values();
        }
    }
}
