//package hashingAndDocumentation;
/**
 * SongProgram.java
 * @author Kanemoto
 * @since 11/12/24
 * Fetches a song based on an ID from a hash map.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SongProgram {


    //Hashmap to store SongRecords with the sing's ID as the key

    private HashMap<String, SongRecord> songMap;

    // Constructor
    public SongProgram() {
        songMap = new HashMap<>();
    }

    /**
     * Method to load songs from a CSV file using aMethod to load songs from a CSV file buffered reader
     * Try method inside to catch exceptions related to file reading
     * 
     * Precondition: filePath is a valid file with .csv extension
     * Postcondition: Song information loaded from .csv file into a hashmap
     * @param filePath path of the file to be read from
     */
    public void loadSongsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            //read in first line and do nothing with it
            br.readLine();
            
            while ((line = br.readLine()) != null) {
            	
            	//System.out.println(line);//for testing
                // Create a SongRecord from the line and add it to the map
                SongRecord song = new SongRecord(line);
                songMap.put(song.getId(), song);
            }
            System.out.println("Songs successfully loaded from CSV.");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Precondition: ID is a string
     * Postcondtion: Result is either a song, or null
     * @param id id of the song to fetch
     * Gets a song based on its associated ID.  Returns null if ID has no match.
    */
    public SongRecord getSongById(String id) {
        return songMap.get(id);
    }

    /**
     * Precondition: SongMap exists
     * Postcondition: n/a
     * Method to print all songs (for debugging or display purposes)
     */

    public void printAllSongs() {
        for (SongRecord song : songMap.values()) {
            System.out.println(song);
        }
    }
    
    /**
     * Precondition: have software that supports GUI
     * Postcondition: Creates GUI window using JFrame
     * GUI method to search for a song by ID
     */
    public void openSearchGui() {
        // Create the main frame
        JFrame frame = new JFrame("Song Lookup");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold input and button
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Label, Text Field, and Button
        JLabel label = new JLabel("Enter Song ID:");
        JTextField idField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Add label, text field, and button to panel
        panel.add(label);
        panel.add(idField);
        panel.add(searchButton);

        // Result area to display song details
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                SongRecord song = getSongById(id);
                if (song != null) {
                    resultArea.setText("Song Found:\n" + song.toString());
                } else {
                    resultArea.setText("Song with ID " + id + " not found.");
                }
            }
        });

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Precondition: Have software that supports JFrame and Window Maker
     * Postcondition: Creates a window that supports functionality within the program.
     * Alternate main method to demonstrate functionality and open GUI
     */
    public static void main2(String[] args) {
        SongProgram program = new SongProgram();

        // Load songs from a CSV file
        String filePath = "data.csv";  // replace with actual file path
        program.loadSongsFromCSV(filePath);

        // Open GUI for searching songs by ID
        program.openSearchGui();
    }

    /**
     * Precondition: n/a
     * Postcondition: Test example song is output in the console
     * Main method to demonstrate functionality
     */
    public static void main(String[] args) {
        SongProgram program = new SongProgram();

        // Load songs from a CSV file
        String filePath = "data.csv";  // replace with actual file path
        program.loadSongsFromCSV(filePath);

        // Demonstrate retrieving a song by ID
        String testId = "4BJqT0PrAfrxzMOxytFOIz";  // replace with an actual ID from your file
        SongRecord song = program.getSongById(testId);
        if (song != null) {
            System.out.println("Retrieved song: " + song);
        } else {
            System.out.println("Song with ID " + testId + " not found.");
        }

        // Print all songs
        // program.printAllSongs();
    }
}

