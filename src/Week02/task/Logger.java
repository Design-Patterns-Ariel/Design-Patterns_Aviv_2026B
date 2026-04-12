package Week02.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    private String buildLog(String msg, String from, String action, String hasError, String typeOfError) {
        return "{"+"Logger"+ " from: " +LocalDateTime.now() + " - " + msg + " -> " ;
    }


}
