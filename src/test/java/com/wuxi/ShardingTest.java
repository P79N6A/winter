package com.wuxi;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author: qingtong
 * @create: 2018-05-11
 **/
public class ShardingTest {

    private static int MAX_TABLE = 8;

    private static int MAX_DB = 4;

    private static int TABLE_MOD = 2;//表示开始时将2张表并成1张表

    private static int DB_MOD = 4;//表示开始时将4个库并成1个库

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + getString(i + "", "xx_"));
        }

    }

    /*
        取模方案扩容的解决：一、可以提前按照预估方案创建库和表，前期放到一个DB，后期迁移，或者一次性弄好
        二、可以按照预估的方案去确定分库和分表的后缀ID，但是前期只创建一部分库和表。
        例如：最终4库，每个库8个表。后缀应该是0000----0307共32个表的后缀，前期可以一个库，4个表，也就是所有数据都在这个四张表里面。
     */
    private static String getString(String value, String preTable) {

        long valueLong = Long.parseLong(value);
        long dbId = valueLong / MAX_TABLE % MAX_DB / DB_MOD * DB_MOD;
        long tableId = valueLong / MAX_DB % MAX_TABLE / TABLE_MOD * TABLE_MOD;

        String db = StringUtils.leftPad(String.valueOf(dbId), 2, '0');
        String table = StringUtils.leftPad(String.valueOf(tableId), 2, '0');

        return preTable + "_" + db + table;
    }

    private static void sharding(long id) {
        long a1 = (id / MAX_DB);
        long a2 = (id / MAX_DB % MAX_TABLE);
        long a3 = (id / MAX_DB % MAX_TABLE / TABLE_MOD);
        long a4 = (id / MAX_DB % MAX_TABLE / TABLE_MOD * TABLE_MOD);
        System.out.println(id + " : " + a1 + " -> " + a2 + " -> " + a3 + " -> " + a4);
    }

    @Test
    public void shardingTest() {
        sharding(255);
        sharding(256);
        sharding(511);
        sharding(512);
    }


    @Test
    public void shardingDbTableTest() {
        for (long i = 0; i < 100; i++) {
            shardingDbTable(i);
        }
    }

    private static void shardingDbTable(long id) {
        long dbId = id / MAX_TABLE % MAX_DB;
        long tableId = id / MAX_DB % MAX_TABLE;

        String db = StringUtils.leftPad(String.valueOf(dbId), 2, '0');
        String table = StringUtils.leftPad(String.valueOf(tableId), 2, '0');

        System.out.println(id + "\t: " + db + " - " + table);
    }


}
