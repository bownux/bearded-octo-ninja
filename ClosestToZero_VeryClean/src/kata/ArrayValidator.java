package kata;

public class ArrayValidator {
	public void validateInput(int[] candidates) {
		if (candidates == null || candidates.length == 0) throw new IllegalArgumentException("Invalid input");
	}
}
