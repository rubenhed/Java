import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Edits the database and text file
 */
public class DatabaseEditor {
    Path path = Paths.get("movies.txt");
    Map<String, String> movieDatabase = new HashMap<String, String>();
    /**
     * Creates the database from a text file or creates the text file first if it doesn't exist
     */
    public void createDatabase() throws IOException {
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        addToDatabase();
    }

    /**
     * Adds data to the database
     */
    public void addToDatabase() throws IOException{
        List<String> allLines = Files.readAllLines(path);
        for(int i = movieDatabase.size()+1; i<allLines.size(); i+=2) {
            if(allLines.get(i).matches("1|2|3|4|5")){
                movieDatabase.put(allLines.get(i-1),allLines.get(i));
            }
        }
    }

    /**
     * Gets title from database
     * @param title Title of movie
     */
    public void getTitle(String title){
        for(String key : movieDatabase.keySet()){
            for(int i = 0; i<key.length(); i++){
                if(title.length()+i <= key.length()){
                    if(key.substring(i,title.length()+i).equals(title)){
                        output(key,movieDatabase.get(key));
                        break;
                    }
                }else break;
            }
        }
    }

    /**
     * Gets review score from database
     * @param review Review score of movie
     */
    public void getReviewScore(int review){
        for(Map.Entry<String,String> entry : movieDatabase.entrySet()){
            if(review <= Integer.parseInt(entry.getValue())){
                output(entry.getKey(),entry.getValue());
            }
        }
    }

    /**
     * Adds new movie to database and text file
     * @param title Title of movie
     * @param reviewScore Review score of movie
     * @throws IOException
     */
    public void newMovie(String title,int reviewScore)throws IOException{
        List<String> lines = new ArrayList<String>();
        lines.add(title);
        lines.add(Integer.toString(reviewScore));
        Files.write(path,lines, StandardOpenOption.APPEND);
        addToDatabase();
    }
    /**
     * Creates the output which shows relevant information
     * @param title title of movie
     * @param score score of movie
     * @return sentence of information
     */
    private static void output(String title,String score){
        System.out.println("\nTitle: " + title + "\nScore: " + score + "/5");
    }
}
