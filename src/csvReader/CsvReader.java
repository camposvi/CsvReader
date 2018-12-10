package csvReader;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import java.awt.Window.Type;

public class CsvReader {

	private JFrame frame;
	private JTextField txtBrowse;
	String location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CsvReader window = new CsvReader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CsvReader() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 305, 115);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btnBrowse = new JButton("browse");
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (txtBrowse.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Choose a CSV File");
				} else {
					Connect con = new Connect();
					con.updateTable(location);
				}
			}
		});

		txtBrowse = new JTextField();
		txtBrowse.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(18)
						.addComponent(txtBrowse, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnUpdate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBrowse, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 78,
										Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtBrowse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBrowse))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnUpdate)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".CSV Files", "csv");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new java.io.File("C:/Users"));
				fileChooser.setDialogTitle("File browser");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				if (fileChooser.showOpenDialog(btnBrowse) == JFileChooser.APPROVE_OPTION) {
					txtBrowse.setText(fileChooser.getSelectedFile().getAbsolutePath());
					location = txtBrowse.getText();
				}
			}
		});
	}

}
