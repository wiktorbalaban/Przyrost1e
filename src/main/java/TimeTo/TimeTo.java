package TimeTo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class TimeTo {

    Scheduler scheduler;

    public void start() {
        try {
            // Grab the Scheduler instance from the Factory
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(TimeToJob.class)
                    .withIdentity("job2", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 1 seconds 0,30 * * ? * * *
            Trigger trigger = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startAt(dateOf(8, 15, 0))
                    .withSchedule(cronSchedule("0 * * ? * MON,TUE,WED,THU,FRI *"))
                    .endAt(dateOf(18, 45, 0))
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public void stop() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
