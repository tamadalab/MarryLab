package marrylab;

import java.util.List;

/**
 * 
 */
public class Student {

	/**
	 * 学生のIDを保持するフィールド
	 */
	private Integer studentNumber;

	/**
	 * 学生のGPAを保持するフィールド
	 */
	private Double GPA;

	/**
	 * 学生の選択コースを保持するフィールド
	 */
	private List<String> myCourse;

	/**
	 * 学生の研究室希望順位を保持するフィールド
	 */
	private List<Laboratory> labRank;

	/**
	 * 学生の決まった研究室を保持するフィールド
	 */
	private Laboratory result;

	/**
	 * 学生の氏名を保持するフィールド
	 */
	private String name;

	/**
	 * 点数計算をするメソッド
	 */
	public Double calculateScore() {
		return null;
	}

	/**
	 * 当てはまる教員点を返すメソッド
	 */
	public Double serchCorsePoint() {
		return null;
	}

	/**
	 * 当てはまるコースを返すメソッド
	 */
	public Double serchLabScore() {
		return null;
	}

}
