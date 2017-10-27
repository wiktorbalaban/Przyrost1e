import java.util.Scanner;

import Querys.Queries;
import Querys.QueryJob1;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("OK");
        Queries queries = new Queries("SQLQueryData/odp.txt");
        queries.startSchedule();

//        Scheduler scheduler=null;
//
//        try {
//            // Grab the Scheduler instance from the Factory
//            scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//            // and start it off
//            scheduler.start();
//
//            // define the job and tie it to our HelloJob class
//            JobDetail job = newJob(QueryJob1.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            // Trigger the job to run now, and then repeat every 1 seconds 0,30 * * ? * * *
//            Trigger trigger = newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .startNow()
//                    //.withSchedule(cronSchedule("0,30 * * ? * * *"))
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(1)
//                            .repeatForever())
//                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
//
//        } catch (SchedulerException se) {
//            se.printStackTrace();
//        }

        while (true) {
            System.out.println("Podaj numer zadania:");
            Scanner scanner = new Scanner(System.in);
            int zadNr = Integer.parseInt( scanner.nextLine());
            System.out.println("Podaj zapytanie:");
            String query=scanner.nextLine();

            if(queries.addQuery(zadNr,query)==false){
                System.err.println("Błędne zapytanie!");
            }
            System.out.println("Kontynuować?[y/n]");
            if(scanner.nextLine().equals("n"))break;
        }
        queries.stopSchedule();
//        try {
//                scheduler.shutdown();
//            } catch (SchedulerException se) {
//                se.printStackTrace();
//            }
        }
}
