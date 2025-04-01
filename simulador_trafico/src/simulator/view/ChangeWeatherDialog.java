package simulator.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Road;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class ChangeWeatherDialog extends JDialog {

	private Controller _ctrl;
	private List<Road> roads;
	private int currTime;

	public ChangeWeatherDialog(Frame parent, Controller _ctrl, List<Road> roads, int currTime) {
		super(parent, "Change Road Weather", true);
		this._ctrl = _ctrl;
		this.roads = roads;
		this.currTime = currTime;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JLabel message = new JLabel(
				"Schedule an event to change the weather of a road after a given number of simulation ticks from now.");
		message.setAlignmentX(CENTER_ALIGNMENT);
		this.add(message);
		this.add(Box.createVerticalStrut(10));

		JPanel panelRoad = new JPanel();
		panelRoad.setLayout(new BoxLayout(panelRoad, BoxLayout.X_AXIS));

		JComboBox<Road> rList = new JComboBox<>(roads.toArray(new Road[0]));
		rList.setSelectedIndex(0);

		panelRoad.add(new JLabel("Road: "));
		panelRoad.add(rList);
		this.add(panelRoad);
		this.add(Box.createVerticalStrut(5));

		JPanel panelWeather = new JPanel();
		panelWeather.setLayout(new BoxLayout(panelWeather, BoxLayout.X_AXIS));

		JComboBox<Weather> weatherList = new JComboBox<>(Weather.values());

		panelWeather.add(new JLabel("Weather: "));
		panelWeather.add(weatherList);
		this.add(panelWeather);
		this.add(Box.createVerticalStrut(5));

		JPanel panelTicks = new JPanel();
		panelTicks.setLayout(new BoxLayout(panelTicks, BoxLayout.X_AXIS));

		JSpinner spinnerTicks = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

		panelTicks.add(new JLabel("Ticks: "));
		panelTicks.add(spinnerTicks);
		this.add(panelTicks);
		this.add(Box.createVerticalStrut(10));

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> setVisible(false));

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<Pair<String, Weather>> si = new ArrayList<>();
				si.add(new Pair<>(rList.getSelectedItem().toString(), (Weather) weatherList.getSelectedItem()));
				_ctrl.addEvent(new SetWeatherEvent((int) spinnerTicks.getValue() + currTime, si));
				setVisible(false);
			}
		});

		panelButtons.add(cancelButton);
		panelButtons.add(Box.createHorizontalStrut(10));
		panelButtons.add(okButton);
		this.add(panelButtons);
		this.pack();

	}

}
