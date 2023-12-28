package marrylab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * csvファイルを読み込んで操作を行うクラスです。
 */
public class Reader extends IO {
	/**
	 * 教員点の情報をもつファイルへのパスを保持するMap
	 * キー：研究室名、バリュー：教員点の情報が書かれたファイルへのパス
	 */
	private Map<String, String> labScoreMap;

	/**
	 * コンストラクタ：初期値を設定しておく。
	 * @param table 研究室や学生のマップを保持しています。
	 */
	public Reader(Table table) {
		super(table);
		this.labScoreMap = new HashMap<>();
	}

	/**
	 * csvファイルを読み込んで操作を実行します。
	 */
	public void run() {
		this.readStudentGPA();
		this.readStudentCourse();
		this.readLabScoreMap();
		this.readCoursePoint();
		this.readLabScore();
		this.readLabRank();
	}

	/**
	 * 学業成績.csvを読み込み、履修可能な生徒のみStudentのインスタンスを作成する。
	 * 生徒ID,氏名,特別研究１履修可否,通算GPA
	 * 
	 * @param filePath csvのファイルパス
	 */
	public void readStudentGPA() {
		
		this.CSVtoList(this.getFilePass("学業成績.csvを読み込んでください。")).forEach((column) -> {
			try {
				// column配列の長さを確認
				if (column.length >= 4) {
					Integer studentID = Integer.valueOf(column[0]);
					String studentName = column[1];
					Double gpa = Double.valueOf(column[3]);

					if (Objects.equals("○", column[2])) {
						this.table.studentMap().put(studentID, new Student(studentID ,studentName, gpa));
					}
				}
			} catch (NumberFormatException e) {
				// 数値変換での例外をキャッチし、適切に処理
				e.printStackTrace(); // または他のエラー処理
			}
		});
	}

	/**
	 * コース選択.csvを読み込み、Studentのフィールドに情報を保存する。
	 * 生徒ID,氏名,第一希望,第二希望,第三希望
	 * 
	 * @param filePath csvのファイルパス
	 */
	public void readStudentCourse() {
		this.CSVtoList(this.getFilePass("コース選択.csvを読み込んでください。")).forEach((column) -> {
			try {
				Integer studentID = Integer.valueOf(column[0]);
				if (this.table.studentMap().get(studentID) != null) {
					this.table.studentMap().get(studentID).setCourse(column[2], column[3], column[4]);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 教員点ファイルパス.csvを読み込み、フィールドのlabScoreMapに保存する。
	 * 研究室名,LabSvcoreのファイルパス
	 * 
	 * @param filePath csvのファイルパス
	 */
	public void readLabScoreMap() {
		this.CSVtoList(this.getFilePass("教員点ファイルパス.csvを読み込んでください。")).forEach((column) -> {
			try {
				this.labScoreMap.put(column[0], column[1]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * コース点.csvを読み込み、コース点をLabratoryにそれぞれ保持させる。
	 * 研究室名,各コースの点数,...
	 * 
	 * @param filePath csvのファイルパス
	 */
	public void readCoursePoint() {
		this.CSVtoList(this.getFilePass("コース点.csvを読み込んでください。")).forEach((column) -> {
			try {
				String labName = column[0];
				// コース点を整数型に変換し、リストに追加
				List<Integer> coursePoint = Arrays.stream(column)
						.skip(1)
						.map(point -> {
							return Integer.valueOf(point);
						})
						.collect(Collectors.toList());
				if (this.table.laboratoryMap().get(labName) != null) {
					this.table.laboratoryMap().get(labName).setCoursePoint(coursePoint);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * labのインスタンスを作成し、Lab_Score.csvを研究室ごとに読み込む。
	 */
	public void readLabScore() {
		this.labScoreMap.forEach((labName, filePath) -> {
			// Labのインスタンスを作成
			this.table.laboratoryMap().put(labName, new Laboratory(labName));
			// 実際にファイルを読み込む
			this.readLabScore(labName, filePath);
		});
	}

	/**
	 * Lab_Score.csvを読み込み、教員点をLaboratoryのインスタンスに保持させる。
	 * 生徒ID,氏名,教員点（0~24）
	 * 研究室ごとにファイルが存在する。
	 * 
	 * @param labName 研究室名
	 * @param filePath csvのファイルパス
	 */
	public void readLabScore(String labName, String filePath) {
		this.CSVtoList(filePath).forEach((labScoreColumn) -> {
			try {
				Integer studentID = Integer.valueOf(labScoreColumn[0]);
				Double labScore = Double.valueOf(labScoreColumn[2]);
				if(this.table.studentMap().containsKey(studentID)){
					this.table.laboratoryMap().get(labName).setLabScore(studentID, labScore);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 研究室配属希望調査.csvを読み込み、Studentに研究室希望順を保持させる。
	 * 生徒ID,氏名,回答の状態,開始日時,受験完了日時,所要時間,評点,回答
	 * 生徒によって複数回答ある場合があるので、受験完了日時が最新の情報を参照する。
	 * 
	 * @param filePath csvのファイルパス
	 */
	public void readLabRank() {
		this.CSVtoList(this.getFilePass("研究室配属希望調査.csvを読み込んでください。")).forEach((column) -> {
			try {
				List<Laboratory> labRank = new ArrayList<Laboratory>();
				Integer studentID = Integer.valueOf(column[0]);
				String[] labNameList = column[7].replaceAll("^\\{|\\}$", "")
						.split("} \\{");
				for (String labName : labNameList) {
					if(labName != null){
						labRank.add(this.table.laboratoryMap().get(labName));
					}
				}
				if (this.table.studentMap().containsKey(studentID)) {
					this.table.studentMap().get(studentID).setLabRank(labRank);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});
	}
}
