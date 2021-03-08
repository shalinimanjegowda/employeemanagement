package com.postgresql;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMain {

	Connection getPostGresConnectionInstance() throws SQLException, ClassNotFoundException;
}
