package main.common;

import main.exceptions.ModelNotFound;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Model {
    protected static Driver driver;

    static {
        driver = new Driver();
    }

    public static ArrayList<HashMap<String, Object>> all(String table, List<String> properties) {
        ArrayList<HashMap<String, Object>> collectionAll = new ArrayList<>();
        try (Statement statement = Model.driver.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from " + table);
            while (resultSet.next()) {
                HashMap<String, Object> row = new HashMap<>();
                for (String property : properties) {
                    row.put(property, resultSet.getObject(property));
                }
                collectionAll.add(row);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return collectionAll;
    }

    public static HashMap<String, Object> findOrFail(Integer id, String table, List<String> properties) throws ModelNotFound {
        HashMap<String, Object> model = new HashMap<>();

        try (Statement statement = Model.driver.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from "
                    + table
                    + " where id="
                    + id);

            resultSet.next();
            for (String property : properties) {
                model.put(property, resultSet.getObject(property));
            }

            if (model.size() == 0) {
                throw new ModelNotFound("Model not found!");
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

        return model;
    }

    public static ArrayList<HashMap<String, Object>> executeQuery(String queryString, List<String> properties) {
        try (Statement statement = Model.driver.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queryString);
            ArrayList<HashMap<String, Object>> result = new ArrayList<>();
            while (resultSet.next()) {
                HashMap<String, Object> row = new HashMap<>();
                for (String property : properties) {
                    row.put(property, resultSet.getObject(property));
                }
                result.add(row);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String select(String table, String[] columns) {
        StringBuilder builder = new StringBuilder("select ");
        if (columns[0].equals("*")) {
            builder.append("* ");
        } else {
            for (String column : columns) {
                builder.append(column).append(", ");
            }
            builder.delete(builder.length() - 2, builder.length() - 1);
        }

        builder.append("from ").append(table).append(" ");

        return builder.toString();
    }

    public static String where(String column, String value) {
        return "where " + column + "=" + "'" + value + "'";
    }

    public static String where(String operator, String column, String value) {
        return operator + " " + column + "=" + "'" + value + "'";
    }

    public static void create(HashMap<String, Object> items, String query, List<String> properties) {
        try (PreparedStatement statement = Model.driver.connection.prepareStatement(query)) {
            int position = 1;
            for (String property:properties) {
                if (items.containsKey(property)) {
                    statement.setObject(position, items.get(property));
                } else {
                    statement.setNull(position, 0);
                }
                position++;
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String insert(String table, List<String> properties) {
        StringBuilder query = new StringBuilder("insert into " + table + " (");
        for (String property : properties) {
            query.append(property).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(") values (");
        query.append("?, ".repeat(properties.size()));
        query.delete(query.length() - 2, query.length());
        query.append(")");

        return query.toString();
    }

    public static void bind(HashMap<String, Object> bindings, String commonTable) {
        List<String> columns = bindings.keySet().stream().toList();
        String query = Model.insert(commonTable, columns);
        Model.create(bindings, query, columns);
    }

    public static void destroy(Integer id, String table) {
        StringBuilder query = new StringBuilder("delete from ");
        query.append(table).append(" where id=").append(id);

        try(Statement statement = Model.driver.connection.createStatement()) {
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int unbind(Integer id, String column, String commonTable) {
        StringBuilder query = new StringBuilder("delete from ");
        query.append(commonTable)
                .append(" where ")
                .append(column)
                .append("=")
                .append(id);

        try(Statement statement = Model.driver.connection.createStatement()) {
            return statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
