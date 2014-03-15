import java.lang.*;
import java.util.*;

public class Artist
{
	private String name;
	private ArrayList<String> playedGenres;

	public Artist(String name)
	{
		setName(name);
		playedGenres = new ArrayList<String>();
	}

	private void setName(String name)
	{
		/* In the actual site, this would be the 'refname' or server-friendly ID name of the
		 * artist. Changing this would mess things up and break links on past event pages.
		 * Actual website/DB will have seperate 'Display Name' that can be changed. */
		this.name = name;
	}

	public String getName() { return name; }

	/**
	 * Add a genre to the list of what the artist plays at the very end (least often played.)
	 */
	public void addPlayedGenre(String name) { this.addPlayedGenre(name, -1); }

	/**
	 * Add a genre to the list of what the artist plays at a certain position.
	 */
	public void addPlayedGenre(String name, int position)
	{
		// TODO :: Check to see if genre already added, if so then just change its place in list
		// TODO :: Force these to match a defined table of genre names for data consistency.

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

	public int getNumPlayedGenres() { return playedGenres.size(); }

	public ArrayList<String> getListOfPlayedGenres() { return playedGenres; }

	/**
	 * Normalize values across even scale from 0.0 to 1.0 and return as HashMap (with genre names.)
	 */
	public HashMap<String, Float> getNormalizedGenres()
	{
		/* This works for what events must calculate (% of each genre weight across lineup),
		 * but artists need to be able to define % of roughly how much they play each genre,
		 * and then calculate from that. Events must get an event and fair genre weight score
		 * for artists and using this hierarchical algorithm is not at all accurate enough. */

		// TODO :: Move this into Event class when written, and rewrite artist algorithm.
		if (! playedGenres.isEmpty())
		{
			HashMap<String, Float> normalized = new HashMap<String, Float>(playedGenres.size());
			int numGenres = playedGenres.size();
			float normalRangeMin = 0.0f;
			float normalRangeMax = 1.0f;

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

	/**
	 * Returns out a textual representation of what genres the artist plays.
	 */
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