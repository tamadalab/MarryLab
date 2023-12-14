package marrylab;

public class Example {

	public static void main(String[] arguments) {
		Table table = new Table();
		Reader reader = new Reader(table);
		reader.run();
		GaleShapleyMatching aGaleShapleyMatching = new GaleShapleyMatching(table);
		aGaleShapleyMatching.run();
		System.out.println("ok");
		Writer aWriter = new Writer(table);
		aWriter.run();
	}
}
