package TimeTo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Clock;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeToJob implements org.quartz.Job {

    static LocalTime[] hoursOfClassesAndBreaks={
            LocalTime.parse("08:15:00"),
            LocalTime.parse("09:00:00"),
            LocalTime.parse("09:05:00"),
            LocalTime.parse("09:50:00"),
            LocalTime.parse("10:00:00"),
            LocalTime.parse("10:45:00"),
            LocalTime.parse("10:50:00"),
            LocalTime.parse("11:35:00"),
            LocalTime.parse("11:45:00"),
            LocalTime.parse("12:30:00"),
            LocalTime.parse("12:35:00"),
            LocalTime.parse("13:20:00"),
            LocalTime.parse("13:45:00"),
            LocalTime.parse("14:35:00"),
            LocalTime.parse("15:20:00"),
            LocalTime.parse("15:30:00"),
            LocalTime.parse("16:15:00"),
            LocalTime.parse("16:20:00"),
            LocalTime.parse("17:05:00"),
            LocalTime.parse("17:15:00"),
            LocalTime.parse("18:00:00"),
            LocalTime.parse("18:05:00"),
            LocalTime.parse("18:50:00")
    };

    public TimeToJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        LocalTime localTime=LocalTime.now(Clock.systemDefaultZone());
        for (int i=0; i<hoursOfClassesAndBreaks.length;i++){
            if(localTime.isBefore(hoursOfClassesAndBreaks[i])){
//                 Ladniejszy out, lae niezgodyny ze specyfikacją
//                String tmp="";
//                tmp=i%2==0?"następnych zajęć":"do następnej przerwy";
//                System.out.println("Pozostało: "+MINUTES.between(localTime,hoursOfClassesAndBreaks[i])
//                        +" minut "+tmp);
                System.out.println( MINUTES.between(localTime,hoursOfClassesAndBreaks[i])
                        +" minut do końca zajęć/przeryw");
                break;
            }
        }
    }
}
