import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileBufferExample {
    public static void main(String[] args) {
        String filePath = "temp.txt";
        File file = new File(filePath);

        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while((line = bufferedReader.readLine())!=null)
            {
                System.out.println(line);
            }
            bufferedReader.close();

        }
        catch(IOException e){
            System.out.print("Error" + e);
            
        }
    }
    
}
