package RansomeWare;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class Scheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void function() {
        final Runnable test = new Runnable() {
            @Override
            public void run() {
                System.out.println("Test");
            }
        };
        final ScheduledFuture<?> handler = scheduler.scheduleAtFixedRate(test, 10,10,SECONDS);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, 60*60, SECONDS);
    }

}
