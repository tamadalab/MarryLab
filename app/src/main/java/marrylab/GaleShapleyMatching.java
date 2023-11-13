package marrylab;

import marrylab.Table;

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

		return;
	}

	/**
	 * 学生を研究室に配属させるメソッドです。
	 */

	public void add() {

	}

	/**
	 * 溢れた学生をリストから削除するメソッドです。
	 */
	public void remove() {

	}

	/**
	 * 配属されていない学生がいるかをチェックするメソッド。
	 * 
	 * @return 未配属がいるかの真偽
	 */
	public boolean hasUnassignedStudents() {
		return false;
	}

	

}
