package marrylab;

import java.util.ArrayList;
import java.util.HashMap;

public class Example {

	public static void main(String[] arguments) {
		Table table = new Table();
		Reader reader = new Reader(table, null);
		reader.run();
		Example example = new Example();
		example.debug(table);
		GaleShapleyMatching aGaleShapleyMatching = new GaleShapleyMatching(table);
		aGaleShapleyMatching.run();
		Writer aWriter = new Writer(table, null);
		aWriter.run();
		System.out.println("ok");
	}

	public void debug(Table table){
		table.studentMap().forEach((ID, student) -> {
			System.out.printf("ID = %d ", ID);
			System.out.printf("GPA = %f ", student.GPA());
			System.out.printf("name = %s ", student.name());
			System.out.printf("%d ", student.currentIndex());
			student.myCourse().stream().forEach((courseID) -> {
				System.out.printf("%d ", courseID);
			});
			student.labRank().stream().forEach((lab) -> {
				System.out.printf("%s ", lab.name());
			});
			System.out.println();
		});
		System.out.println(table.studentMap().size());
		table.laboratoryMap().forEach((labName, Lab) -> {
			System.out.printf("%s ", labName);
			System.out.printf("%s ", Lab.name());
			System.out.println();
		});
		System.out.println(table.laboratoryMap().size());
	}
}
