package marrylab;

import java.util.List;

public class Reader extends IO {
    public Reader(Table table, String filePass) {
		super(table, filePass);
	}


	public void readStudentGPA(String filePath) {
		this.CSVtoList(filePath).forEach((column) -> {
			try {
				// column配列の長さを確認
				if (column.length >= 4) {
					Integer studentID = Integer.parseInt(column[0]);
					String studentName = column[1];
					Double gpa = Double.parseDouble(column[3]);
	
					if ("○".equals(column[2])) {
						this.table.studentMap().put(studentID, new Student(studentName, gpa));
					}
				}
			} catch (NumberFormatException e) {
				// 数値変換での例外をキャッチし、適切に処理
				e.printStackTrace(); // または他のエラー処理
			}
		});
	}
	
}
