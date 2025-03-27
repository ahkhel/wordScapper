package models;

import java.util.regex.Pattern;

import methods.WordMethods;

public class MyQuery {
	private String queryValue = "";
	private String prefixValue = "";
	private String suffixValue = "";
	public boolean skipSpaces, skipDashes;
	private String queryName;
	
	public MyQuery(String queryName, String prefixValue, String suffixValue) {
		this.queryName = queryName;
		this.prefixValue = prefixValue;
		this.suffixValue = suffixValue;
	}

	public String getRegex(boolean skipSpaces, boolean skipDashes) {
		String regex = RegexMethods.initializeQueryRegex(prefixValue, suffixValue,  skipSpaces, skipDashes);
		return regex;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public String getPrefixValue() {
		return prefixValue;
	}
	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}
	public String getSuffixValue() {
		return suffixValue;
	}
	public void setSuffixValue(String suffixValue) {
		this.suffixValue = suffixValue;
	}
}
