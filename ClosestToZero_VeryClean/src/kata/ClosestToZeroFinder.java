package kata;

public class ClosestToZeroFinder {
	private final int[] candidates;
	
	public ClosestToZeroFinder(int[] candidates) {
		new ArrayValidator().validateInput(candidates);
		this.candidates = candidates;
	}

	public int find() {
		int closestCandidate = candidates[0];
		
		for (int currentCandidate : candidates) {
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
