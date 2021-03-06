package Querys;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

import Querys.Query;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Queries {
    final static Logger logger = Logger.getLogger(Queries.class);
    private String pathToSave;
    QueryScheduler  queryScheduler=new QueryScheduler();

    public static ArrayList<Query> queryList;

    public Queries(String pathToSave){
        this.pathToSave=pathToSave;
        queryList=new ArrayList<Query>();
    }
    public Queries(){
    }

    public boolean addQuery(int nr, String data){
        boolean result=false;

        String tmpData=data.toUpperCase();
        int select = tmpData.indexOf("SELECT");
        int from = tmpData.indexOf("FROM");
        int where = tmpData.indexOf("WHERE");
        int orderBy = tmpData.indexOf("ORDER BY");

        int tmp=0;
        if(select!=-1){
            tmp=select;
        }
        else {
            tmp=-1;
        }
        if(from!=-1){
            if(from>tmp)tmp=from;
            else return result;
        }
        if(where!=-1){
            if(where>tmp)tmp=where;
            else return result;
        }
        if(orderBy!=-1){
            if(orderBy>tmp);
            else return result;
        }

        result=true;

        for (int i =0; i<queryList.size();i++ ) {
            if(queryList.get(i).getNr()==nr){
                queryList.set(i,new Query(nr,data));
                return result;
            }
        }

        queryList.add(new Query(nr,data));

        return result;
    }

    public void saveListToFile(){
        try {
            PrintWriter writer = new PrintWriter(pathToSave, "UTF-8");
            
            Collections.sort(queryList, new Comparator<Query>(){
                public int compare(Query o1, Query o2){
                    if(o1.getNr() == o2.getNr())
                        return 0;
                    return o1.getNr() < o2.getNr() ? -1 : 1;
                }
            });

            for (Query query: queryList
                 ) {
                writer.println("Zad "+query.getNr());
                writer.println(query.getData());
                writer.println();
            }

            writer.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch (UnsupportedEncodingException e1){
            System.out.println(e1);
        }
    }

    public void startSchedule(){
        queryScheduler.start();
    }

    public void stopSchedule(){
        queryScheduler.stop();
    }

    private class QueryScheduler {
        public void QueryScheduler(String path){
        }

        Scheduler scheduler;

        public void start(){
            try {
                // Grab the Scheduler instance from the Factory
                scheduler = StdSchedulerFactory.getDefaultScheduler();

                // and start it off
                scheduler.start();
                String tmp = queryList.toString();
                // define the job and tie it to our HelloJob class
                JobDetail job = newJob(QueryJob1.class)
                        .withIdentity("job1", "group1")
                        .usingJobData("pathToSave",pathToSave)
                        .build();

                // Trigger the job to run now, and then repeat every 1 seconds 0,30 * * ? * * *
                Trigger trigger = newTrigger()
                        .withIdentity("trigger1", "group1")
                        .startNow()
                        .withSchedule(cronSchedule("0,30 * * ? * * *"))
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
//
        public void stop(){
            try {
                scheduler.shutdown();
            } catch (SchedulerException se) {
                se.printStackTrace();
            }
        }
//
//    }
//
//
//    public class QueryJob implements org.quartz.Job {
//
//        public QueryJob() {
//        }
//
//        public void execute(JobExecutionContext context) throws JobExecutionException {
//            System.out.println("Query job is executed.");
//            //saveListToFile();
//        }
   }
}
