package com.shadowinlife.app.Tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.RemoteIterator;

public class RegexPathFilter implements PathFilter, Configurable {

    public static final String CONF_REGEX_PROPERTY = "regexPathFilter.regex";
    private String _regex;
    private Configuration _conf;
    public RegexPathFilter(String _regex) {
        this._regex = _regex;
    }

    @Override
    public void setConf(Configuration conf) {

        // get regex from Configuration

    }

    @Override
    public Configuration getConf() {
        return _conf;
    }

    public boolean accept(Path path) {
        try {
            _conf = new Configuration(true);
            FileSystem fs = FileSystem.get(_conf);
            RemoteIterator<LocatedFileStatus> filePaths = fs.listFiles(path, true);
            while (filePaths.hasNext()) {
                String name = filePaths.next().getPath().toString();
                System.out.println("gongmeng:" + name);
                if (name.matches(_regex)) {
                    return true;
                }

            }
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }
}