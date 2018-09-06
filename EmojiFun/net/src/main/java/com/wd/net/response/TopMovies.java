package com.wd.net.response;

import java.util.List;

/**
 * @author : wudu
 * @Date : 2018/9/4
 * Hi,Baby.
 */

public class TopMovies {
    private String title;
    private List<Subjects> subjects;

    public String getTitle() {
        return title;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public class Subjects{
        private String title, year, id;

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }

        public String getId() {
            return id;
        }
    }
}
