package Main.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rows {

	public int GetRowCount(ResultSet rs) {
		int count = 0;

		try {
			while (rs.next()) {
			    ++count;
			}
		} catch (SQLException e) {
			return 0;
		}
		
		return count;
	}
}
