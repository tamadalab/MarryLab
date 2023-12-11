package marrylab;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Writer extends IO {
	public Writer(Table table, String filePath) {
		super(table, filePath);
	}

	public void run() {
		this.writeStudent("./src/main/resources/result/result1.csv");
		this.writeLaboratory("./src/main/resources/result/result2.csv");
	}

	/**
	 * CSVファイルを書き出すメソッドです。
	 * 
	 * @param list     振り分けた結果が入っているリスト
	 * @param filePass 出力したいCSVファイルのパス
	 */
	public void writeStudent(String filepath) {
		List<String[]> resultList = new ArrayList<>();
		this.table.studentMap().forEach((ID, student) -> {
			// ID,生徒名,研究室名,現在の希望順位をStringの配列にしてListに入れる
			String name = student.name();
			String resultLab = "";
			if(!Objects.equals(student.resultLaboratory(), null)){
				resultLab = student.resultLaboratory().name();
			}
			String[] result = { ID.toString(), name, resultLab, String.valueOf(student.currentIndex()) };
			resultList.add(result);
		});
		this.ListtoCSV(resultList, filepath);
	}

	public void writeLaboratory(String filepath){
		List<String[]> resultList = new ArrayList<>();
		this.table.laboratoryMap().forEach((name, lab) -> {
			List<String> result = new ArrayList<>();
			result.add(name);
			lab.studentList().stream().forEach(student -> result.add(student.name()));
			String[] resultArray = result.toArray(new String[result.size()]);
			resultList.add(resultArray);
		});
		this.ListtoCSV(resultList, filepath);
	}
}
