package marrylab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import java.util.List;

/**
 * 学生の情報を管理するクラス
 */
public class Student implements Comparable<Student> {

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
	private List<Integer> myCourse;

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
	 * 配属願いを出す番号を保持する。
	 */
	private int currentIndex;

	/**
	 * 自身が配属済かどうかを示す。
	 */
	private boolean isAssigned;

	/**
	 * コンストラクタ
	 */
	public Student(String name, Double GPA) {
		this.name = name;
		this.GPA = GPA;
		this.myCourse = new ArrayList<Integer>();
		this.labRank = new ArrayList<Laboratory>();
		this.currentIndex = 0;
	}

	/**
	 * 点数計算をするメソッド
	 */
	public Double calculateScore(Map<Integer, Double> coursePoint, Map<Integer, Double> labScore) {
		return this.GPA * 12 + this.serchCorsePoint(coursePoint) + this.serchLabScore(labScore);
	}

	/**
	 * 当てはまるコース点を返すメソッド
	 */
	public Double serchCorsePoint(Map<Integer, Double> coursePoint) {
		// コースが合致して、その中で点数が一番高いコースを返す
		List<Double> alist = new ArrayList<Double>();
		for (Integer course : myCourse) {
			Double value = coursePoint.get(course);
			if (value != null) {
				alist.add(value);
			}
		}

		Collections.sort(alist, Collections.reverseOrder());

		return alist.isEmpty() ? null : alist.get(0);
	}

	/**
	 * 当てはまる教員点を返すメソッド
	 */
	public Double serchLabScore(Map<Integer, Double> labScore) {
		return labScore.get(this.studentNumber);
	}

	/**
	 * 現在希望している研究室名を応答する。
	 * 
	 * @return 研究室名
	 */
	public String getCurrentLabRank() {
		if (!Objects.equals(this.labRank.get(currentIndex), null)) {
			return this.labRank.get(currentIndex).name();
		}
		return null;
	}

	/**
	 * 現在の希望順位を応答する
	 * 
	 * @return 現在の希望順位
	 */
	public int currentIndex() {
		return currentIndex;
	}

	/**
	 * 生徒自身を未配属に変更する。
	 */
	public void unAssign() {
		this.result = null;
		this.isAssigned = false;
		currentIndex++;
	}

	/**
	 * 配属済みに変更する。
	 * 
	 * @return
	 */
	public void assign() {
		this.isAssigned = true;
	}

	/**
	 * 現在配属済かどうかを応答する。
	 * 
	 * @return 配属済ならtrue
	 */
	public boolean isAssigned() {
		return this.isAssigned;
	}

	/**
	 * 生徒の名前を応答する
	 * 
	 * @return 生徒の名前
	 */
	public String name() {
		return this.name;
	}

	/**
	 * 配属された研究室を応答する
	 * 
	 * @return 配属研究室
	 */
	public Laboratory resultLaboratory() {
		return this.result;
	}

	/**
	 * 第一希望の研究室を応答する
	 * 
	 * @return 第一希望の研究室
	 */
	public Laboratory firstLabRank() {
		return this.labRank.get(0);
	}

	@Override
	public int compareTo(Student other) {
		// オブジェクトを比較し、比較結果を返すロジックを実装
		if (this.GPA < other.GPA) {
			return -1;
		} else if (this.GPA > other.GPA) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer studentNumber() {
		return this.studentNumber;
	}

	public Double GPA() {
		return this.GPA;
	}

	public List<Integer> myCourse() {
		return this.myCourse;
	}

	public List<Laboratory> labRank() {
		return this.labRank;
	}

	public Laboratory result() {
		return this.result;
	}

	public void setCourse(String course1, String course2, String course3) {
		this.myCourse.add(Integer.valueOf(course1.split(":")[0], 10));
		this.myCourse.add(Integer.parseInt(course2.split(":")[0]));
		this.myCourse.add(Integer.parseInt(course3.split(":")[0]));
	}

	public void setLabRank(List<Laboratory> labRank) {
		labRank.removeIf(Objects::isNull);
		this.labRank = labRank;
	}
}
