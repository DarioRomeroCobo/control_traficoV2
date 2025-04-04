package simulator.view;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver {

	private JLabel timeLabel;
	private JLabel eventsLabel;

	public StatusBar(Controller _ctrl) {
		initGui();
		_ctrl.addObserver(this);
	}

	private void initGui() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); 

		timeLabel = new JLabel("Time: " + 0);
		this.add(timeLabel);


		this.add(Box.createRigidArea(new Dimension(20, 0)));
		
		eventsLabel = new JLabel("");

		this.add(eventsLabel); 

	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		timeLabel.setText("Time: " + time);
		eventsLabel.setText("");
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		eventsLabel.setText("Event added (" + e.toString() + ")");

	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		timeLabel.setText("Time: " + time);
		eventsLabel.setText("");
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		timeLabel.setText("Time: " + time);
	}

}
