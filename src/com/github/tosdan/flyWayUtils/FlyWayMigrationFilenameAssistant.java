package com.github.tosdan.flyWayUtils;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FlyWayMigrationFilenameAssistant {

	private final static SimpleDateFormat VERSION = new SimpleDateFormat("yyyy.MM.dd_HH.MM.ss");
	
	public FlyWayMigrationFilenameAssistant() {}

	public static void main( String[] args ) {
        executeProgram();
	}

	/**
	 * 
	 */
	private static void executeProgram() {
		String description = askForDecription();

        String filename = getFilename(description).trim();
        
		storeIntoClipboard(filename);
		
        showSuccessMessage(filename);
	}

	/**
	 * 
	 * @return
	 */
	private static String askForDecription() {
		
		String description = JOptionPane.showInputDialog("Inserisci la descrizione del file di migration.");
		return description;
	}

	/**
	 * 
	 * @param filename
	 */
	private static void showSuccessMessage(String filename) {
		JFrame frame = new JFrame();
        frame.setTitle("FlyWay Migratoin filename assistant.");
        JOptionPane.showMessageDialog(frame.getContentPane(), "Migration filename copiato negli appunti: " + filename);
	}

	/**
	 * 
	 * @param description
	 * @return
	 */
	private static String getFilename(String description) {
        String version = VERSION.format(new Date());
        String template = "V${version}__${description}.sql";
        String filename = template.replace("${version}", version).replace("${description}", description);
		return filename;
	}

	/**
	 * 
	 * @param text
	 */
	private static void storeIntoClipboard(String text) {
		StringSelection selection = new StringSelection(text);
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(selection, selection);
	}
}
