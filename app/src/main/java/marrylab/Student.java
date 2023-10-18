/**
 * 生徒に関する情報を記憶。
 */
public class Student {

	/**
	 * 学生証番号を保持するフィールド
	 */
	private Integer studentNumber;

	/**
	 * GPAを保持するフィールド
	 */
	private Integer GPA;

	/**
	 * 生徒が選択したコース（最大３個）を保持するフィールド
	 */
	private List<String> myCourse;

	/**
	 * 研究室の希望順位のリストを保持するフィールド
	 */
	private List<Laboratory> labPreferences;

	/**
	 * 配属された研究室を保持するフィールド
	 */
	private Laboratory assignedLab;

	/**
	 * 学生証番号を応答する
	 * @return 
	 */
	public Integer getStudentNumber() {
		return null;
	}

	/**
	 * GPAを応答する
	 * @return
	 */
	public Integer getGPA() {
		return null;

	}

	/**
	 * 選択コースを応答する
	 */
	public void getMyCourse() {

	}

	/**
	 * 研究室希望順位を応答する
	 * @return
	 */
	public List<Laboratory> labPreferences() {
		return null;
	}

}
