package com.arseniculage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Task2 {

    private String text;

    public Task2(String text) {
        this.text = text;
    }

    String t1_insert()
    {
        Pattern ptrn = Pattern.compile("(\\.)");
        Matcher mtch = ptrn.matcher(this.text);
        String txt = "";
        txt = this.text.split("(\\.)",2)[0]+". Вставить"+this.text.split("(\\.)",2)[1];
        return txt;
    }
    String t2_palindrome()
    {
        Pattern ptrn = Pattern.compile("(?<=(−| ))(([0-9]{3}))(?= )");
        Matcher mtch = ptrn.matcher(this.text);
        List<String> arr = new ArrayList<>();
        String txt = "";
        while (mtch.find())
        {
            String str_temp = new StringBuffer(mtch.group()).reverse().toString();
            this.text = this.text.split(mtch.group(),2)[0]+mtch.group() + str_temp + this.text.split(mtch.group(),2)[1];
        }
        return this.text;
    }

}
