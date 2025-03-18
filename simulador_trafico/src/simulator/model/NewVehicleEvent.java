package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
	String id;
	int maxSpeed;
	int contClass;
	List<String> itinerary;

	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		super(time);
		// put the different values in fields
		this.id = id;
		this.maxSpeed = maxSpeed;
		this.contClass = contClass;
		this.itinerary = itinerary;
	}

	private List<Junction> convertListToJunction(RoadMap map) {
		List<Junction> listJunct = new ArrayList<>();
		for (String s : itinerary) {
			listJunct.add(map.getJunction(s));
		}
		return listJunct;
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		Vehicle v = new Vehicle(id, maxSpeed, contClass, convertListToJunction(map));
		map.addVehicle(v);
		v.moveToNextRoad();
	}

	@Override
	public String toString() {

		return "New Vehicle " + id + " Max Speed " + maxSpeed + " Cont Class " + contClass + " Itinerary " + itinerary;
	}

}
