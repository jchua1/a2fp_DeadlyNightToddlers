import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tester {

    private ArrayList<Card> cards;
    
    public Tester() {
	cards = new ArrayList<Card>();
    }
    
    public static void main(String[] args) {
	try {
	    FileReader reader = new FileReader("Test.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    while ((line = br.readLine()) != null) {
		System.out.println(line);
	    }
	    reader.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
