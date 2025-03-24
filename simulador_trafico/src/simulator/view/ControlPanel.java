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

	ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		initGui();

	}

	private void initGui(){
		
		JButton fileButton = new JButton("Abrir fichero de eventos");

		fileButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
         
                JFileChooser fileChooser = new JFileChooser();
                
                if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                	 File file = fileChooser.getSelectedFile();
                
                	 try( FileInputStream input = new FileInputStream(file)){
                		
                		 _ctrl.reset();
                		 _ctrl.loadEvents(input);
                	 }
                	 
                	 catch(IllegalArgumentException ex) {
                			JOptionPane.showMessageDialog(fileButton, ex.getMessage());
                	
                	 } catch (FileNotFoundException f) {
						
                		 JOptionPane.showMessageDialog(fileButton, f.getMessage());
					}
                	 catch (Exception ex) {
                         JOptionPane.showMessageDialog(fileButton, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                     }
                	 
                	 
                }
                
            }
	
        });
        this.add(fileButton);
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
