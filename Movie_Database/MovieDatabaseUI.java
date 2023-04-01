import java.io.IOException;
import java.util.*;

/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
    public DatabaseEditor dbe;
    Scanner _scanner;
    MovieDatabaseUI(){
        dbe = new DatabaseEditor();
    }
    /**
     * Start the movie database UI.
     */
    public void startUI() throws IOException {
        dbe.createDatabase();

        _scanner = new Scanner(System.in);
        int input;
        boolean quit = false;
        System.out.println("** MOVIE DATABASE **");
        while(!quit) {
            input = getNumberInput(_scanner, 1, 4, getMainMenu());

            switch(input) {
                case 1: searchTitle(); break;
                case 2: searchReviewScore(); break;
                case 3: addMovie(); break;
                case 4: quit = true;
            }
        }
        //Close scanner to free resources
        _scanner.close();
    }
    /**
     * Get input and translate it to a valid number.
     *
     * @param scanner the Scanner we use to get inputx
     * @param min the lowest correct number
     * @param max the highest correct number
     * @param message message to user
     * @return the chosen menu number
     */
    private int getNumberInput(Scanner scanner, int min, int max, String message) {
        int input = -1;

        while(input < 0) {
            System.out.println(message);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            }
            catch(NumberFormatException nfe) {
                input = -1;
            }
            if(input < min || input > max) {
                System.out.println("Invalid input.");
                input = -1;
            }
        }
        return input;
    }
    /**
     * Get search string from user, search title in the movie
     * database and present the search result.
     */
    private void searchTitle() {
        System.out.print("Enter search word: \n");
        String title = _scanner.nextLine().trim();
        dbe.getTitle(title);
    }
    /**
     * Get search string from user, search review score in the movie
     * database and present the search result.
     */
    private void searchReviewScore() {
            int review = getNumberInput(_scanner, 1, 5, "Enter minimum score (1 - 5): ");
        dbe.getReviewScore(review);
    }
    /**
     * Get information from user on the new movie and add
     * it to the database.
     */
    private void addMovie()throws IOException{
        System.out.print("Title: ");
        String title = _scanner.nextLine().trim();
        int reviewScore = getNumberInput(_scanner, 1, 5, "Score (1 - 5): ");
        dbe.newMovie(title,reviewScore);
    }
    /**
     * Return the main menu text.
     *
     * @return the main menu text
     */
    private String getMainMenu() {
        return  "-------------------\n" +
                "1. Search by title\n" +
                "2. Search by score\n" +
                "3. Add movie\n" +
                "-------------------\n" +
                "4. Quit";
    }
}