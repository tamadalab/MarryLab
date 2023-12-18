package marrylab;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生の研究室の配属結果をcsvファイルで書き出すクラスです。
 */
public class Writer extends IO {
	public Writer(Table table) {
		super(table);
	}

	/**
	 * csvファイルの書き出しを実行します。
	 */
	public void run() {
		this.writeStudent("./src/main/resources/result/result1.csv");
		this.writeLaboratory("./src/main/resources/result/result2.csv");
	}

	/**
	 * 学生の研究室の配属結果のCSVファイルを書き出すメソッドです。
	 * 
	 * @param list     振り分けた結果が入っているリスト
	 * @param filepath 出力したいCSVファイルのパス
	 */
	public void writeStudent(String filepath) {
		List<String[]> resultList = new ArrayList<>();
		this.table.studentMap().forEach((ID, student) -> {
			// ID,生徒名,研究室名,現在の希望順位をStringの配列にしてListに入れる
			String name = student.name();
			String resultLab = "";
			if(student.resultLaboratory() != null){
				resultLab = student.resultLaboratory().name();
			}
			String[] result = { ID.toString(), name, resultLab, String.valueOf(student.currentIndex()) };
			resultList.add(result);
		});
		this.listToCSV(resultList, filepath);
	}

	/**
	 * 研究室のメンバーのcsvファイルを書き出すメソッドです。
	 * @param filepath 出力したいcsvファイルパス
	 */
	public void writeLaboratory(String filepath){
		List<String[]> resultList = new ArrayList<>();
		this.table.laboratoryMap().forEach((name, lab) -> {
			List<String> result = new ArrayList<>();
			result.add(name);
			lab.studentList().stream().forEach(student -> result.add(student.name()));
			String[] resultArray = result.toArray(new String[result.size()]);
			resultList.add(resultArray);
		});
		this.listToCSV(resultList, filepath);
	}
}
