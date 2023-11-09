package marrylab;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import marrylab.Student;
/**
 * 研究室に関する情報を管理するクラス
 */
public class Laboratory {

	/**
	 * 生徒の総合点を管理するマップを保持するフィールド
	 */
	private HashMap<Integer, Double> studentPoint;

	/**
	 * 生徒の希望順位を保持するフィールド
	 */
	private List<Student> studentList;

	/**
	 * 研究室のコース点を保持するフィールド
	 */
	private HashMap<String, Integer> coursePoint;

	/**
	 * 研究室名を保持するフィールド
	 */
	private String name;

	/**
	 * 学生ごとに付与される教員展を保持するフィールド
	 */
	private HashMap<Integer, Double> labScore;

	/**
	 * 研究室の最大配属人数を保持するフィールド
	 */
	private Integer capacity;

	private Student student;

	/**
	 * 研究室のコンストラクタ
	 */
	public void Laboratory(){

	}

	/**
	 * 研究室の最大配属人数を変更するメソッド
	 */
	public void updateCapacity() {

	}

	/**
	 * 生徒の希望順位を総合点をもとに並べ替える
	 */
	public void sortStudent() {

	}
}