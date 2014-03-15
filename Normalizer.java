import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

public class Normalizer
{
	public static void main(String args[])
	{
		int[] values = {
			45, 0, 1234, 31, 492, 94, 1000, 128, 843, 7
		};

		Arrays.sort(values);

		int highest = values[values.length - 1];
		int lowest = 0;

		System.out.println("Highest: " + highest + ", Lowest: " + lowest);

		ArrayList<Float> normalizedValues = new ArrayList<Float>();

		for ( int i = 0; i < values.length; i++ ) {
			float normalized = ( (float)values[i] - lowest ) / ( highest - lowest );
			normalizedValues.add(new Float(normalized));

			System.out.println("* " + normalized);
		}
	}
}