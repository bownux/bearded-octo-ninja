package kata;

import static org.junit.Assert.*;

import org.junit.Test;

public class WhenFindingClosestToZero {

	@Test
	public void findClosestAmongNonZeros() {
		assertEquals(2, new ClosestToZeroFinder(new int[] {-3, 2, 5}).find());
	}
	
	@Test
	public void findZero() {
		assertEquals(0, new ClosestToZeroFinder(new int[] {-3, 0, 5}).find());
	}
	
	@Test
	public void preferPositive_WithoutRegardToPosition() {
		assertEquals(1, new ClosestToZeroFinder(new int[] {1,-1}).find());
		assertEquals(1, new ClosestToZeroFinder(new int[] {-1,1}).find());
	}
	
	@Test
	public void throwIllegalArgumentException_IfInputArrayIsNull() throws Exception {
		try {
			new ClosestToZeroFinder(null).find();
			fail("Excepted IllegalArgumentException");
		} catch (IllegalArgumentException expected) {}

	}
	
	@Test
	public void throwIllegalArgumentException_IfInputArrayIsEmpty() throws Exception {
		try {
			new ClosestToZeroFinder(new int[]{}).find();
			fail("Excepted IllegalArgumentException");
		} catch (IllegalArgumentException expected) {}

	}
}
