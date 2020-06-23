package main.java.tdd.chap08.testable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyBatchLoader {
    //시간을 구하는 기능을 따로 분리하여 시간에 따라 다른결과가 도출되는 것을 막는다.
    private Times times = new Times();
    private String basePath = "";

    public void setBasePath(String basePath){
        this.basePath = basePath;
    }

    public void setTimes(Times times){
        this.times = times;
    }

    public int load(){
        LocalDate date = times.today();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Path bathPath = Paths.get(basePath,date.format(formatter),"batch.txt");

        try{
            return (int) Files.lines(bathPath).count();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
