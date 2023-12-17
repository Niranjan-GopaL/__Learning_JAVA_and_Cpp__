import java.io.File;
import java.io.IOException;
public class FileExample {
    public static void main(String args[]) throws IOException{
        String filePath = "example.txt";
        //Create file object
        File file = new File(filePath);

        if(file.exists()){
            System.out.println("File Exist");
            System.out.println("File Absolute Path"+file.getAbsolutePath());
            System.out.println(("File Name: "+ file.getName()));
            System.out.println("File path: "+ file.getPath());
            System.out.println("Is file: "+ file.isFile());
            System.out.println("File size: " + file.length() + " bytes");
            //rename file by creating another file object
            String newFileName = "newexample.txt";

            File renameFile = new File(newFileName);
            file.renameTo(renameFile);
            //Delete a File
            if(renameFile.delete())
                System.out.println("File delete");
            else
                System.out.println("File is not deleted");

        }
        File testFile = new File("testfile.txt");
        testFile.createNewFile();
    }
    
}
