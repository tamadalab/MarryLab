package marrylab;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;

/**
 * CSVの入出力を司るクラスです。
 */
public class IO {

	/**
	 * 表を保持するフィールドです。
	 */
	protected Table table;

	/**
	 * コンストラクタ：初期値を設定しておく。
	 * 
	 * @param table
	 */
	public IO(Table table) {
		this.table = table;
		return;
	}

	/**
	 * CSVファイルからリストに変換するメソッドです。
	 */
	public List<String[]> CSVtoList(String filePass) {
		List<String[]> resultList = new ArrayList<String[]>();
		File inputFile = new File(filePass);
		// ファイルが存在しない場合のエラー処理
		if(!inputFile.exists()){ 
			System.out.printf("%sを用意してください。%n", filePass);
			System.exit(1);
		}
		try {
			resultList = Csv.load(inputFile,
					new CsvConfig(), new StringArrayListHandler());
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultList.remove(0);
		return resultList;
	}

	/**
	 * リストからCSVファイルに変換するメソッドです。
	 * 
	 * @param list     振り分けた結果が入っているリスト
	 * @param filePass 出力したいCSVファイルのパス
	 */
	public void listToCSV(List<String[]> list, String filePass) {
		File outputFile = new File(filePass);
		if(!outputFile.exists()){
			try {
				outputFile.getParentFile().mkdirs();
				outputFile.createNewFile();
			} catch (IOException e) {
				return;
			}
		}
		try {
			Csv.save(list, outputFile, new CsvConfig(), new StringArrayListHandler());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
	}

	public String getFilePass(String title){
		JFileChooser fileChooser = new JFileChooser();
		// ダイアログのタイトルを設定
        fileChooser.setDialogTitle(title);

        // 選択ボタンのテキストを設定
        fileChooser.setApproveButtonText("選択");

		// ダイアログを表示し、ユーザーにファイルを選択させる
        int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // 選択されたファイルのパスを取得
            String filePath = selectedFile.getAbsolutePath();
			return filePath;
        } else {
            System.out.println("ファイル選択がキャンセルされました。");
			System.exit(1);
			return null;
        }
	}
}
