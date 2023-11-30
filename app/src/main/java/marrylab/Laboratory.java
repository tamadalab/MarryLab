package marrylab;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 研究室に関する情報を管理するクラス
 */
public class Laboratory {

	/**
	 * 生徒の総合点を管理するマップを保持するフィールド。
	 * キー：生徒ID バリュー：総合点
	 */
	private Map<Integer, Double> studentPoint;

	/**
	 * 生徒の希望順位を保持するフィールド
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
	 */
	public Laboratory(String name){
		this.studentPoint = new HashMap<>();
		this.studentList = new ArrayList<>();
		this.coursePoint = new HashMap<>();
		this.labScore = new HashMap<>();
		this.capacity = 0;
		this.name = name;
	}

	/**
	 * 生徒を配属リストに追加する
	 * @param newStudent　生徒のインスタンス
	 */
	public void addStudent(Student newStudent){
		studentList.add(newStudent);
	}

	/**
	 * 配属済の生徒を並べ替え、溢れた生徒を除名
	 * 除名した生徒が持つIDをまとめて応答する。
	 * @return 除名された生徒のIDリスト
	 */
	public List<Integer> removeStudent(){
		this.sortStudent();
		List<Integer> removedStudentsList = new ArrayList<Integer>();
		while (studentList.size() > this.capacity) {
            removedStudentsList.add(studentList.remove(studentList.size() - 1).studentNumber());
        }
		return removedStudentsList;
	}

	/**
	 * 研究室の最大配属人数を変更するメソッド
	 */
	public void updateCapacity(Integer capacity) {
		this.capacity += capacity;
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
		//Collections.sort(studentList);
	}

	/**
	 * 研究室名を応答する。
	 * @return 研究室名
	 */
	public String name(){
		return this.name;
	}

	public Map<Integer, Double> studentPoint(){
		return this.studentPoint;
	}

	public List<Student> studentList(){
		return this.studentList;
	}

	public Map<Integer, Double> coursePoint(){
		return this.coursePoint;
	}

	public HashMap<Integer, Double> labScore(){
		return this.labScore;
	}

	/**
	 * コース点の設定を行う
	 * @param coursePoint
	 */
	public void setCoursePoint(List<Integer> coursePoint){
		for (int i = 0; i < coursePoint.size(); i++) {
			// インデックスを1オリジンに変換し、リストの要素をDoubleに変換してMapに格納
			this.coursePoint.put(i + 1, coursePoint.get(i).doubleValue());
		}
	}
	
	/**
	 * 教員点の設定を行う
	 * @param studentID 生徒ID
	 * @param labScore 教員点
	 */
	public void setLabScore(Integer studentID, Double labScore) {

	}
}
