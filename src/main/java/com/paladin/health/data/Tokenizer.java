package com.paladin.health.data;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Word;
import com.paladin.framework.utils.StringUtil;

public class Tokenizer {

	protected Dictionary dic;
	protected Seg seg;

	private final static Tokenizer instance = new Tokenizer();

	public static Tokenizer getTokenizer() {
		return instance;
	}

	private Tokenizer() {
		dic = Dictionary.getInstance();
		seg = new ComplexSeg(dic);
	}

	public String segWords(Reader input, String wordSpilt) throws IOException {
		StringBuilder sb = new StringBuilder();
		MMSeg mmSeg = new MMSeg(input, seg);
		Word word = null;
		boolean first = true;
		while ((word = mmSeg.next()) != null) {
			if (!first) {
				sb.append(wordSpilt);
			}
			String w = word.getString();
			sb.append(w);
			first = false;
		}
		return sb.toString();
	}

	public List<String> segWords(Reader input) throws IOException {
		MMSeg mmSeg = new MMSeg(input, seg);
		Word word = null;
		List<String> result = new ArrayList<>();
		while ((word = mmSeg.next()) != null) {
			result.add(word.getString());
		}
		return result;
	}

	public String segWords(String txt, String wordSpilt) throws IOException {
		return segWords(new StringReader(txt), wordSpilt);
	}

	public List<String> segWords(String txt) throws IOException {
		return segWords(new StringReader(txt));
	}

	public static void main(String[] args) throws IOException {
		Tokenizer t = new Tokenizer();
		String str = "我在昆山上班";
		List<String> result = t.segWords(str);
		HashSet<String> set = new HashSet<>();

		for (String s : result)
			set.add(s);

		System.out.println(StringUtil.toString(set));

	}

}
