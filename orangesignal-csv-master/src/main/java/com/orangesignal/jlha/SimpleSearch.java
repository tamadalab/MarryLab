/**
 * Copyright (C) 2002  Michel Ishizuka  All rights reserved.
 * 
 * 以下の条件に同意するならばソースとバイナリ形式の再配布と使用を
 * 変更の有無にかかわらず許可する。
 * 
 * １．ソースコードの再配布において著作権表示と この条件のリスト
 *     および下記の声明文を保持しなくてはならない。
 * 
 * ２．バイナリ形式の再配布において著作権表示と この条件のリスト
 *     および下記の声明文を使用説明書もしくは その他の配布物内に
 *     含む資料に記述しなければならない。
 * 
 * このソフトウェアは石塚美珠瑠によって無保証で提供され、特定の目
 * 的を達成できるという保証、商品価値が有るという保証にとどまらず、
 * いかなる明示的および暗示的な保証もしない。
 * 石塚美珠瑠は このソフトウェアの使用による直接的、間接的、偶発
 * 的、特殊な、典型的な、あるいは必然的な損害(使用によるデータの
 * 損失、業務の中断や見込まれていた利益の遺失、代替製品もしくは
 * サービスの導入費等が考えられるが、決してそれだけに限定されない
 * 損害)に対して、いかなる事態の原因となったとしても、契約上の責
 * 任や無過失責任を含む いかなる責任があろうとも、たとえそれが不
 * 正行為のためであったとしても、またはそのような損害の可能性が報
 * 告されていたとしても一切の責任を負わないものとする。
 */

package com.orangesignal.jlha;

/**
 * 特別な検索機構を用いない LzssSearchMethod の最もシンプルな実装。<br>
 * 検索機構を用いないため、 他の検索機構を用いる実装と比べると遅いが、 メモリ消費量も非常に少ない。<br>
 * 
 * @author $Author: dangan $
 * @version $Revision: 1.0 $
 */
public class SimpleSearch implements LzssSearchMethod {

	/**
	 * LZSS辞書サイズ。
	 */
	private int dictionarySize;

	/**
	 * LZSS圧縮に使用される値。 最大一致長を示す。
	 */
	private int maxMatch;

	/**
	 * LZSS圧縮に使用される閾値。 一致長が この値以上であれば、圧縮コードを出力する。
	 */
	private int threshold;

	/**
	 * LZSS圧縮を施すためのバッファ。 position を境に 前半は辞書領域、 後半は圧縮を施すためのデータの入ったバッファ。 LzssSearchMethodの実装内では読み込みのみ許される。
	 */
	private byte[] textBuffer;

	/**
	 * 辞書の限界位置。 TextBuffer前半の辞書領域にデータが無い場合に 辞書領域にある不定のデータ(Javaでは0)を使用 して圧縮が行われるのを抑止する。
	 */
	private int dictionaryLimit;

	// ------------------------------------------------------------------
	// Constructor

	/**
	 * 特別な検索機構を用いないシンプルな LzssSearchMethod を構築する。<br>
	 * 
	 * @param dictionarySize 辞書サイズ
	 * @param maxMatch 最大一致長
	 * @param threshold 圧縮、非圧縮の閾値
	 * @param textBuffer LZSS圧縮を施すためのバッファ
	 */
	public SimpleSearch(final int dictionarySize, final int maxMatch, final int threshold, final byte[] textBuffer) {
		this.dictionarySize = dictionarySize;
		this.maxMatch = maxMatch;
		this.threshold = threshold;
		this.textBuffer = textBuffer;
		dictionaryLimit = this.dictionarySize;
	}

	// ------------------------------------------------------------------
	// method of jp.gr.java_conf.dangan.util.lha.LzssSearchMethod

	/**
	 * SimpleSearch は検索機構を使用しないため このメソッドは何もしない。
	 * 
	 * @param position TextBuffer内のデータパタンの開始位置
	 */
	@Override
	public void put(final int position) {
	}

	/**
	 * TextBuffer 内の辞書領域にあるデータパタンから position から始まるデータパタンと 最長の一致を持つものを検索する。<br>
	 * 
	 * @param position TextBuffer内のデータパタンの開始位置。
	 * @return 一致が見つかった場合は LzssOutputStream.createSearchReturn によって生成された一致位置と一致長の情報を持つ値、 一致が見つからなかった場合は LzssOutputStream.NOMATCH。
	 * @see LzssOutputStream#createSearchReturn(int,int)
	 * @see LzssOutputStream#NOMATCH
	 */
	@Override
	public int searchAndPut(final int position) {
		int matchlen = threshold - 1;
		int matchpos = position;
		final int scanlimit = Math.max(dictionaryLimit, position - dictionarySize);
		int scanpos = position - 1;

		final byte[] buf = textBuffer;
		final int max = position + maxMatch;
		int p = 0;
		int s = 0;
		int len = 0;
		while (scanlimit < scanpos) {
			s = scanpos;
			p = position;
			while (buf[s] == buf[p]) {
				s++;
				p++;
				if (max <= p) {
					break;
				}
			}

			len = p - position;
			if (matchlen < len) {
				matchpos = scanpos;
				matchlen = len;
				if (maxMatch == len) {
					break;
				}
			}
			scanpos--;
		}

		if (threshold <= matchlen) {
			return LzssOutputStream.createSearchReturn(matchlen, matchpos);
		}
		return LzssOutputStream.NOMATCH;
	}

	/**
	 * TextBuffer 内の辞書領域にあるデータパタンから position から始まるデータパタンと 最長の一致を持つものを検索する。<br>
	 * 
	 * @param position TextBuffer内のデータパタンの開始位置。
	 * @param lastPutPos 最後に登録したデータパタンの開始位置。
	 * @return 一致が見つかった場合は LzssOutputStream.createSearchReturn によって生成された一致位置と一致長の情報を持つ値、 一致が見つからなかった場合は LzssOutputStream.NOMATCH。
	 * @see LzssOutputStream#createSearchReturn(int,int)
	 * @see LzssOutputStream#NOMATCH
	 */
	@Override
	public int search(final int position, final int lastPutPos) {
		int matchlen = threshold - 1;
		int matchpos = position;
		final int scanlimit = Math.max(dictionaryLimit, position - dictionarySize);
		int scanpos = position - 1;

		final byte[] buf = textBuffer;
		final int max = Math.min(position + maxMatch, textBuffer.length);
		int p = 0;
		int s = 0;
		int len = 0;
		while (scanlimit < scanpos) {
			s = scanpos;
			p = position;
			while (buf[s] == buf[p]) {
				s++;
				p++;
				if (max <= p) {
					break;
				}
			}

			len = p - position;
			if (matchlen < len) {
				matchpos = scanpos;
				matchlen = len;
				if (maxMatch == len) {
					break;
				}
			}
			scanpos--;
		}

		if (threshold <= matchlen) {
			return LzssOutputStream.createSearchReturn(matchlen, matchpos);
		}
		return LzssOutputStream.NOMATCH;
	}

	/**
	 * LzssOutputStream が slide() でTextBuffer内のデータを DictionarySize だけ移動させる際に検索機構内のデータを それらと矛盾無く移動させる処理を行う。
	 */
	@Override
	public void slide() {
		dictionaryLimit = Math.max(0, dictionaryLimit - dictionarySize);
	}

	/**
	 * SimpleSearch は検索機構を使用しないため常に 0 を返す。
	 * 
	 * @return 常に 0
	 */
	@Override
	public int putRequires() {
		return 0;
	}

}