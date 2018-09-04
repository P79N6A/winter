package com.panda.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils{

	private final static String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？a-zA-Z\\u4e00-\\u9fa5-_]";

    private final static String PLUS86 = "+86";

    /**
     * 将首字母转小写
     *
     * @param str 字符串
     * @return
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param str 字符串
     * @return
     */
    public static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 判断是否有未空的参数
     *
     * @param objs 参数
     * @return
     */
    public static boolean hasBlank(Object... objs) {
        for (Object obj : objs) {
            if (obj instanceof String) {
                if (StringUtils.isBlank((String) obj)) {
                    return true;
                }
            } else {
                if (obj == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将文本中的英文双引号转换为中文双引号
     * <p>部分数据库中文字段,由于采用英文双引号,导致转换为JSON之后前端无法解析</p>
     */
    public static String convertQuotation(String content) {

        String regex = "\"([^\"]*)\"";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        String reCT = content;

        while (matcher.find()) {
            String itemMatch = "“" + matcher.group(1) + "”";
            reCT = reCT.replace("\"" + matcher.group(1) + "\"", itemMatch);
        }

        return reCT;
    }

    /**
     * 字符串转换为map
     *
     * @param str
     * @return
     */
    public static Map<String, String> stringTranferMap(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String[] arr = str.split("&");
        Map<String, String> map = new HashMap<String, String>();
        if (arr != null) {
            String[] newArr = null;
            for (String string : arr) {
                newArr = string.split("=");
                if (newArr.length == 1){
                    map.put(newArr[0], "");
                }else{
                    map.put(newArr[0], newArr[1]);
                }

            }
        }
        return map;
    }
    
	/**
	 * 功能：<br/>
	 * Object 2 String
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		return obj == null ? null : obj.toString();
	}

    public static void main(String[] args) {
        System.out.println(toLowerCaseFirstOne("Hello"));
        System.out.println(toUpperCaseFirstOne("hello"));
        System.out.println(convertQuotation("say:\"hello1\",\"hello2\",\"hello3\",\"hello4\""));

        String s = "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
        System.out.println("原始字符串="+s);
        System.out.println("修改后字符串="+StringUtils.deleteWhitespace(StringFilter(s)));
    }

    /**
     * 过滤特殊字符、字母、汉字、下划线(只允许数字)
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String StringFilter(String str) throws PatternSyntaxException {
        if(StringUtil.isBlank(str)){
            return str;
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 手机号特殊字符、字母、汉字、下划线等过滤
     * @param number
     * @return
     */
    public static String phoneNumberFilter(String number){
        String newNumber = StringUtils.deleteWhitespace(number);
        if(StringUtil.isBlank(newNumber)){
            return newNumber;
        }
        if(newNumber.startsWith(PLUS86)){
            newNumber = newNumber.replace(PLUS86, "");
            newNumber = StringUtils.deleteWhitespace(newNumber);
        }
        try{
            newNumber = StringFilter(newNumber);
        }catch(Exception e){
            e.printStackTrace();
        }
        return StringUtils.deleteWhitespace(newNumber);
    }
}
