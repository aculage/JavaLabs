package com.arseniculage;

public class Film {
        private String title;
        private String studio;
        private int year;
        private String director;
        private String leadRole;

        public Film(String title, String studio, int year, String director, String leadRole) {
            this.title = title;
            this.studio = studio;
            this.year = year;
            this.director = director;
            this.leadRole = leadRole;
        }

        public String getTitle() { return this.title; }

        public String getStudio() { return this.studio; }

        public int getYear() { return this.year; }

        public String getDirector() { return this.director; }

        public String getLeadRole() { return this.leadRole; }

}
