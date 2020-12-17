import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Boggle {
	private char[][] gridLetters = new char[4][4];
	private boolean[][] currentGridChecked = new boolean[4][4];
	private TreeSet<String> possibleWords = new TreeSet<String>();
	private TreeSet<String> dictionary = new TreeSet<String>();

	Boggle() throws FileNotFoundException {
		Random rand = new Random();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				this.gridLetters[y][x] = (char) ((rand.nextInt(122 - 97) + 97));
			}
		}
		Scanner dictionaryReader = new Scanner(new File("dictionary.txt"));
		while (dictionaryReader.hasNext()) {
			String current = dictionaryReader.next();
			if (current.length() >= 4) {
				dictionary.add(current);
			}
		}
		for (int y = 0; y < 4; y++) {
			System.out.println();
			for (int x = 0; x < 4; x++) {
				System.out.println(gridLetters[y][x] + " ");
			}
		}
		checkPossible(0, 0, "");
		System.out.println(possibleWords.toString());
	}

	public void checkPossible(int x, int y, String word) {
		word = word + gridLetters[y][x];
		if (y > 0 && currentGridChecked[y - 1][x] != true) {
			currentGridChecked[y - 1][x] = true;
			checkPossible(x, y - 1, word);
			currentGridChecked[y - 1][x] = false;
		}
		if (y < 3 && currentGridChecked[y + 1][x] != true) {
			currentGridChecked[y + 1][x] = true;
			checkPossible(x, y + 1, word);
			currentGridChecked[y + 1][x] = false;
		}
		if (x < 3 && currentGridChecked[y][x + 1] != true) {
			currentGridChecked[y][x + 1] = true;
			checkPossible(x + 1, y, word);
			currentGridChecked[y][x + 1] = false;
		}
		if (x > 0 && currentGridChecked[y][x - 1] != true) {
			currentGridChecked[y][x - 1] = true;
			checkPossible(x - 1, y, word);
			currentGridChecked[y][x - 1] = false;
		}
		if (x < 3 && y < 3 && currentGridChecked[y + 1][x + 1] != true) {
			currentGridChecked[y + 1][x + 1] = true;
			checkPossible(x + 1, y + 1, word);
			currentGridChecked[y + 1][x + 1] = false;
		}
		if (x < 3 && y > 0 && currentGridChecked[y - 1][x + 1] != true) {
			currentGridChecked[y - 1][x + 1] = true;
			checkPossible(x + 1, y - 1, word);
			currentGridChecked[y - 1][x + 1] = false;
		}
		if (x > 0 && y > 0 && currentGridChecked[y - 1][x - 1] != true) {
			currentGridChecked[y - 1][x - 1] = true;
			checkPossible(x - 1, y - 1, word);
			currentGridChecked[y - 1][x - 1] = false;
		}
		if (x > 0 && y < 3 && currentGridChecked[y + 1][x - 1] != true) {
			currentGridChecked[y + 1][x - 1] = true;
			checkPossible(x - 1, y + 1, word);
			currentGridChecked[y + 1][x - 1] = false;
		}
		if (dictionary.contains(word) && word.length() >= 4) {
			possibleWords.add(word);
		}
	}

}
