package com.wuxi;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: qingtong
 * @create: 2018-05-11
 **/
public class ShardingTest {

    private static int MAX_TABLE = 64;

    private static int MAX_DB = 64;

    private static int TABLE_MOD = 4;

    private static int DB_MOD = 64;

    public static void main(String[] args) {
        System.out.println(getString("000000246", "xx_"));
    }

    private static String getString(String value, String preTable) {

        long valueLong = Long.parseLong(value);
        long dbId = ((valueLong / MAX_TABLE) % MAX_DB) / DB_MOD * DB_MOD;
        long tableId = ((valueLong / MAX_DB) % MAX_TABLE) / TABLE_MOD * TABLE_MOD;

        String db = StringUtils.leftPad(String.valueOf(dbId), 2, '0');
        String table = StringUtils.leftPad(String.valueOf(tableId), 2, '0');

        return preTable + "_" + db + table;
    }

}
