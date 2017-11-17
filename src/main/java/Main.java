import java.util.Scanner;

import Querys.Queries;
import TimeTo.TimeTo;
import org.apache.log4j.Logger;

import static org.quartz.JobBuilder.newJob;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("OK");
        Queries queries = new Queries("SQLQueryData/odp.txt");
        queries.startSchedule();

        TimeTo timeTo = new TimeTo();
        timeTo.start();

        while (true) {
            System.out.println("Podaj numer zadania:");
            Scanner scanner = new Scanner(System.in);
            int zadNr = Integer.parseInt(scanner.nextLine());
            System.out.println("Podaj zapytanie:");
            String query = scanner.nextLine();

            if (queries.addQuery(zadNr, query) == false) {
                System.err.println("Błędne zapytanie!");
            }
            System.out.println("Kontynuować?[y/n]");
            if (scanner.nextLine().equals("n")) break;
        }
        queries.stopSchedule();
        timeTo.stop();
    }
}
