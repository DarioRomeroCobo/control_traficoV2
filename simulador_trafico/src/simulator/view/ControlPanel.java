package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {
	
	private Controller _ctrl;
	
	ControlPanel(Controller ctrl){
		this._ctrl = ctrl;
		
		  JButton openButton = new JButton("Abrir fichero de eventos");

	        openButton.addActionListener(new ActionListener() {
	        	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            
	                JFileChooser fileChooser = new JFileChooser();
	                
	                File file=fileChooser.getSelectedFile();
	                
	                try (FileInputStream fis = new FileInputStream(file)){
	                
	                		
	                _ctrl.reset();
	                _ctrl.loadEvents(fis);
	                	
	                	
	                	
	                }catch(IllegalArgumentException ex) {
	                	JOptionPane.showMessageDialog(openButton, ex.getMessage());
	                	
	                }
	            }
	        }
	}

	
	
	
	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
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
