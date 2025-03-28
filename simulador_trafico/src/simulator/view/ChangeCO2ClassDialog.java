package simulator.view;

import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

import simulator.control.Controller;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog {

	private Controller _ctrl;

	public ChangeCO2ClassDialog(Frame parent, Controller _ctrl, Vehicle[] vehicles) {
		super(parent, "Change CO2 Class", true);
		this._ctrl = _ctrl;
		initGUI(vehicles);
	}

	private void initGUI(Vehicle[] vehicles) {

		this.setLayout(new BorderLayout());

		JLabel message = new JLabel(
				"Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now.");

		this.add(message, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		
		JComboBox<Vehicle> listV = new JComboBox<Vehicle>(vehicles);
		listV.setSelectedIndex(0);
		
		panel.add(new JLabel("Vehicle:"));
		panel.add(listV);
		
		JPanel panel1 = new JPanel();
	
		
		JComboBox<Integer> co2List = new JComboBox<>();
		for (int i = 0; i <= 10; i++) {
			co2List.addItem(i);
		}
		panel1.add(new JLabel("CO2 Class:"));
		panel1.add(co2List);
	
		JPanel panel2 = new JPanel();
		
		JSpinner spinnerTicks = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		panel2.add(new JLabel("Ticks:"));
		panel2.add(spinnerTicks);
		
		JButton ButtonC= new JButton("Cancel");
		
		JButton ButtonOK= new JButton("OK");
		
		this.add(panel, BorderLayout.WEST);
		this.add(panel1, BorderLayout.CENTER);
		this.add(panel2, BorderLayout.EAST);
	}

}
