package marrylab;

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
			this.add();
			this.remove();
			if(this.hasUnassignedStudents()) { break; }
		}
	}

	/**
	 * 学生を研究室に配属させるメソッドです。
	 */
	public void add() {
		this.table.add();
	}


	/**
	 * 学生を除名するメソッドです。
	 */
	public void remove() {
		
	}


	/**
	 * 未配属の学生がいないかチェックするメソッドです。
	 * いない場合、tureを返します。
	 * @return
	 */
	public boolean hasUnassignedStudents() {
		return false;
	}

	

}
