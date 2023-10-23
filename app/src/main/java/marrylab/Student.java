/**
 * 生徒に関する情報を記憶。
 */

package marryLab;

import java.lang.String;
import java.util.List;

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
	 * @return 学生証番号
	 */
	public Integer getStudentNumber() {
		return this.studentNumber;
	}

	/**
	 * GPAを応答する
	 * @return GPA
	 */
	public Integer getGPA() {
		return this.GPA;

	}

	/**
	 * 選択コースを応答する
	 * @return 選択コース
	 */
	public List<String> getMyCourse() {
		return this.myCourse;
	}

	/**
	 * 研究室希望順位を応答する
	 * @return 研究室希望順位
	 */
	public List<Laboratory> labPreferences() {
		return this.assignedLab;
	}

}
