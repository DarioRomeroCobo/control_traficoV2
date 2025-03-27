package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {

	private Controller _ctrl;
	private RoadMap map;
	
	ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		ctrl.addObserver(this);
		initGui();
		
		
	}

	private void initGui() {
		JToolBar toolBar = new JToolBar();

		JButton fileButton = new JButton(new ImageIcon("resources/icons/open.png"));

		toolBar.add(fileButton);

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

		JButton CO2Button = new JButton(new ImageIcon("resources/icons/co2class.png"));
		CO2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ChangeCO2ClassDialog dialog = new ChangeCO2ClassDialog(this, _ctrl, );
				dialog.setVisible(true);
			}

		});

		toolBar.add(CO2Button);

		this.add(toolBar);
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		update(map);

	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub

	}

}
