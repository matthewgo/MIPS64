package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class OutputsPanel {
	private static JPanel panel;

	public static JPanel getInstance() {
		if (panel == null) {
			buildPanel();
		}
		return panel;
	}

	private static void buildPanel() {
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Outputs"));
	}
}
