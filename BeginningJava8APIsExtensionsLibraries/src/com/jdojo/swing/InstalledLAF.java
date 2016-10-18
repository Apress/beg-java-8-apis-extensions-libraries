// InstalledLAF.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class InstalledLAF extends JFrame {
	JLabel nameLbl = new JLabel("Name:");
	JTextField nameFld = new JTextField(20);
	JButton saveBtn = new JButton("Save");
	JTextField lafClassNameFld = new JTextField();
	ButtonGroup radioGroup = new ButtonGroup();
	static final Map<String, String> installedLAF = new TreeMap<>();

	static {
		for (LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()) {
			installedLAF.put(lafInfo.getName(), lafInfo.getClassName());
		}
	}

	public InstalledLAF(String title) {
		super(title);
		initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();

		// Get he current look and feel
		LookAndFeel currentLAF = UIManager.getLookAndFeel();
		String currentLafName = currentLAF.getName();
		String currentLafClassName = currentLAF.getClass().getName();

		lafClassNameFld.setText(currentLafClassName);
		lafClassNameFld.setEditable(false);

		// Build the panels
		JPanel topPanel = buildTopPanel();
		JPanel leftPanel = buildLeftPanel(currentLafName);
		JPanel rightPanel = buildRightPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(leftPanel, BorderLayout.WEST);
		contentPane.add(rightPanel, BorderLayout.CENTER);
	}

	private void setLAF(String lafClassName) {
		try {
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(this);
			this.pack();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel buildTopPanel() {
		JPanel panel = new JPanel();
		panel.add(lafClassNameFld);
		panel.setBorder(getBorder("L&F Class Name"));
		return panel;
	}

	private JPanel buildLeftPanel(String currentLafName) {
		JPanel panel = new JPanel();
		panel.setBorder(getBorder("L&F Name"));
		Box vBox = Box.createVerticalBox();

		// Add a radio button for each installed L&F		
		for (String lafName : installedLAF.keySet()) {
			JRadioButton radioBtn = new JRadioButton(lafName);
			if (lafName.equals(currentLafName)) {
				radioBtn.setSelected(true);
			}

			radioBtn.addItemListener(this::changeLAF);
			vBox.add(radioBtn);
			radioGroup.add(radioBtn);
		}

		panel.add(vBox);
		return panel;
	}

	private JPanel buildRightPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(getBorder("Swing Components"));

		Box hBox = Box.createHorizontalBox();
		hBox.add(nameLbl);
		hBox.add(nameFld);
		hBox.add(saveBtn);
		panel.add(hBox);

		return panel;
	}

	private void changeLAF(ItemEvent e) {
		if (e.getSource() instanceof AbstractButton) {
			AbstractButton btn = (AbstractButton) e.getSource();
			String lafName = btn.getText();
			String lafClassName = installedLAF.get(lafName);
			this.lafClassNameFld.setText(lafClassName);
			try {
				UIManager.setLookAndFeel(lafClassName);
				SwingUtilities.updateComponentTreeUI(this);
				this.pack();
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private Border getBorder(String title) {
		Border etched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border titledBorder = BorderFactory.createTitledBorder(etched, title);
		return titledBorder;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			InstalledLAF lafApp = new InstalledLAF("Swing L&F");
			lafApp.pack();
			lafApp.setVisible(true);
		});
	}
}
