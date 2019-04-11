package com.wuxi;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class XssFilterTest {


    private String[] filterChars;
    private String[] replaceChars;
    private static final String REGEX = "(<script.*?>).*?(</script>)";

    @Before
    public void init() {
        String filterChar = ">@<@\'@\"@\\@#@(@)";
        String replaceChar = "＞'@<@‘@“@＼@＃@（@）";
        String splitChar = "@";

        if (filterChar != null && filterChar.length() > 0) {
            filterChars = filterChar.split(splitChar);
        }
        if (replaceChar != null && replaceChar.length() > 0) {
            replaceChars = replaceChar.split(splitChar);
        }
    }

    @Test
    public void xssEncode() {
        String s = "<script>alert('XSS')</script>";
        if (s == null || s.equals("")) {
            return;
        }
        try {
            s = URLDecoder.decode(s, "UTF-8");
            s = replaceScript(s);
            if (filterChars != null && filterChars.length != 0 && replaceChars != null && replaceChars.length != 0) {
                for (int i = 0; i < filterChars.length; i++) {
                    if (s.contains(filterChars[i])) {
                        s = s.replace(filterChars[i], replaceChars[i]);
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }

    /**
     * 替换 script
     *
     * @param content
     * @return
     */
    private String replaceScript(String content) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String group = matcher.group();
            String str = group;
            str = str.replace("<", "&lt;");
            str = str.replace(">", "&gt;");
            str = str.replaceAll("\"", "&quot;");
            content = content.replace(group, str);
        }
        return content;
    }

}
