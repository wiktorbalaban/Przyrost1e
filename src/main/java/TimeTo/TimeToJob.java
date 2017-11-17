package TimeTo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Clock;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeToJob implements org.quartz.Job {

    static LocalTime[] hoursOfClassesAndBreaks = {
            LocalTime.parse("08:15:00"),
            LocalTime.parse("09:45:00"),
            LocalTime.parse("10:00:00"),
            LocalTime.parse("11:30:00"),
            LocalTime.parse("11:45:00"),
            LocalTime.parse("13:15:00"),
            LocalTime.parse("13:45:00"),
            LocalTime.parse("15:15:00"),
            LocalTime.parse("15:30:00"),
            LocalTime.parse("17:00:00"),
            LocalTime.parse("17:15:00"),
            LocalTime.parse("18:45:00")
    };

    public TimeToJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        String message = getStringTmp(LocalTime.now(Clock.systemDefaultZone()));
        if (message != null)
            System.out.println(message);
    }

    public String getStringTmp(LocalTime localTime) {
        String result = null;
        for (int i = 0; i < hoursOfClassesAndBreaks.length; i++) {
            if (localTime.isBefore(hoursOfClassesAndBreaks[i])) {
                String tmp = i % 2 == 0 ? "przerwy" : "zajęć";
                result = MINUTES.between(localTime, hoursOfClassesAndBreaks[i])
                        + " minut do końca " + tmp;
                break;
            }
        }
        return result;
    }

}
