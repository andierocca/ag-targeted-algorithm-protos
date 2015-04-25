import java.util.List;

/**
 * This computes the minimum edit distance and gives feedback about the edits of two strings.
 * It is a very basic algorithm using Levenshtein's method of equating substitution of characters
 * to 2 edits and insertion or deletion of a character to be 1 edit.
 * 
 * @author Andie Rocca
 * @version 1.0
 */
public class EditDistance
{
    /**
     * Computes edit distance for 2 strings
     * @param x the first String
     * @param y the second String
     * @return the edit distance between the 2 strings
     */
    public static int computeDistance(String x, String y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("One of the strings passed was null");
        }
        int[][] table = new int[x.length()][y.length()];
        //fill table with initial values
        for (int i = 0; i < table.length; i++) {
            table[i][0] = i;
        }
        for (int i = 0; i < table[0].length; i++) {
            table[0][i] = i;
        }
        
        int minDistance; //min distance between insertion, deletion, and substitution
        int sub; //will be a 0 or a 2 based on whether the characters are the same
        for (int i = 1; i < x.length(); i++) {
            for (int j = 1; j < y.length(); j++) {
                sub = Character.toLowerCase(x.charAt(i)) == Character.toLowerCase(y.charAt(j)) ? 0 : 2;
                minDistance = Math.min(table[i-1][j-1] + sub, 
                    Math.min(table[i-1][j] + 1, table[i][j-1] + 1));
                table[i][j] = minDistance;
            }
        }
        
        return table[table.length - 1][table[0].length - 1];
    }

    /**
     * Finds the closest match of a String in a list of Strings
     * @param list the dictionary so to speak of strings to compare
     * @param str the String to find the closest match to
     * @return which string in the list was the closest match to str
     */
    public static String findClosestMatch(List<String> list, String str) {
        if (list == null || str == null) {
            throw new IllegalArgumentException("Either the dictionary or string passed was null");
        }
        int minDistance = Integer.MAX_VALUE;
        String closestMatch = str;
        int distance;
        for (String s : list) {
            distance = computeDistance(s, str);
            if (distance < minDistance) {
                closestMatch = s;
                minDistance = distance;
            }
        }
        return closestMatch;
    }
}
