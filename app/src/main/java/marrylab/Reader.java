package marrylab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reader extends IO {
    public Reader(Table table, String filePath) {
		super(table, filePath);
	}

	public void run() {
		
	}


	/**
	 * 学業成績.xlsxを読み込み、履修可能な生徒のみStudentのインスタンスを作成する。
	 * 生徒ID,氏名,特別研究１履修可否,通算GPA
	 * @param filePath ファイルパス
	 */
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

	/**
	 * コース選択.xlsxを読み込み、Studentのフィールドに情報を保存する。
	 * 生徒ID,氏名,第一希望,第二希望,第三希望
	 * @param filePath
	 */
	public void readStudentCourse(String filePath) {
		this.CSVtoList(filePath).forEach((column) -> {
			try {
				Integer studentID = Integer.parseInt(column[0]);
				this.table.studentMap().get(studentID).setCourse(column[2],column[3],column[4]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * コース点.csvを読み込み、コース点をLabratoryにそれぞれ保持させる。
	 * 研究室名,各コースの点数,...
	 * @param filePath
	 */
	public void readCoursePoint(String filePath) {
		this.CSVtoList(filePath).forEach((column) -> {
			try {
				String name = column[0];
				// コース点を整数型に変換し、リストに追加
				List<Integer> coursePoint = Arrays.stream(column)
				.skip(1)
				.map(point -> {
					return Integer.parseInt(point);
				})
				.collect(Collectors.toList());
				this.table.laboratoryMap().get(name).setCoursePoint(coursePoint);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Lab_Score.csvを読み込み、教員点をLaboratoryのインスタンスに保持させる。
	 * 生徒ID,氏名,教員点（0~24）
	 * 研究室ごとにファイルが存在する。
	 * @param filePath
	 */
	public void readLabScore(String labName, String filePath) {

	}

	/**
	 * 研究室配属希望調査.csvを読み込み、Studentに研究室希望順を保持させる。
	 * 生徒ID,氏名,回答の状態,開始日時,受験完了日時,所要時間,評点,回答
	 * 生徒によって複数回答ある場合があるので、受験完了日時が最新の情報を参照する。
	 * @param filePath
	 */
	public void readLabRank(String filePath) {

	}
	
}
