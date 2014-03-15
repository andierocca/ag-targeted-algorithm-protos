import java.lang.*;
import java.util.*;

public class Artist
{
	public String name;
	private ArrayList<String> playedGenres;

	public Artist(String name)
	{
		this.name = name;

		playedGenres = new ArrayList<String>();
	}

	public void addPlayedGenre(String name)
	{
		// Add at end by default
		this.addPlayedGenre(name, -1);
	}

	public void addPlayedGenre(String name, int position)
	{
		// TODO :: Check to see if genre already added, if so then just change its place in list

		if (position == 0)
		{
			// Add at beginning
			playedGenres.add(0, name);
		} else if (position < 0 || position >= playedGenres.size()) {
			// Add at end
			playedGenres.add(name);
		} else {
			// Add at position and push others down one level
			playedGenres.add(position, name);
		}
	}

	public void removeGenre(int position)
	{
		if (position >= 0 && position < playedGenres.size())
		{
			playedGenres.remove(position);
		} else {
			System.out.println("! Error: Artist.removeGenre() given position out of bounds.");
		}
	}

	public int getNumplayedGenres()
	{
		return playedGenres.size();
	}

	public HashMap<String, Float> getNormalizedGenres()
	{
		if (! playedGenres.isEmpty())
		{
			HashMap<String, Float> normalized = new HashMap<String, Float>(playedGenres.size());
			int numGenres = playedGenres.size();
			float normalRangeMin = 1.0f;
			float normalRangeMax = 10.0f;

			System.out.println("> Normalizing " + this.name + "'s genre specialization weight ("
				+ normalRangeMin + " to " + normalRangeMax + "):");

			for (int i = 0; i < playedGenres.size(); i++)
			{
				// Inverse value of index (e.g. top genre's score = # of genres, bottom genre = 1)
				int thisScore = numGenres - i;

				float normalizedValue = (float)(thisScore - 1) / (numGenres - 1);

				normalized.put(playedGenres.get(i), new Float(normalizedValue));

				System.out.println("   - " + playedGenres.get(i) + ": " + normalizedValue
								   + ( (i != numGenres - 1) ? "," : "" ));
			}

			return normalized;
		} else {
			System.out.println("! Error: Artist.getNormalizedGenres() no genres have been set.");
			return null;
		}
	}

	public String toString()
	{
		int numGenres = playedGenres.size();
		String output = "$ Artist " + name + " plays:\n";

		for (int i = 0; i < numGenres; i++)
		{
			String thisLine = "   - #" + (i + 1) + ": "
							+ playedGenres.get(i)
				   			+ ( (i != numGenres - 1) ? ",\n" : "" );
			output = output + thisLine;
		}

		return output;
	}
}