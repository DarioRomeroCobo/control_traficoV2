package simulator.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import simulator.misc.Pair;
import simulator.model.SetContClassEvent;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog {

	private Controller _ctrl;
	private List<Vehicle> vehicles;
	private int currTime;

	public ChangeCO2ClassDialog(Frame parent, Controller _ctrl, List<Vehicle> vehicles, int currTime) {
		super(parent, "Change CO2 Class", true);
		this._ctrl = _ctrl;
		this.vehicles = vehicles;
		this.currTime = currTime;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JLabel message = new JLabel(
				"Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now.");
		message.setAlignmentX(CENTER_ALIGNMENT);
		this.add(message);
		this.add(Box.createVerticalStrut(10));
		
		JPanel interactivePanel = new JPanel();
		interactivePanel.setLayout(new BoxLayout(interactivePanel, BoxLayout.X_AXIS));
		
		JPanel panelVehicle = new JPanel();
		panelVehicle.setLayout(new BoxLayout(panelVehicle, BoxLayout.X_AXIS));
		
		JComboBox<Vehicle> vList = new JComboBox<>(vehicles.toArray(new Vehicle[0]));
		vList.setSelectedIndex(0);

		panelVehicle.add(new JLabel("Vehicle: "));
		panelVehicle.add(vList);
		interactivePanel.add(panelVehicle);
		interactivePanel.add(Box.createHorizontalStrut(5));

		JPanel panelCO2 = new JPanel();
		panelCO2.setLayout(new BoxLayout(panelCO2, BoxLayout.X_AXIS));

		JComboBox<Integer> co2List = new JComboBox<>();
		for (int i = 0; i <= 10; i++) {
			co2List.addItem(i);
		}

		panelCO2.add(new JLabel("CO2 Class: "));
		panelCO2.add(co2List);
		interactivePanel.add(panelCO2);
		interactivePanel.add(Box.createHorizontalStrut(5));

		JPanel panelTicks = new JPanel();
		panelTicks.setLayout(new BoxLayout(panelTicks, BoxLayout.X_AXIS));

		JSpinner spinnerTicks = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

		panelTicks.add(new JLabel("Ticks: "));
		panelTicks.add(spinnerTicks);
		interactivePanel.add(panelTicks);
		interactivePanel.add(Box.createHorizontalStrut(5));

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> setVisible(false));

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<Pair<String, Integer>> si = new ArrayList<>();
				si.add(new Pair<>(vList.getSelectedItem().toString(), (int) co2List.getSelectedItem()));
				_ctrl.addEvent(new SetContClassEvent((int) spinnerTicks.getValue() + currTime, si));
				setVisible(false);
			}
		});

		panelButtons.add(cancelButton);
		panelButtons.add(Box.createHorizontalStrut(10));
		panelButtons.add(okButton);
		this.add(interactivePanel);
		this.add((Box.createVerticalStrut(10)));
		this.add(panelButtons);
		this.pack();
	}

}
