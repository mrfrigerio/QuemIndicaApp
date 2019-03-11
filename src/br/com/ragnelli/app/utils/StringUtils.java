package br.com.ragnelli.app.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StringUtils {
	
	public static List<Integer> unidadesParser(String str) {
		String[] tokens = str.split("[,-]");
		List<Integer> unidadesList = Arrays.stream(tokens)
				.map(i -> Integer.parseInt(i.trim()))
				.distinct()
				.sorted()
				.collect(Collectors.toList());
				
		return unidadesList;
	}
	
}
