package edu.project3.Sources;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MasterOfSources implements LogSource {

    public static ArrayList<String> paths = new ArrayList<>();

    @Override
    public String getData(String path) {
        Pattern http = Pattern.compile("^(https?|ftp):.*");
        if (http.matcher(path).find()) {
            return new HttpSource().getData(path);
        } else if (path.contains("?") || path.contains("*") || path.contains("[") || path.contains("{")) {
            return new GlobSource().getData(path);
        } else {
            return new FileSource().getData(path);
        }
    }

}
