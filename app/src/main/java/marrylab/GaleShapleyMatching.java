package marrylab;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

import java.util.List;
import java.util.ArrayList;

/**
 * Gale-Shapleyのアルゴリズムによって、学生を研究室に配属させるクラスです。
 */
public class GaleShapleyMatching {
	/**
	 * 表を保持するフィールドです。
	 */
	private Table table;

	public GaleShapleyMatching(Table table) {
		this.table = table;
	}

	/**
	 * マッチングアルゴリズムを実行します。
	 */
	public void run() {
		this.table.calculateCapacity();
		while (!this.table.hasUnassignedStudents()) {
			this.add(this.table.laboratoryMap(), this.table.studentMap());
			this.remove(this.table.laboratoryMap(), this.table.studentMap());
		}

	}

	/**
	 * 希望研究室をこれ以上持たない学生に対しアルゴリズムの実行をスキップするフラグを立てるメソッドです。
	 */
	public void markNullStudent(){
		this.table.studentMap().forEach((ID, student) -> {
			student.nulLabRank();
		});
	}

	/**
	 * 学生を研究室に配属させるメソッドです。
	 * skipFlagが立っている生徒のアルゴリズムの実装はスキップします。
	 */
	public void add(Map<String, Laboratory> laboratoryMap, Map<Integer, Student> studentMap) {
		studentMap.values().stream()
		          .filter(student -> !student.nulLabRank())
				  .filter(student -> student.getCurrentLabRank() != null)
				  .forEach(student -> student.getCurrentLabRank().addStudent(student));
	}

	/**
	 * 学生を除名するメソッドです。
	 */
	public void remove(Map<String, Laboratory> laboratoryMap, Map<Integer, Student> studentMap) {
		laboratoryMap.forEach((key, laboratory) -> {
			laboratory.removeStudent();
		});
	}
}
