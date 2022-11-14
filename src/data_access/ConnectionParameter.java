package data_access;

public class ConnectionParameter {
    public static final String username = "";
    public static final String password = "";
    
	// JDBC driver class name, database location and name, and connection string
	public static final String jdbcDriver = "org.sqlite.JDBC";
	public static final String databaseLocation = "databases/";
	public static final String databaseName = "studentlistjdbc.db";
	public static final String connectionString = "jdbc:sqlite:" + databaseName;

	// PK violation: The error code in SQLite is 19
	public static final int PK_VIOLATION_ERROR = 19;
}
