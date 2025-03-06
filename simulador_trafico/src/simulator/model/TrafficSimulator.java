package simulator.model;

import java.util.PriorityQueue;
import java.util.Queue;

import org.json.JSONObject;

public class TrafficSimulator {
	private RoadMap _roadMap;
	private Queue<Event> _events;
	private int _time;

	public TrafficSimulator() {
		_roadMap = new RoadMap();
		_events = new PriorityQueue<>();
		_time = 0;
	}

	public void addEvent(Event e) throws IllegalArgumentException {
		if (e._time > this._time) {
			this._events.add(e); // Se ordena solo porque es PriorityQueue
		}

		else
			throw new IllegalArgumentException("ERROR: the event time should be bigger than de current time");

	}

	public void advance() {
		this._time++;
		while (!_events.isEmpty() && _events.peek()._time == _time) {
			_events.poll().execute(_roadMap);
		}

		for (Junction j : _roadMap.getJunctions()) {
			j.advance(_time);
		}

		for (Road r : _roadMap.getRoads()) {
			r.advance(_time);
		}
	}

	public void reset() {
		_roadMap.reset();
		_events.clear();
		_time = 0;
	}

	public JSONObject report() {
		JSONObject report = new JSONObject();
		report.put("time", this._time);
		report.put("state", this._roadMap.report());
		return report;
	}
}
