package Querys;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;

public class QueryJob1 implements org.quartz.Job {


    //final static Logger logger = Logger.getLogger(QueryJob1.class);

    public QueryJob1() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        //System.out.println("Query job is executed.");
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String pathToSave = dataMap.getString("pathToSave");
        //saveListToFile();
        //logger.info("Query job is executed.");
        try {
            PrintWriter writer = new PrintWriter(pathToSave, "UTF-8");

            Collections.sort(Queries.queryList, new Comparator<Query>(){
                public int compare(Query o1, Query o2){
                    if(o1.getNr() == o2.getNr())
                        return 0;
                    return o1.getNr() < o2.getNr() ? -1 : 1;
                }
            });

            for (Query query: Queries.queryList
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

}
