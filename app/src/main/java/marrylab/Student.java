package marrylab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	public Student(String name, Double GPA){
		this.name = name;
		this.GPA = GPA;
	}

	/**
	 * 点数計算をするメソッド
	 */
	public Double calculateScore(HashMap<String, Double> coursePoint, HashMap<Integer, Double> labScore) {
		return this.GPA*12 + this.serchCorsePoint(coursePoint) + this.serchLabScore(labScore);
	}

	/**
	 * 当てはまるコース点を返すメソッド
	 */
	public Double serchCorsePoint(HashMap<String, Double> coursePoint) {
		//コースが合致して、その中で点数が一番高いコースを返す
		List<Double> alist = new ArrayList<Double>();
		for (String course : myCourse) {
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
	public Double serchLabScore(HashMap<Integer, Double> labScore) {
		return labScore.get(this.studentNumber);
	}

	/**
	 * 現在希望している研究室名を応答する。
	 * @return 研究室名
	 */
	public String getCurrentLabRank(){
		return this.labRank.get(currentIndex).name();
	}

	/**
	 * 現在の希望順位を応答する
	 * @return　現在の希望順位
	 */
	public int currentIndex(){
		return currentIndex;
	}

	/**
	 * 生徒自身を未配属に変更する。
	 */
	public void unAssign(){
		this.result = null;
		this.isAssigned = false;
		currentIndex++;
	}

	/**
	 * 配属済みに変更する。
	 * @return
	 */
	public void assign(){
		this.isAssigned = true;
	}

	/**
	 * 現在配属済かどうかを応答する。
	 * @return　配属済ならtrue
	 */
	public boolean isAssigned(){
		return this.isAssigned;
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

}
