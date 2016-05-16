package com.elite.tools.catcher.core.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by df on 16/5/15.
 */
public class TestPattern {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(?<=data\\.dataList\\s=\\s).+(?=;)");
        String str = "data.dataList = [{\"phone\":\"020-82250856\",\"email\":\"xta@xt-motor.com\",\"contactName\":\"赵卫群\",\"age\":\"\",\"genderName\":\"男\",\"mobile\":\"\"},{\"phone\":\"02082250856\",\"email\":\"736104889@qq.com\",\"contactName\":\"赵卫群\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"013609778909\"},{\"phone\":\"02082215706\",\"email\":\"\",\"contactName\":\"财务\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"},{\"phone\":\"18702177288\",\"email\":\"\",\"contactName\":\"赵先生\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"},{\"phone\":\"\",\"email\":\"935930393@qq.com\",\"contactName\":\"张\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"13710257728\"},{\"phone\":\"\",\"email\":\"\",\"contactName\":\"\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"},{\"phone\":\"\",\"email\":\"\",\"contactName\":\"\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"},{\"phone\":\"\",\"email\":\"\",\"contactName\":\"\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"},{\"phone\":\"\",\"email\":\"\",\"contactName\":\"\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"},{\"phone\":\"\",\"email\":\"yuio4433@163.com\",\"contactName\":\"赵\",\"age\":\"\",\"genderName\":\"\",\"mobile\":\"\"}];\n";
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group());

        } else {
            System.out.println("not matched");
        }

    }
}
