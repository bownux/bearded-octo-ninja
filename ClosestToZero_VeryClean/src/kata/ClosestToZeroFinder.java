package kata;

public class ClosestToZeroFinder {
	private final int[] candidates;
	private int closestCandidate;
	private int currentCandidate;
	
	public ClosestToZeroFinder(int[] candidates) {
		this.candidates = candidates;
		new ArrayValidator().validateInput(candidates);
		closestCandidate = candidates[0];
	}

	public int find() {
		for (int candidate : candidates) {
			currentCandidate = candidate;
			
			closestCandidate = closerOf(closestCandidate, currentCandidate);
			closestCandidate = preferPositiveIfBothSameDistance(closestCandidate,currentCandidate);
		}
		
		return closestCandidate;
	}

	private int closerOf(int closest, int currentCandidate) {
		return distance(currentCandidate) < distance(closest) ? currentCandidate : closest;
	}
	
	private int preferPositiveIfBothSameDistance(int closest,int currentCandidate) {
		if (sameDistance(closest, currentCandidate)) {
			closest = whicheverIsPositive(closest, currentCandidate);
		}
		return closest;
	}

	private boolean sameDistance(int closest, int currentCandidate) {
		return distance(currentCandidate) == distance(closest);
	}

	private int whicheverIsPositive(int closest, int currentCandidate) {
		return currentCandidate > 0 ? currentCandidate : closest;
	}

	private int distance(int value) {
		return Math.abs(value);
	}
}
