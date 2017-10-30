package TimeTo;

import java.time.Clock;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeTo {

    public static void main(String[] args) {

        LocalTime localTime=LocalTime.now(Clock.systemDefaultZone());

        LocalTime[] hoursOfClassesAndBreaks={
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

       System.out.println(localTime.getHour()+" "+localTime.getMinute()+" "+localTime.getSecond());

       int index=0;
       for (int i=0; i<hoursOfClassesAndBreaks.length;i++){
           if(localTime.isBefore(hoursOfClassesAndBreaks[i])){
               String tmp="";
               tmp=i%2==0?"następnych zajęć":"do następnej przerwy";
               System.out.println("Pozostało: "+MINUTES.between(localTime,hoursOfClassesAndBreaks[i])
               +" minut "+tmp);
               break;
           }
       }

       TimeTo2 timeTo2 = new TimeTo2();
        timeTo2.start();

        try {
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeTo2.stop();
//
//        LocalTime l1 = LocalTime.parse("02:53:40");
//        LocalTime l2 = LocalTime.parse("02:54:27");
//        System.out.println(l1.until(l2, SECONDS));
//        System.out.println(SECONDS.between(l1, l2));
    }

}
