package com.cpic.team.basetools.utils;

import java.util.List;

/**
 * Created by Taylor on 2016/7/20.
 */
public class ArrayToString {

    /**
     * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
     */
    public static String converToString(String[] ig) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for (int i = 0; i < ig.length; i++) {
                str += ig[i] + ",";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * @Description:把list转换为一个用逗号分隔的字符串
     */
    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * @Description:把list转换为一个用逗号分隔的字符串
     */
    public static String listToString3(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + "||");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }



    /**
     * @Description:把list转换为一个用分号分隔的字符串
     */
    public static String listToString2(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ";");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }
    /**
     * @Description:把list转换为一个用扩折号分隔的字符串
     */
    public static String listToString4(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + "-");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }


    /**
     * @Description:把list转换为一个用下划线分隔的字符串
     */
    public static String listToString5(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + "_");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * @Description:把list转换为一个用下划线分隔的字符串
     */
    public static String listToString6(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + "||");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

}
