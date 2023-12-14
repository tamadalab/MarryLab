package marrylab;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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
		// 余りのぶんだけ人気の研究室順でcapacityを＋１する。
		this.storeFirstLabRank().stream()
            .limit(this.getRemainderOfStudentsAndLabs())
            .forEach(labname -> {
                this.laboratoryList.get(labname).updateCapacity(1);
            });
	}

	/**
	 * 第一希望が多い順に研究室を並べ替えたリストを応答する
	 * @return ソートされた研究室名のリスト
	 */
	public List<String> storeFirstLabRank(){
		Map<String, Integer> labCounts = new HashMap<>();
		this.studentsList.forEach((ID, student) -> {
			if(student.firstLabRank() != null){
				labCounts.merge(student.firstLabRank().name(), 1, Integer::sum);
			}
		});
		List<String> sortedLabs = labCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
		return sortedLabs;
	}

	/**
	 * 生徒の数を研究室数で割った商を応答
	 * @return 商
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
	 * 生徒のマップを応答する
	 * @return 生徒情報
	 */
	public Map<Integer, Student> studentMap(){
		return this.studentsList;
	}

	/**
	 * 研究室のマップを応答する
	 * @return 研究室情報
	 */
	public Map<String, Laboratory> laboratoryMap(){
		return this.laboratoryList;
	}
	
	/**
	 * 生徒全員が配属済かどうかを応答する。
	 * 配属済ならば、trueを返す
	 * @return 真偽値
	 */
	public boolean hasUnassignedStudents(){
		return this.studentsList.values().stream()
								.filter(student -> !student.getSkipFlag()) // skipFlagがfalseの生徒だけを対象にする
								.anyMatch(student -> !student.isAssigned()); // 未配属の生徒がいればtrueを返す
	}
}
