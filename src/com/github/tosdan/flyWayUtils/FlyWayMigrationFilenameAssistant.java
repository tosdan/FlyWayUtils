package com.github.tosdan.flyWayUtils;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

public class FlyWayMigrationFilenameAssistant {

	private final static SimpleDateFormat VERSION = new SimpleDateFormat("yyyy.MM.dd_HH.MM.ss");
	private final static int MIN_CHARS = 15;
	
	public FlyWayMigrationFilenameAssistant() {}

	public static void main( String[] args ) {
        executeProgram();
	}

	/**
	 * 
	 */
	private static void executeProgram() {
		String description = askForDecription();

        if (description != null && description.trim().length() >= MIN_CHARS) {
        	String filename = getFilename(description.trim());
        	
			storeIntoClipboard(filename);
			
	        showSuccessMessage(filename);
        }
	}

	/**
	 * 
	 * @return
	 */
	private static String askForDecription() {
		JFrame frame = getFrame();
		String description = JOptionPane.showInputDialog(frame, "Descrivi l'integrazione/modifica apportata da questo migration script.\n(Minimo "+MIN_CHARS+" caratteri, ma non esagerare)");
		frame.dispose();
		return description;
	}

	/**
	 * 
	 * @param filename
	 */
	private static void showSuccessMessage(String filename) {
		JFrame frame = getFrame();
        JOptionPane.showMessageDialog(frame, "Migration filename copiato negli appunti: " + filename);
		frame.dispose();
	}

	private static JFrame getFrame() {
		JFrame frame = new JFrame("FlyWay VersionGen");
		List<Image> icons = new ArrayList<Image>();
		Image icon = null;
		icon = new ImageIcon(FlyWayMigrationFilenameAssistant.class.getResource("flyway-logo_64.png")).getImage();
		icons.add(icon);
		icon = new ImageIcon(FlyWayMigrationFilenameAssistant.class.getResource("flyway-logo_48.png")).getImage();
		icons.add(icon);
		icon = new ImageIcon(FlyWayMigrationFilenameAssistant.class.getResource("flyway-logo_32.png")).getImage();
		icons.add(icon);
		frame.setIconImages(icons);
        frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		return frame;
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
