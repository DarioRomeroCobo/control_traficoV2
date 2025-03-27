package simulator.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import simulator.control.Controller;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog {

	private Controller _ctrl;

	public ChangeCO2ClassDialog(Frame parent, Controller _ctrl, String[] vehicles) {
		super(parent, "Change CO2 Class", true);
		this._ctrl = _ctrl;
		initGUI(vehicles);
	}

	private void initGUI(String[] vehicles) {

		this.setLayout(new BorderLayout());

		JLabel message = new JLabel(
				"Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now.");

		this.add(message, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.add(new JLabel("Vehicle:"));
		JComboBox<Vehicle> list = new JComboBox<Vehicle>();
		list.setSelectedIndex(0);
		list.addActionListener({
			
			
			
			
		});
		panel.add(list);

		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("CO2 Class:"));
		
		this.add(panel, BorderLayout.WEST);
		this.add(panel1, BorderLayout.CENTER);
	}

}
