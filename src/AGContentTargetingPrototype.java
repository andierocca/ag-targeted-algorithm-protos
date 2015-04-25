import java.util.*;
import java.lang.*;

public class AGContentTargetingPrototype
{
	public static void main(String args[])
	{
		/*
		int[] values = { 45, 0, 1234, 31, 492, 94, 1000, 128, 843, 7 };
		ArrayList<Float> normalizedInts = getNormalizedValues(values);
		*/

		/* Create a test artist, give them some genres that they play from most
		 * frequent to least frequent, and then get the normalized values. */
		Artist artist = new Artist("Sensient");
		artist.addPlayedGenre("Progressive Psytrance");
		artist.addPlayedGenre("Minimal Techno");
		artist.addPlayedGenre("Trance");
		artist.addPlayedGenre("Ambient");
		//having a stroke (testing out spelling corrections)
		//hard acid trance
		artist.addPlayedGenre("Harf acid transe");
		//deep house
		artist.addPlayedGenre("derp hoz");
		//hard mode: downtempo
		artist.addPlayedGenre("owgtemurrru");


		System.out.println(artist); // Test the toString() method.

		// TODO :: Create new algorithm using artist-defined weights, but this one works for events.
		HashMap<String, Float> normalized = artist.getNormalizedGenres();
	}

	/**
	 * Takes an array of ints, sorts it, and returns the evenly-normalized values
	 * on a scale from 0.0f to 1.0f in an ArrayList of Float objects.
	 */
	public static ArrayList<Float> getNormalizedValues(int[] srcValues)
	{
		// Sorts in reverse order of what I want... but works for basic test/example
		Arrays.sort(srcValues);

		int numValues = srcValues.length;
		int highest = srcValues[srcValues.length - 1];
		int lowest = 0;

		System.out.println("> Normalizing array values:");
		System.out.println("  Highest: " + highest + ", Lowest: " + lowest);

		ArrayList<Float> normalizedValues = new ArrayList<Float>();

		for ( int i = 0; i < numValues; i++ )
		{
			float normalized = ( (float)srcValues[i] - lowest ) / ( highest - lowest );
			// Kind of hacky... add in at position 0 to reverse the order (1.0 first, 0.0 last)
			normalizedValues.add(0, new Float(normalized));

		}

		// Loop again (!?) to print in the right order... again, works for quick example
		for ( int i = 0; i < numValues; i++ )
		{
			System.out.println("   - " + normalizedValues.get(i));
		}

		return normalizedValues;
	}
}