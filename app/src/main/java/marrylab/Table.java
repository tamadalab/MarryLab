package marrylab;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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
	 * 研究室第一希望のみを持つリスト
	 */
	private List<String> firstLabRank;

	/**
	 * コンストラクタ
	 */
	public Table(){
		this.laboratoryList = new HashMap<String, Laboratory>();
		this.studentsList = new HashMap<Integer, Student>();
		this.firstLabRank = new ArrayList<String>();
	}

	
	public void addLaboratory(String key, Laboratory aLaboratory){
		this.laboratoryList.put(key, aLaboratory);
	}

	public void addStudent(Integer key, Student student) {
		this.studentsList.put(key, student);
	}

	/**
	 * 研究室の最大配属人数を計算する
	 */
	public void calculateCapacity(){
		
	}

	/**
	 * 生徒の数を研究室数で割った商を応答
	 * @return　商
	 */
	public int getQuotientOfStudentsAndLabs() {
		return this.studentsList.size() / this.laboratoryList.size();
	}
	
	/**
	 * 生徒の数を研究室数で割った余りを応答
	 * @return 余り
	 */
	public int getRemainderOfStudentsAndLabs() {
		return this.studentsList.size() % this.laboratoryList.size();
	}
}