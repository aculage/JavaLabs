package com.arseniculage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private String str;

    public  Task3 (String str)
    {
        this.str = str;
    }

    Boolean t1_check()
    {
        Matcher mtchr = Pattern.compile("((?<=')([0-9]{1,})(?='))").matcher(this.str);

        if (mtchr.find())
        {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    String t2_check()
    {
        Matcher mtchr = Pattern.compile("((?<=')([0-9]{1,})(?='))").matcher(this.str);

        while (mtchr.find())
        {
            this.str = this.str.split(mtchr.group(),2)[0] + Integer.toString(Integer.parseInt(mtchr.group())*2) + this.str.split(mtchr.group(),2)[1];
        }
        return this.str;

    }


}
