package marrylab;

public class Example {

	public static void main(String[] arguments) {
		Table table = new Table();
		Reader reader = new Reader(table, null);
		reader.run();
		GaleShapleyMatching aGaleShapleyMatching = new GaleShapleyMatching(table);
		aGaleShapleyMatching.run();
		Writer aWriter = new Writer(table, null);
		aWriter.run();
		table.laboratoryMap().forEach((name, lab) -> {
			lab.studentList().forEach((student) -> {
				System.out.println(student.name());
			});
		});
	}
}
