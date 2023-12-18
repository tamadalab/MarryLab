package marrylab;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Gale-Shapleyのアルゴリズムによって、学生を研究室に配属させるクラスです。
 */
public class GaleShapleyMatching {
	/**
	 * 表を保持するフィールドです。
	 */
	private Table table;

	/**
	 * コンストラクタ：初期値を設定しておく
	 * @param table
	 */
	public GaleShapleyMatching(Table table) {
		this.table = table;
		// 研究室ごとの配属上限を決定する
		this.table.calculateCapacity();
	}

	/**
	 * マッチングアルゴリズムを実行します。
	 */
	public void run() {
		while (this.table.hasUnassignedStudents()) {
			this.add(this.table.laboratoryMap(), this.table.studentMap());
			this.remove(this.table.laboratoryMap(), this.table.studentMap());
		}
		this.addUnmatchedStudents(this.table);
	}

	/**
	 * 学生を研究室に配属させるメソッドです。
	 * skipFlagが立っている生徒のアルゴリズムの実装はスキップします。
	 * @param laboratoryMap 研究室に関するマップです。
	 * @param studentMap 学生に関するマップです。
	 */
	public void add(Map<String, Laboratory> laboratoryMap, Map<Integer, Student> studentMap) {
		studentMap.values().stream()
				  .filter(student -> !student.nulLabRank())
				  .filter(student -> student.getCurrentLabRank() != null)
				  .filter(student -> !student.isAssigned())
				  .forEach(student -> {
					Laboratory aLaboratory = student.getCurrentLabRank();
					aLaboratory.addStudent(student);
					student.assign(aLaboratory);
				});
	}

	/**
	 * GaleSharpleyアルゴリズムを実行できなかった生徒をランダムに配属する
	 * @param table 研究室と学生のマップ情報を保持しています。
	 */
	public void addUnmatchedStudents(Table table){
		// 未配属の生徒をリストに追加
		List<Student> unmatchedStudents = table.studentMap().values().stream()
		.filter(Student::nulLabRank)
		.collect(Collectors.toList());
		
		// 未配属の生徒を可能な限り研究室に配属
		for (Laboratory lab : table.laboratoryMap().values()) {
			// 研究室のキャパシティに余裕がある限り、生徒を配属
			while (lab.studentList().size() < lab.capacity() && !unmatchedStudents.isEmpty()) {
				Student unmatchedStudent = unmatchedStudents.remove(0); // 生徒をリストから削除
				unmatchedStudent.assign(lab); // 生徒に研究室を割り当て
				lab.addStudent(unmatchedStudent); // 研究室に生徒を追加
			}
		}
	}

	/**
	 * 学生を除名するメソッドです。
	 * @param laboratoryMap 研究室に関するマップです。
	 * @param studentMap 学生に関するマップです。
	 */
	public void remove(Map<String, Laboratory> laboratoryMap, Map<Integer, Student> studentMap) {
		laboratoryMap.forEach((key, laboratory) -> { laboratory.removeStudent(); });
	}
}
