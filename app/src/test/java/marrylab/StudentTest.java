package marrylab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class StudentTest {

    /**
     * 
     */
    private Student student;

    /**
     * テスト用の研究室のコース点を保持するフィールド
     */
    private Map<Integer, Double> coursePoints;

    /**
     * テスト用の学生ごとに付与される教員点を保持するフィールド
     */
    private Map<Integer, Double> labScores;

    @Test
    public void setup() throws Exception{
    // サンプル学生を初期化し、コースポイントとラボスコアのマップを作成する。
        student = new Student(1,"山田 太郎",3.5);
        student.setCourse("101:CourseA", "102:CourseB", "103:CourseC");

        coursePoints = new HashMap<>();
        coursePoints.put(101, 3.0);
        coursePoints.put(102, 1.0);
        coursePoints.put(103, 6.0);

        labScores = new HashMap<>();
        labScores.put(student.studentNumber(), 4.5);

        List<Laboratory> labRankList = new ArrayList<>();
        labRankList.add(new Laboratory("青木研"));
        labRankList.add(new Laboratory("赤岩研"));
        labRankList.add(new Laboratory("玉田研"));

        student.setLabRank(labRankList);
                Double Score = 3.5 * 12 + 3.0;

        Double actualScore = student.calculateScore(coursePoints, labScores);

        assertEquals(Score, actualScore, 0.001);
    }
}


