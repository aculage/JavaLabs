package com.arseniculage;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Task1 {

    private String code;

    public Task1(String code) {
        this.code = code;
    }

    boolean chk_for_math() { //done
        /*Pattern initial_spillter = Pattern.compile("[\n\r]{1,}|[\n]{1,}|[\r]{1,}");
        */
        Pattern math_locator = Pattern.compile("import java.lang.Math;");
        Matcher math_matcher = math_locator.matcher(this.code);
        if (math_matcher.find())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    String locate_all_mthfuncs(){
        Pattern func_locator = Pattern.compile("(?<=(Math\\.))([a-z]*)");
        Matcher matcher = func_locator.matcher(this.code);
        String locs = "";
        while (matcher.find())
        {
            locs += (matcher.group() + " ");
        }
        return locs.toString();

    }
    List variables(){
        Pattern var_locator = Pattern.compile("([a-z]+ [a-z]+)(?=( *= *.Math\\.))");
        Matcher matcher = var_locator.matcher(this.code);
        List <String> vars = new ArrayList<>();
        while (matcher.find())
        {
            vars.add(matcher.group());
        }
        return vars;
    }
    String locate_operators(){
        Pattern op_locator = Pattern.compile("(.*(parseInt|toString).*)");
        Matcher matcher = op_locator.matcher(this.code);
        String outp = "";
        while (matcher.find())
        {
            outp += matcher.group()+'\n';
        }
        return outp;
    }

}