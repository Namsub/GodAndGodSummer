package com.juntcompany.godandgodsummer.Util;

import android.provider.BaseColumns;

/**
 * Created by EOM on 2016-07-28.
 */
public class DBContants {


    public interface ChattingRoom extends BaseColumns {
        public static final String TABLE_NAME = "chattingRoom";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LAST_MESSAGE_ID = "lastMessageId";

//        public static final String COLUMN_LAST_MESSAGE_ID = "lastMessageId";
    }

    public interface MessageTable extends BaseColumns {
        public static final String TABLE_NAME = "messageTable";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_MESSAGE = "message";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_CREATED_DATE = "created";
    }

    public interface Target extends BaseColumns {
        public static final String TABLE_NAME = "chat_room_info";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LAST_SPEAK = "last_speak";
        public static final String COLUMN_LAST_TIME = "last_time";
    }

    ///db 컬럼 정의 하는 곳
}
