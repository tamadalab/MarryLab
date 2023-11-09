package marrylab;

import java.util.HashMap;
import java.util.Map;

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
	public Table(){
		this.laboratoryList = new HashMap<String, Laboratory>();
		this.studentsList = new HashMap<Integer, Student>();
	}

	
	public void addLaboratory(String key, Laboratory aLaboratory){
		this.laboratoryList.put(key, aLaboratory);
	}

	public void addStudent(Integer key, Student student) {
		this.studentsList.put(key, student);
	}

}