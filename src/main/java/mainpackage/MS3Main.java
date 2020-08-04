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

public class MS3Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			
			
			FileWriter myWriter = new FileWriter("ms3interview-bad.csv");
			FileWriter myLog = new FileWriter("ms3interview.log");

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			PreparedStatement stmt = conn
					.prepareStatement("insert into exampleTable (" + line + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			int numColumns = line.split(csvSplitBy).length;
			// System.out.println(numColumns);
			while ((line = br.readLine()) != null) {
				numberOfRecords++;
				String[] values = line.split(csvSplitBy);
				if (values.length < numColumns) {
					myWriter.write(line + '\n');
					numberOfFailedRecords++;
				} else {
					numberOfSuccessfulRecords ++;
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
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
