package RansomeWare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Calendar;

public class FileScore {

    HashMap<String, Double> FileScore = new HashMap<>();

    public void FileScore() throws Exception {
        File in = new File(System.getProperty("user.home") + File.separator + "output.txt");
        File out = new File(System.getProperty("user.home") + File.separator + "files.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(in))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] lineIn = line.split(",\\s* ");
                System.out.println(lineIn.length);
            }
        }
    }






    public double calculateBeta(File file) throws Exception {

        long lastModified = file.lastModified();
        Date lastModDate = new Date(lastModified);

        //recency
        //working out how to score it
        long currentTimeMillis = System.currentTimeMillis();

        Date BUCKET_1 = new Date(currentTimeMillis- TimeUnit.DAYS.toMillis(1));
        Date BUCKET_2 = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(5));
        Date BUCKET_3 = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(10));
        Date BUCKET_4 = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(30));
        Date BUCKET_5 = new Date(currentTimeMillis - TimeUnit.DAYS.toMillis(90));

        double BUCKET_1_SCORE = 1;
        double BUCKET_2_SCORE = 0.9;
        double BUCKET_3_SCORE = 0.7;
        double BUCKET_4_SCORE = 0.4;
        double BUCKET_5_SCORE = 0.1;

        double p = (lastModDate.after(BUCKET_1)) ? BUCKET_1_SCORE:
                (lastModDate.after(BUCKET_2)) ? BUCKET_2_SCORE:
                        (lastModDate.after(BUCKET_3)) ? BUCKET_3_SCORE:
                                (lastModDate.after(BUCKET_4)) ? BUCKET_4_SCORE:
                                        (lastModDate.after(BUCKET_5)) ? BUCKET_5_SCORE: 0.0;

        //calculate frequency
        //a = time since last accessed in hrs
        long hoursDiff = (currentTimeMillis - lastModDate.getTime())/1000*60*60;
        double a = (double) hoursDiff;

        //the decay constant is ln2/
        double lambda = Math.log(2) / 30;

        double beta = p * Math.exp(-a * lambda);

        return beta;
    }

  /*  public double FileScore(File file, double gamma) throws Exception {
        if(gamma == 0) return calculateBeta(file);

        //already had a beta score, add new one to it
        double newBeta = calculateBeta(file);
        return gamma + newBeta;

    }*/
}
