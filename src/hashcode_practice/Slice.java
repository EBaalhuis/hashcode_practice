package hashcode_practice;

import java.util.ArrayList;

public class Slice {
	public int id;
	public ArrayList<Cell> cells;
	
	Slice(int _id) {
		id = _id;
		cells = new ArrayList<>();
	}
	
	public void addCell(Cell c) {
		cells.add(c);
	}
	
	public void removeCell(Cell c) {
		cells.remove(c);
	}
	
	public boolean isLegal(int req, int maxCells) {
		if (maxCells < cells.size()) {
			return false;
		}
		int tCount = 0;
		for (Cell c : cells) {
			if (c.isT) {
				tCount++;
			}
		}
		return (tCount >= req && cells.size()-tCount >= req);
	}
	
	public String getLine() {
		int rMin = Integer.MAX_VALUE, rMax = 0, cMin = 0, cMax;
		
	}
}
