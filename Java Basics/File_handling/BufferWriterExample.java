import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriterExample {
    public static void main(String[] args) {
        String filePath = "test.txt";
        File file = new File(filePath);
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Write data to file
            bufferedWriter.write("Hello");
            bufferedWriter.newLine();
            bufferedWriter.write("World");
            bufferedWriter.close();

        }
        catch(IOException  e){
            System.out.println("Error" + e);
        }
    }
}
