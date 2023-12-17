import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite
{
    public static void main(String[] args) throws IOException {
        File file = new File("Database.txt");

        file.createNewFile();

        //FileWriter object to write to a file
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Hello \nWorld");    
        fileWriter.flush();  
        fileWriter.close();

        FileReader fileReader = new FileReader(file);
        char [] ch = new char[1000];

        fileReader.read(ch);

        for(char i: ch){
            System.out.print(i);
        }
        fileReader.close();

    }
}