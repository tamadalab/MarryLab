package marrylab;

import java.util.HashMap;
import java.util.Map;

import marrylab.Student;
import marrylab.Laboratory;
/**
 * 研究室と生徒の情報を管理するクラス
 */
public class Table {

	/**
	 * 研究室の集合を保持するフィールド
	 */
	private Map<String, Laboratory> laboratoryList;

	/**
	 * 生徒の集合を保持するフィールド
	 */
	private Map<Integer, Student> studentsList;

	/**
	 * コンストラクタ
	 */
	public void table(){
		Map<String, Laboratory> laboratoryList = new HashMap<String, Laboratory>();
		Map<Integer, Student> studentsList = new HashMap<Integer, Student>();
	}
}
