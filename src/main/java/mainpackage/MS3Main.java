package mainpackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement; 

public class MS3Main {

	public static void main(String[] args) {

		String csvFile = "ms3Interview.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		String dbName = "ms3.db";
		Connection conn = null;
		int numberOfRecords = 0;
		int numberOfSuccessfulRecords = 0;
		int numberOfFailedRecords = 0;

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/jamalnuman/Desktop/sqlite" + dbName);

			FileWriter myWriter = new FileWriter("ms3interview-bad.csv");
			FileWriter myLog = new FileWriter("ms3interview.log");

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();

			String sqlstatement = ("CREATE TABLE IF NOT EXISTS exampleTable (\n" + "firstName varchar(255), \n"
					+ "lastName varchar(255), \n" + "email varchar(150), \n" + "gender varchar(50), \n" + "miss1 TEXT, \n"
					+ "miss2 TEXT, \n" + "price TEXT, \n" + "bool1 TEXT, \n" + "bool2 TEXT, \n" + "city varchar(100) \n"
					+ ");");
			Statement stmt = conn.createStatement();
			stmt.execute(sqlstatement);
			int numColumns = line.split(csvSplitBy).length;
			while ((line = br.readLine()) != null) {
				numberOfRecords++;
				String[] values = line.split(csvSplitBy);
				if (values.length < numColumns) {
					myWriter.write(line + '\n');
					numberOfFailedRecords++;
				} else {
					String insertSQL = "INSERT INTO exampleTable (firstName, lastName, email, gender, miss1, "
							+ "miss2, price, bool1, bool2, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
					preparedStatement.setString(1, values[0]);
					preparedStatement.setString(2, values[1]);
					preparedStatement.setString(3, values[2]);
					preparedStatement.setString(4, values[3]);
					preparedStatement.setString(5, values[4]);
					preparedStatement.setString(6, values[5]);
					preparedStatement.setString(7, values[6]);
					preparedStatement.setString(8, values[7]);
					preparedStatement.setString(9, values[8]);
					preparedStatement.setString(10, values[9]);
					preparedStatement.executeUpdate();
					numberOfSuccessfulRecords++;
				}

			}
			myWriter.close();
			myLog.write("Number Of Records are " + numberOfRecords + '\n');
			myLog.write("Number Of Successful Records are " + numberOfSuccessfulRecords + '\n');
			myLog.write("Number Of Failed Records are " + numberOfFailedRecords + '\n');
			myLog.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					if (conn != null) {
						conn.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
