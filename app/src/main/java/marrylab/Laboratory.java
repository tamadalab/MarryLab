package marrylab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 研究室に関する情報を管理するクラス
 */
public class Laboratory {

	/**
	 * 学生の配属情報を保持するフィールド
	 */
	private List<Student> studentList;

	/**
	 * 研究室のコース点を保持するフィールド
	 */
	private Map<Integer, Double> coursePoint;

	/**
	 * 研究室名を保持するフィールド
	 */
	private String name;

	/**
	 * 学生ごとに付与される教員点を保持するフィールド
	 */
	private HashMap<Integer, Double> labScore;

	/**
	 * 研究室の最大配属人数を保持するフィールド
	 */
	private Integer capacity;

	/**
	 * 研究室のコンストラクタ
	 * 
	 * @param name 研究室名
	 */
	public Laboratory(String name) {
		this.capacity = 0;
		this.coursePoint = new HashMap<>();
		this.labScore = new HashMap<>();
		this.name = name;
		this.studentList = new ArrayList<>();
	}

	/**
	 * 生徒を配属リストに追加する
	 * 
	 * @param newStudent 生徒のインスタンス
	 */
	public void addStudent(Student newStudent) {
		studentList.add(newStudent);
	}

	/**
	 * 研究室のキャパシティを返すメソッドです。
	 * 
	 * @return 研究室のキャパシティの情報を返します。
	 */
	public int capacity() {
		int ReturnCapacity = this.capacity;
		return ReturnCapacity;
	}

	/**
	 * コース点を返すメソッドです。
	 * 
	 * @return コース点の情報を返します。
	 */
	public Map<Integer, Double> coursePoint() {
		Map<Integer, Double> ReturnCoursePoint = new HashMap<Integer, Double>();
		ReturnCoursePoint = this.coursePoint;
		return ReturnCoursePoint;
	}

	/**
	 * 教員点を返すメソッドです。
	 * 
	 * @return 教員点の情報を返します。
	 */
	public HashMap<Integer, Double> labScore() {
		HashMap<Integer, Double> ReturnLabScore = new HashMap<Integer, Double>();
		ReturnLabScore = this.labScore;
		return ReturnLabScore;
	}

	/**
	 * 研究室名を応答する。
	 * 
	 * @return 研究室名
	 */
	public String name() {
		String ReturnLabName = new String();
		ReturnLabName = this.name;
		return ReturnLabName;
	}

	/**
	 * 配属済の生徒を並べ替え、溢れた生徒を除名
	 * 除名した生徒が持つIDをまとめて応答する。
	 * 
	 * @return 除名された生徒のIDリスト
	 */
	public void removeStudent() {
		this.sortStudent();
		while (studentList.size() > this.capacity) {
			studentList.remove(studentList.size() - 1).unAssign();
		}
		// List<Integer> removedStudentsList = new ArrayList<Integer>();
		// while (studentList.size() > this.capacity) {
		// removedStudentsList.add(studentList.remove(studentList.size() -
		// 1).studentNumber());
		// }
		// return removedStudentsList;
	}

	/**
	 * コース点の設定を行う
	 * 
	 * @param coursePoint 各研究室のコース点を保持するリスト
	 */
	public void setCoursePoint(List<Integer> coursePoint) {
		for (int i = 0; i < coursePoint.size(); i++) {
			// インデックスを1オリジンに変換し、リストの要素をDoubleに変換してMapに格納
			this.coursePoint.put(i + 1, coursePoint.get(i).doubleValue());
			// コース選択なし：９９ を0点に設定
			this.coursePoint.put(99, 0.0);
		}
	}

	/**
	 * 教員点の設定を行う
	 * 
	 * @param studentID 生徒ID
	 * @param labScore  教員点
	 */
	public void setLabScore(Integer studentID, Double labScore) {
		this.labScore.put(studentID, labScore);
	}

	/**
	 * 生徒の希望順位を総合点をもとに並べ替える
	 */
	public void sortStudent() {
		studentList.sort((s1, s2) -> {
			double score1 = s1.calculateScore(this.coursePoint, this.labScore);
			double score2 = s2.calculateScore(this.coursePoint, this.labScore);
			return Double.compare(score2, score1); // 高いスコア順にソート
		});
	}

	/**
	 * 学生の配属情報を返すメソッドです。
	 * 
	 * @return 学生の配属情報を返します。
	 */
	public List<Student> studentList() {
		List<Student> ReturnStudentList = new ArrayList<Student>();
		ReturnStudentList = this.studentList;
		return ReturnStudentList;
	}

	/**
	 * 研究室の最大配属人数を変更するメソッド
	 */
	public void updateCapacity(Integer capacity) {
		this.capacity += capacity;
	}
}
