/**
 * 研究室に関する情報を記憶。ソート、学生の点数計算を行う
 */
package marrylab;

public class Laboratory {

	/**
	 * 点数計算、ソートを施した学生の順位を持つリスト
	 */
	private List<Student> studentRank;

	/**
	 * マッチング後の配属された生徒の情報を持つリスト
	 */
	private List<Student> result;

	/**
	 * コース名をキー、コース点をバリューにもつ辞書
	 */
	private HashMap<String, Integer> coursePoint;

	/**
	 * 研究室名保持するフィールド
	 */
	private String labName;

	/**
	 * 研究室名を応答する
	 * @return 研究室名
	 */
	public String getLabName() {
		return null;
	}

	/**
	 * コース点を応答する
	 * @param コース名
	 * @return コース点
	 */
	public Integer getCoursIntegerePoint(String aCourceName) {
		return null;
	}

	/**
	 * マッチング後の研究室に配属された生徒の情報を応答する
	 */
	public void getResult() {

	}

	/**
	 * 研究室が持つ生徒の希望順位を応答する
	 */
	public void getStudentRank() {

	}

	/**
	 * 研究室名を設定する
	 */
	public void setLabName() {

	}

	/**
	 * 配属結果を設定する
	 */
	public void setResult() {

	}

	/**
	 * 生徒ごとの点数（GPA*12 + コース点 + 教員点）を計算する
	 */
	public void calculateScoreRank() {

	}

	/**
	 * 生徒を点数で並べ替えする
	 */
	public void sortStudentRank() {

	}

}