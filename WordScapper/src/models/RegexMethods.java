package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import methods.WordMethods;

public class RegexMethods {
	public static String initializeQueryRegex(String prefix, String suffix, boolean ignoreMultiSpaces, boolean ignoreDashes) {
		prefix = WordMethods.cleanText(prefix, ignoreMultiSpaces, ignoreMultiSpaces);
		suffix = WordMethods.cleanText(suffix, ignoreMultiSpaces, ignoreMultiSpaces);
		String regex = String.format("(?=(%s\\s*((?:\\S+\\s*){1,10}?)\\s*%s))", prefix, suffix);		
		return regex;
	}
	
	public static String myRegexMatcher(String text, String regex) {
		Pattern pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher(text);
        String lastMatch = null;
        int i = 0;
        while (matcher.find()) { // Find the first occurrence only
        	i++;
        	lastMatch = matcher.group(2);
        }
		return lastMatch;
	}
	
	
	public static String myRegexMatcher(String text, String prefix, String suffix, boolean ignoreMulipleSpaces, boolean ignoreElongation) {
	    
		String regex = String.format("(?=(%s\\s*(.*?)\\s*%s))", prefix, suffix);
		Pattern pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher(text);
        String lastMatch = null;
        int i = 0;
        while (matcher.find()) { // Find the first occurrence only
        	i++;
        	lastMatch = matcher.group(2);
        }
		return lastMatch;
	}
}
