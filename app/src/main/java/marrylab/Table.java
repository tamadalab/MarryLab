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

	
	/**
	 * 研究室の情報を追加する
	 * @param key　研究室名
	 * @param aLaboratory Labのインスタンス
	 */
	public void addLaboratory(String key, Laboratory aLaboratory){
		this.laboratoryList.put(key, aLaboratory);
	}


	/**
	 * 生徒の情報を追加する
	 * @param key　生徒ID
	 * @param student 生徒のインスタンス
	 */
	public void addStudent(Integer key, Student student) {
		this.studentsList.put(key, student);
	}

	/**
	 * 研究室の最大配属人数を計算する
	 */
	public void calculateCapacity(){
		for (Laboratory lab : this.laboratoryList.values()) {
			lab.updateCapacity(this.getQuotientOfStudentsAndLabs());
		}
	
		for (int i = 0; i < this.getRemainderOfStudentsAndLabs(); i++) {
			String labName = this.firstLabRank.get(i);
			Laboratory lab = this.laboratoryList.get(labName);
			lab.updateCapacity(1);
		}
	}

	/**
	 * 生徒の数を研究室数で割った商を応答
	 * @return　商
	 */
	public Integer getQuotientOfStudentsAndLabs() {
		return this.studentsList.size() / this.laboratoryList.size();
	}
	
	/**
	 * 生徒の数を研究室数で割った余りを応答
	 * @return 余り
	 */
	public int getRemainderOfStudentsAndLabs() {
		return this.studentsList.size() % this.laboratoryList.size();
	}

	/**
	 * 生徒を研究室に配属させる
	 */
	public void add(){
		studentsList.forEach((key, student) -> {
			
			laboratoryList.get(student.getCurrentLabRank()).addStudent(student);
		});
	}

	/**
	 * 研究室に所属されている、溢れた生徒を除名させる。
	 */
	public void remove(){
		laboratoryList.forEach((key, laboratory) -> {
			// 研究室クラスのremoveを実行→消された生徒の情報を未配属にする
		});
	}
}