package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent {

	public NewCityRoadEvent(int time, String id, String srcJunc, String destJunc, int length, int co2Limit,
			int maxSpeed, Weather weather) {
		super(time, id, srcJunc, destJunc, length, co2Limit, maxSpeed, weather);

	}

	@Override
	void execute(RoadMap map) {
		map.addRoad(new CityRoad(id, map.getJunction(srcJunc), map.getJunction(destJunc), maxSpeed, co2Limit, length,
				weather));

	}

	@Override
	public String toString() {
		return "New City Road '" + super.toString() + "'";
	}

}
