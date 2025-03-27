package methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordMethods {
	public static XWPFDocument openDocx(File openedDocxFile) throws IOException {
        FileInputStream fis = new FileInputStream(openedDocxFile);
        XWPFDocument document = new XWPFDocument(fis);
        return document;
	}
		
	
	public static String getTableContents(XWPFTable table, boolean ignoreMultiSpaces, boolean ignoreDashes) {
        StringBuilder tableText = new StringBuilder();
        for (XWPFTableRow row : table.getRows()) { // Loop through each row
            StringBuilder rowText = new StringBuilder();
            for (XWPFTableCell cell : row.getTableCells()) { // Loop through each cell
                rowText.append(cell.getText()).append(" \n "); // Get text from each cell
            }
            tableText.append(rowText.append(" \n\n "));
        }
        return cleanText(tableText.toString(), ignoreMultiSpaces, ignoreDashes);
	}
	
	public static String cleanText(String text, boolean ignoreMultiSpaces, boolean ignoreDashes) {
		if(text != null) {
			text = text.replaceAll("\\s*:\\s*", " : ");
			if(ignoreMultiSpaces) text = text.replaceAll("\\s+", " ").trim();
			if(ignoreDashes) text = text.replaceAll("ـ", "");
		}
		return text;
	}
}
