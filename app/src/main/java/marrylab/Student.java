package marrylab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 学生の情報を管理するクラス
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
	 * GaleSharpleyアルゴリズムをスキップするためのフラグ
	 */
	private boolean skipFlag;

	/**
	 * コンストラクタ
	 */
	public Student(Integer ID, String name, Double GPA) {
		this.studentNumber = ID;
		this.name = name;
		this.GPA = GPA;
		this.myCourse = new ArrayList<Integer>();
		this.labRank = new ArrayList<Laboratory>();
		this.currentIndex = 1;
		this.isAssigned = false;
		this.skipFlag = false;
	}

	/**
	 * 配属済みに変更する。
	 * 
	 * @return
	 */
	public void assign(Laboratory newLaboratory) {
		this.result = newLaboratory;
		this.isAssigned = true;
	}

	/**
	 * 点数計算をするメソッド
	 */
	public Double calculateScore(Map<Integer, Double> coursePoint, Map<Integer, Double> labScore) {
		return this.GPA * 12 + this.searchCoursePoint(coursePoint) + this.searchLabScore(labScore);
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
	 * 第一希望の研究室を応答する
	 * 
	 * @return 第一希望の研究室
	 */
	public Laboratory firstLabRank() {
		if (this.labRank.isEmpty()) {
			return null;
		}
		return this.labRank.get(0);
	}

	/**
	 * 現在希望している研究室名を応答する。
	 * 
	 * @return 研究室名
	 */
	public Laboratory getCurrentLabRank() {
		return this.labRank.get(currentIndex - 1);
	}

	/**
	 * アルゴリズム実行されないことを応答する。
	 * 
	 * @return スキップフラグ
	 */
	public boolean getSkipFlag() {
		return this.skipFlag;
	}

	/**
	 * GPAの点数を応答する。
	 * 
	 * @return GPAの点数
	 */
	public Double GPA() {
		return this.GPA;
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
	 * 研究室の希望順位を応答する。
	 * 
	 * @return 研究室の希望順位
	 */
	public List<Laboratory> labRank() {
		return this.labRank;
	}

	/**
	 * 学生のコースを応答する。
	 * 
	 * @return コース
	 */
	public List<Integer> myCourse() {
		return this.myCourse;
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
	 * 研究室希望順位に関しての例外処理を行う
	 * 
	 * @return スキップフラグ
	 */
	public boolean nulLabRank() {
		// リストが空の場合、またはcurrentIndexがリストの範囲外の場合
		if (labRank.isEmpty() || currentIndex < 0 || currentIndex >= labRank.size()) {
			skipFlag = true;
		} else {
			// currentIndexが範囲内の場合、指定されたインデックスの要素がnullかどうかをチェック
			skipFlag = (labRank.get(currentIndex) == null);
		}
		return skipFlag;
	}

	/**
	 * 学生の決まった研究室を応答する。
	 * 
	 * @return
	 */
	public Laboratory result() {
		return this.result;
	}

	/**
	 * 配属された研究室を応答する
	 * 
	 * @return 配属された研究室
	 */
	public Laboratory resultLaboratory() {
		return this.result;
	}

	/**
	 * 当てはまるコース点を返すメソッド
	 * 
	 * @return コース点の最高得点
	 */
	public Double searchCoursePoint(Map<Integer, Double> coursePoint) {
		// コースが合致して、その中で点数が一番高いコースを返す
		Double highestPoint = 0.0;
		for (Integer course : this.myCourse) {
			Double value = coursePoint.get(course);
			if (value != null && value > highestPoint) {
				highestPoint = value;
			}
		}
		return highestPoint;
	}

	/**
	 * 当てはまる教員点を返すメソッド
	 * 
	 * @return 教員点
	 */
	public Double searchLabScore(Map<Integer, Double> labScore) {
		if (labScore.containsKey(studentNumber)) {
			return labScore.get(this.studentNumber);
		}
		return 0.0;
	}

	/**
	 * 学生の３つのコースを設定する。
	 * 
	 * @param course1 コース１
	 * @param course2 コース２
	 * @param course3 コース３
	 */
	public void setCourse(String course1, String course2, String course3) {
		this.myCourse.add(Integer.valueOf(course1.split(":")[0], 10));
		this.myCourse.add(Integer.valueOf(course2.split(":")[0]));
		this.myCourse.add(Integer.valueOf(course3.split(":")[0]));
	}

	/**
	 * 研究室の希望順位を設定する。
	 * 
	 * @param labRank 研究室の希望順位
	 */
	public void setLabRank(List<Laboratory> labRank) {
		labRank.removeIf(Objects::isNull);
		this.labRank = labRank;
	}

	/**
	 * 学生のIDを応答する。
	 * 
	 * @return 学生ID
	 */
	public Integer studentNumber() {
		return this.studentNumber;
	}

	/**
	 * 生徒自身を未配属に変更する。
	 */
	public void unAssign() {
		this.result = null;
		this.isAssigned = false;
		currentIndex++;
	}
}
