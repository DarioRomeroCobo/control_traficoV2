package simulator.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {

	private Controller _ctrl;
	private RoadMap map;
	private Collection<Event> events;
	private int time;
	private boolean _stopped;
	private List<JButton> buttons;

	ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		ctrl.addObserver(this);
		_stopped = true;
		buttons = new ArrayList<>();
		initGui();

	}

	private void initGui() {

		JToolBar toolBar = new JToolBar();

		JButton fileButton = new JButton(new ImageIcon("resources/icons/open.png"));

		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try (FileInputStream input = new FileInputStream(file)) {

						_ctrl.reset();
						_ctrl.loadEvents(input);
					}

					catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(fileButton, ex.getMessage());

					} catch (FileNotFoundException f) {

						JOptionPane.showMessageDialog(fileButton, f.getMessage());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(fileButton, "Error inesperado: " + ex.getMessage(), "error",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		});

		toolBar.add(fileButton);

		JButton co2Button = new JButton(new ImageIcon("resources/icons/co2class.png"));
		co2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ChangeCO2ClassDialog dialog = new ChangeCO2ClassDialog(null, _ctrl, map.getVehicles(), time);
				dialog.setVisible(true);
			}

		});
		toolBar.add(co2Button);
		JButton weatherButton = new JButton(new ImageIcon("resources/icons/weather.png"));
		weatherButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeWeatherDialog dialog = new ChangeWeatherDialog(null, _ctrl, map.getRoads(), time);
				dialog.setVisible(true);
			}

		});

		toolBar.add(weatherButton);

		JPanel panelTicks = new JPanel();
		panelTicks.setLayout(new BoxLayout(panelTicks, BoxLayout.X_AXIS));
		JSpinner spinnerTicks = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		panelTicks.add(new JLabel("Ticks:"));
		panelTicks.add(spinnerTicks);

		JButton runButton = new JButton(new ImageIcon("resources/icons/run.png"));
		runButton.addActionListener(e -> {
			_stopped = false;
			enableToolbar();
			run_sim((int) spinnerTicks.getValue());
		});

		JButton stopButton = new JButton(new ImageIcon("resources/icons/stop.png"));
		stopButton.addActionListener(e -> {
			this._stopped = true;
		});

		JButton exitButton = new JButton(new ImageIcon("resources/icons/exit.png"));

		exitButton.addActionListener(e -> {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Exit",
					JOptionPane.YES_NO_OPTION);

			if (response == JOptionPane.YES_OPTION)
				System.exit(0);

		});

		toolBar.add(runButton);
		toolBar.add(stopButton);
		toolBar.add(panelTicks);
		
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(exitButton);

		buttons.add(fileButton);
		buttons.add(co2Button);
		buttons.add(weatherButton);
		buttons.add(runButton);
		buttons.add(exitButton);

		this.add(toolBar);
	}

	private void enableToolbar() {
		for (JButton b : buttons)
			b.setEnabled(this._stopped);
	}

	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
				SwingUtilities.invokeLater(() -> run_sim(n - 1));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error at execute");

				_stopped = true;
				enableToolbar();
			}
		} else {
			_stopped = true;
			enableToolbar();
		}
	}

	public void update(RoadMap map, Collection<Event> events, int time) {
		SwingUtilities.invokeLater(() -> {
			this.map = map;
			this.events = events;
			this.time = time;
			repaint();
		});
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		update(map, events, time);

	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		update(map, events, time);

	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		update(map, events, time);

	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		update(map, events, time);
	}

}
