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
	 * 点数計算をするメソッド
	 */
	public Double calculateScore(HashMap<String, Double> coursePoint, HashMap<Integer, Double> labScore) {
		return this.GPA*12 + this.serchCorsePoint(coursePoint) + this.serchLabScore(labScore);
	}

	/**
	 * 当てはまる教員点を返すメソッド
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
	 * 当てはまるコースを返すメソッド
	 */
	public Double serchLabScore(HashMap<Integer, Double> labScore) {
		return null;
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
