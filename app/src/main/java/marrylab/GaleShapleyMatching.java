package marrylab;

import java.util.HashMap;
import java.util.Map;
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


	public GaleShapleyMatching(Table table){
		this.table = table;
	}

	/**
	 * マッチングアルゴリズムを実行します。
	 */
	public void run(){

		while (true) {
			this.add(this.table.laboratoryMap(), this.table.studentMap());
			this.remove(this.table.laboratoryMap(), this.table.studentMap());
			if(this.table.hasUnassignedStudents()) { break; }
		}
	}

	/**
	 * 学生を研究室に配属させるメソッドです。
	 */
	public void add(Map<String, Laboratory> laboratoryMap, Map<Integer, Student> studentMap) {
		studentMap.forEach((key, student) -> {
			laboratoryMap.get(student.getCurrentLabRank()).addStudent(student);
		});
	}

	/**
	 * 学生を除名するメソッドです。
	 */
	public void remove(Map<String, Laboratory> laboratoryMap, Map<Integer, Student> studentMap) {
		laboratoryMap.forEach((key, laboratory) -> {
			laboratory.removeStudent().forEach((studentID) -> {
				studentMap.get(studentID).unAssign();
			});
		});
	}
}
