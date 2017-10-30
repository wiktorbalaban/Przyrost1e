package TimeTo;

import Querys.QueryJob1;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
public class TimeTo2 {
    public void TimeToSchedule(String path){
    }

    Scheduler scheduler;

    public void start(){
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
                    .startNow()
                    //.withSchedule(cronSchedule("0 * 8-19 ? * MON,TUE,WED,THU,FRI *"))
                    .withSchedule(cronSchedule("0 * 8-19 ? * * *"))
//                        .withSchedule(simpleSchedule()
//                                .withIntervalInSeconds(1)
//                                .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public void stop(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
