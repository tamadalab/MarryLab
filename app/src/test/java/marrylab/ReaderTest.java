package marrylab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;


public class ReaderTest {
	@Test
	public void testReader() throws IOException {
		Reader reader = new Reader(new Table(), null);
		reader.readStudentGPA("./src/main/resources/data/学業成績(情理)_2023.csv");
	}
}
