package marrylab;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
	private Map<String, Double> coursePoint;

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
	public Laboratory(){
		this.studentPoint = new HashMap<>();
		this.studentList = new ArrayList<>();
		this.coursePoint = new HashMap<>();
		this.labScore = new HashMap<>();
		this.capacity = 0;
		this.name = "";
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
	public List<Integer> removeStudent(Integer capacity){
		List<Integer> removedStudentsList = new ArrayList<Integer>();
		while (studentList[capacity] == null){
			removedStudentsList.add(studentList[capacity]);
			studentList.remove(capacity);
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
		Collections.sort(studentList);
	}

	/**
	 * 研究室名を応答する。
	 * @return 研究室名
	 */
	public String name(){
		return this.name;
	}
}