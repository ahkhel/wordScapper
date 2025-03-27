package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import methods.WordMethods;
import models.MyQuery;
import models.RegexMethods;

public class Test {
	static String targetText = """
			الجــمهوريــة الجزائريـــــــــــة الديمقراطيـــــة  الشعـــبيــــــــة
وزارة الصحة
ولايـــــــــــــــــــة الجــــــــــلفـــــــــــــــــــــة
المؤسسة العمومية للصحة الجوارية بقطارة
العيادة  المتعددة الخدمات  بفيض البطمــــــة
الرقم  :     013    /2024
            تصـــــــــــريح بالــــــــولادة
  كتـابـة الاســــــم واللقـــــــب 
    بــن عــبد الــســـلام  عـــلي عبد الــحق 
الدفتر العائلي رقم : 153/ح م/2017 
لبلديــة: فـــيض الــــبطـــمــة  
دائـرة : فـــيض الــــبطـــمــة   
 ولايـة: الـــــجــــــلـــــــفـــــــة 
 التصريح المسجل عن :     
      سجل المواليد
  )  تصريح القابلة : جرعوب زهرة  (

 لمكتب الدخول للمؤســـسة :         




الكتابة السابقة للاسم واللقب 
بالأحــــرف اللاتـــــينـــيــة 
BEN ABDESSELAM  ALI ABDELHAK     	في عــــــام  ألـفيـــــــن و أربـــعة وعـــشــــــــــرون
وفي: الحادي عشر         من شهر: جــــــانفـــي
نحن مدير المؤسسة العمومية للصحة الجواريــــة بقطــارة
نشعر رئيس المجلس الشعبي البلدي وضابط الحالة المدنية
لبلدية :   فيض البطمة         بأن هذا اليوم 
على الساعة: 16:50            في  : 11/01/2024
السيدة:بن عبد السلام فطيمة الزهرة بنت خليفة و بن عبدالسلام امهاني
المولودة : في  18/02/1990   بـــ: الجلـــــفة 
دائرة : الجلفــــة                 ولاية: الجلفــــة
المهنة:///////                  زوجة : بن عبد الســلام بلخير 
المولود  : 08/06/1982   بــــ  : فيض البطـــمة 
دائرة : فيض البطمة        ولايـــة : الجلفــــــة
السكن: فيض البطــــمة 
 المهنة:////////
وضعت مولودا  من جنس :  ذكــــر 
والذي حمل  (ت) الاسم  التالي : عــلي عـــبد الــــحق 


                                                                   	                   فيض البطمة  في : 14/01/2024
                                                          ع/ المديــــر
			""";

	public static void main(String[] args) throws IOException {
		String t = "فيض البطمة بأن هذا اليوم على الساعة: 20:14 في : 04/01/2024السيدة: العيش مريم بنت خليفة و ربيحة بنت ابراهيم المولودة في : 17/10/1993 ب: فيض البطمة";
		System.out.println(t.split(" ").length);
		targetText = WordMethods.cleanText(targetText, true, true);
		String prefix = "لبلديــة:";
		String suffix = "دائـرة";
		MyQuery query = new MyQuery("البلدية", prefix, suffix);
		String myResult = RegexMethods.myRegexMatcher(targetText, query.getRegex(true, true));
		System.out.println(String.format("myRegexMatcher Result = %s", myResult));
	}
	public static String myRegexMatcher(String text, String prefix, String suffix) {
//	    String regex = String.format("(?=(%s\\s*(.*?)\\s*%s))", prefix, suffix);
//	    String regex = String.format("(?=(%s\\s*([^\\r\\n]*?)\\s*%s))", prefix, suffix);
//		String regex = String.format("(?=(%s\\s*((?:\\S+\\s*){1,10}?)\\s*%s))", prefix, suffix);
		String regex = String.format("(?=%s\\s*((?:\\S+\\s*){1,10})\\s*%s)", prefix, suffix);

	    Pattern pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher(text);
        String lastMatch = null;
        int i = 0;
        while (matcher.find()) { // Find the first occurrence only
        	i++;
        	lastMatch = matcher.group(2);
            System.out.println(String.format("Found0: %d : %s", i , matcher.group(0))); 
            System.out.println(String.format("Found1: %d : %s", i , matcher.group(1))); 
        	System.out.println(String.format("Found2: %d : %s", i , matcher.group(2))); 
        }
		System.out.println(String.format("MyRegextMatcher Result:\n %s", lastMatch));
		return lastMatch;
	}
	public static String regexMatcher(String regex, String text) {
		Pattern pattern = Pattern.compile( regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(text);
		
        String lastMatch = null;        
        while (matcher.find()) {
            lastMatch = matcher.group(1); // Capture only the content between the prefix and suffix
    		System.out.println(String.format("Result:\n %s", lastMatch));
    		
        }
        if (lastMatch != null) {
            System.out.println("Matched word/phrase: " + lastMatch);
        } else {
            System.out.println("No match found.");
        }
		
		
		System.out.println(String.format("Final Result:\n %s", lastMatch));
		return lastMatch;	
		
	}
	
	
	   public static String claudeFindTextBetweenPrefixSuffix(String text, String prefix, String suffix) {
	        // Regex pattern explanation:
	        // (?<!\w)          - Negative lookbehind to ensure no word character before prefix
	        // (?!.*\b{prefix}\b) - Negative lookahead to skip if there's another prefix before
	        // {prefix}\s*      - Match the prefix followed by optional whitespace
	        // (.*?)            - Non-greedy capture of text between prefix and suffix
	        // \b{suffix}\b     - Match the suffix as a whole word
	        String patternString = "(?<!\\w)(?!.*\\b" + Pattern.quote(prefix) + "\\b)" + 
	                               Pattern.quote(prefix) + "\\s*(.*?)\\b" + 
	                               Pattern.quote(suffix) + "\\b";
	        
	        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
	        Matcher matcher = pattern.matcher(text);
	        
	        if (matcher.find()) {
	            return matcher.group(1);
	        }
	        return null;
	    }
	    public static String googleFindInnerText(String text, String prefix, String suffix) {
	        String patternString = "(?<!" + Pattern.quote(prefix) + ".*)" + Pattern.quote(prefix) + "(.*?)" + Pattern.quote(suffix);
	        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
	        Matcher matcher = pattern.matcher(text);

	        if (matcher.find()) {
	            return matcher.group(1).trim();
	        } else {
	            return null;
	        }
	    }
	    public static String deepSeek(String text) {
	        String regex = String.format("\\bفي\\b\\s+((?:(?!\\bin\\b).)*?\\s+\\bالسيدة\\b");
	        String regex0 = "\\bفي\\b\\s+((?:(?!\\bفي\\b)[^\\s])+)\\s+\\bالسيدة\\b";
	        String result = "";
	        
	        String regex00 = "\\bفي\\b\\s*(:[\\s\\p{Punct}]*)?((?:(?!\\bفي\\b).)+?)\\s*\\bالسيدة\\b";
	        
	        Pattern pattern = Pattern.compile(regex00, Pattern.DOTALL); // DOTALL mode to include newlines
	        Matcher matcher = pattern.matcher(text);
	        
	        if (matcher.find()) {
	            result = matcher.group(2).trim(); // Group 2 contains the matched content
	            System.out.println("Matched: '" + result + "'");
	            return result;
	        }
	        return result;
	    }

}
