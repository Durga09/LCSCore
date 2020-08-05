package com.durga.lc.db
import android.provider.BaseColumns

object DBContract {
    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "users"
            val TABLE_SCORES = "scores"

            val COLUMN_ID = "userid"
            val COLUMN_ROUNDNO = "roundNo"

            val COLUMN_MEM1 = "MEM1"
            val COLUMN_MEM2 = "MEM2"
            val COLUMN_MEM3 = "MEM3"
            val COLUMN_MEM4 = "MEM4"
            val COLUMN_MEM5 = "MEM5"
            val COLUMN_MEM6 = "MEM6"
            val COLUMN_MEM7 = "MEM7"
            val COLUMN_MEM8 = "MEM8"

        }
    }
}