package com.durga.lc.db


 import android.content.ContentValues
    import android.content.Context
    import android.database.Cursor
    import android.database.sqlite.SQLiteConstraintException
    import android.database.sqlite.SQLiteDatabase
    import android.database.sqlite.SQLiteException
    import android.database.sqlite.SQLiteOpenHelper

    import java.util.ArrayList

    class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_ENTRIES)
            db.execSQL(SQL_CREATE_SCORE_ENTRIES)

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES)
            db.execSQL(SQL_DELETE_SCORE_ENTRIES)

            onCreate(db)
        }

        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }

        @Throws(SQLiteConstraintException::class)
        fun insertUser(user: UserModel): Boolean {
            // Gets the data repository in write modes
            val db = writableDatabase

            // Create a new map of values, where column names are the keys
            val values = ContentValues()
//            values.put(DBContract.UserEntry.COLUMN_USER_ID, user.userid)
            values.put(DBContract.UserEntry.COLUMN_ID, user.id)
            values.put(DBContract.UserEntry.COLUMN_MEM1, user.member1)
            values.put(DBContract.UserEntry.COLUMN_MEM2, user.member2)
            values.put(DBContract.UserEntry.COLUMN_MEM3, user.member3)
            values.put(DBContract.UserEntry.COLUMN_MEM4, user.member4)
            values.put(DBContract.UserEntry.COLUMN_MEM5, user.member5)
            values.put(DBContract.UserEntry.COLUMN_MEM6, user.member6)
            values.put(DBContract.UserEntry.COLUMN_MEM7, user.member7)
            values.put(DBContract.UserEntry.COLUMN_MEM8, user.member8)

            println(values)
            // Insert the new row, returning the primary key value of the new row
            val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

            return true
        }
        fun updateScore(user: UserModel,roundNo: Int): Boolean {
            val values = ContentValues()
            values.put(DBContract.UserEntry.COLUMN_MEM1, user.member1)
            values.put(DBContract.UserEntry.COLUMN_MEM2, user.member2)
            values.put(DBContract.UserEntry.COLUMN_MEM3, user.member3)
            values.put(DBContract.UserEntry.COLUMN_MEM4, user.member4)
            values.put(DBContract.UserEntry.COLUMN_MEM5, user.member5)
            values.put(DBContract.UserEntry.COLUMN_MEM6, user.member6)
            values.put(DBContract.UserEntry.COLUMN_MEM7, user.member7)
            values.put(DBContract.UserEntry.COLUMN_MEM8, user.member8)

            val db = this.writableDatabase
            db.update(DBContract.UserEntry.TABLE_SCORES, values, "${DBContract.UserEntry.COLUMN_ROUNDNO}"+" ='"+roundNo+"'", arrayOf())
// or
            //db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf("1"))

            return true
        }


        @Throws(SQLiteConstraintException::class)
        fun insertSCORES(user: UserModel): Boolean {
            // Gets the data repository in write modes
            val db = writableDatabase

            // Create a new map of values, where column names are the keys
            val values = ContentValues()
//            values.put(DBContract.UserEntry.COLUMN_USER_ID, user.userid)
            values.put(DBContract.UserEntry.COLUMN_ID, user.id)
                        values.put(DBContract.UserEntry.COLUMN_ROUNDNO, user.roundNo)

            values.put(DBContract.UserEntry.COLUMN_MEM1, user.member1)
            values.put(DBContract.UserEntry.COLUMN_MEM2, user.member2)
            values.put(DBContract.UserEntry.COLUMN_MEM3, user.member3)
            values.put(DBContract.UserEntry.COLUMN_MEM4, user.member4)
            values.put(DBContract.UserEntry.COLUMN_MEM5, user.member5)
            values.put(DBContract.UserEntry.COLUMN_MEM6, user.member6)
            values.put(DBContract.UserEntry.COLUMN_MEM7, user.member7)
            values.put(DBContract.UserEntry.COLUMN_MEM8, user.member8)

            println(values)
            // Insert the new row, returning the primary key value of the new row
            val newRowId = db.insert(DBContract.UserEntry.TABLE_SCORES, null, values)

            return true
        }
        fun getUsersCurrentCount(): Int {
            val countQuery = "SELECT  * FROM "+DBContract.UserEntry.TABLE_NAME
            val db = this.readableDatabase
            val cursor = db.rawQuery(countQuery, null)
            val count = cursor.count
            cursor.close()
            return count
        }
        fun getScoreCurrentCount(): Int {
            val countQuery = "SELECT  * FROM "+DBContract.UserEntry.TABLE_SCORES
            val db = this.readableDatabase
            val cursor = db.rawQuery(countQuery, null)
            val count = cursor.count
            cursor.close()
            return count
        }
        @Throws(SQLiteConstraintException::class)
        fun deleteUser(userid: String): Boolean {
            // Gets the data repository in write mode
            val db = writableDatabase
            // Define 'where' part of query.
            val selection = DBContract.UserEntry.COLUMN_ID + " LIKE ?"
            // Specify arguments in placeholder order.
            val selectionArgs = arrayOf(userid)
            // Issue SQL statement.
            db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)

            return true
        }
        @Throws(SQLiteConstraintException::class)
        fun deleteScores(): Boolean {
            // Gets the data repository in write mode
            val db = writableDatabase
            // Define 'where' part of query.
            val selection = DBContract.UserEntry.COLUMN_ID + " LIKE ?"
            // Specify arguments in placeholder order.
//            val selectionArgs = arrayOf(userid)
            // Issue SQL statement.
            db.delete(DBContract.UserEntry.TABLE_SCORES, null, null)

            return true
        }

        fun readUser(userid: String): ArrayList<UserModel> {
            val users = ArrayList<UserModel>()
            val db = writableDatabase
            var cursor: Cursor? = null
            try {
                cursor = db.rawQuery(
                    "select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_ID + "='" + userid + "'",
                    null
                )
            } catch (e: SQLiteException) {
                // if table not yet present, create it
                db.execSQL(SQL_CREATE_ENTRIES)
                return ArrayList()
            }

            var COLUMN_MEM1: String
            var COLUMN_MEM2: String
            var COLUMN_MEM3: String
            var COLUMN_MEM4: String
            var COLUMN_MEM5: String
            var COLUMN_MEM6: String
            var COLUMN_MEM7: String
            var COLUMN_MEM8: String

            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    COLUMN_MEM1 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM1))
                    COLUMN_MEM2 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM2))
                    COLUMN_MEM3 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM3))
                    COLUMN_MEM4 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM4))
                    COLUMN_MEM5 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM5))
                    COLUMN_MEM6 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM6))
                    COLUMN_MEM7 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM7))
                    COLUMN_MEM8 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM8))

                    users.add(
                        UserModel(
                            userid,
                            0,
                            COLUMN_MEM1,
                            COLUMN_MEM2,
                            COLUMN_MEM3,
                            COLUMN_MEM4,
                            COLUMN_MEM5,
                            COLUMN_MEM6,
                            COLUMN_MEM7,
                            COLUMN_MEM8
                        )
                    )
                    cursor.moveToNext()
                }
            }
            return users
        }
        fun readUserScores(userid: String): ArrayList<UserModel> {
            val users = ArrayList<UserModel>()
            val db = writableDatabase
            var cursor: Cursor? = null
            try {
                cursor = db.rawQuery(
                    "select * from " + DBContract.UserEntry.TABLE_SCORES + " WHERE " + DBContract.UserEntry.COLUMN_ID + "='" + userid + "'",
                    null
                )
            } catch (e: SQLiteException) {
                // if table not yet present, create it
                db.execSQL(SQL_CREATE_ENTRIES)
                return ArrayList()
            }
            var roundNo: Int

            var COLUMN_MEM1: String
            var COLUMN_MEM2: String
            var COLUMN_MEM3: String
            var COLUMN_MEM4: String
            var COLUMN_MEM5: String
            var COLUMN_MEM6: String
            var COLUMN_MEM7: String
            var COLUMN_MEM8: String

            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    roundNo =
                        cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ROUNDNO))
                    COLUMN_MEM1 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM1))
                    COLUMN_MEM2 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM2))
                    COLUMN_MEM3 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM3))
                    COLUMN_MEM4 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM4))
                    COLUMN_MEM5 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM5))
                    COLUMN_MEM6 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM6))
                    COLUMN_MEM7 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM7))
                    COLUMN_MEM8 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM8))

                    users.add(
                        UserModel(
                            userid,
                            roundNo,
                            COLUMN_MEM1,
                            COLUMN_MEM2,
                            COLUMN_MEM3,
                            COLUMN_MEM4,
                            COLUMN_MEM5,
                            COLUMN_MEM6,
                            COLUMN_MEM7,
                            COLUMN_MEM8
                        )
                    )
                    cursor.moveToNext()
                }
            }
            return users
        }
        fun readUserScores(userid: String,roundNo: Int): ArrayList<UserModel> {
            val users = ArrayList<UserModel>()
            val db = writableDatabase
            var cursor: Cursor? = null
            try {
                cursor = db.rawQuery(
                    "select * from " + DBContract.UserEntry.TABLE_SCORES + " WHERE " + DBContract.UserEntry.COLUMN_ID + "='" + userid + "' AND "+ DBContract.UserEntry.COLUMN_ROUNDNO + "='" + roundNo+ "'",
                    null
                )
            } catch (e: SQLiteException) {
                // if table not yet present, create it
                db.execSQL(SQL_CREATE_ENTRIES)
                return ArrayList()
            }
            var roundNo: Int

            var COLUMN_MEM1: String
            var COLUMN_MEM2: String
            var COLUMN_MEM3: String
            var COLUMN_MEM4: String
            var COLUMN_MEM5: String
            var COLUMN_MEM6: String
            var COLUMN_MEM7: String
            var COLUMN_MEM8: String

            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    roundNo =
                        cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ROUNDNO))
                    COLUMN_MEM1 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM1))
                    COLUMN_MEM2 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM2))
                    COLUMN_MEM3 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM3))
                    COLUMN_MEM4 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM4))
                    COLUMN_MEM5 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM5))
                    COLUMN_MEM6 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM6))
                    COLUMN_MEM7 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM7))
                    COLUMN_MEM8 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM8))

                    users.add(
                        UserModel(
                            userid,
                            roundNo,
                            COLUMN_MEM1,
                            COLUMN_MEM2,
                            COLUMN_MEM3,
                            COLUMN_MEM4,
                            COLUMN_MEM5,
                            COLUMN_MEM6,
                            COLUMN_MEM7,
                            COLUMN_MEM8
                        )
                    )
                    cursor.moveToNext()
                }
            }
            return users
        }
        fun readAllUsers(): ArrayList<UserModel> {
            val users = ArrayList<UserModel>()
            val db = writableDatabase
            var cursor: Cursor? = null
            try {
                cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
            } catch (e: SQLiteException) {
                db.execSQL(SQL_CREATE_ENTRIES)
                return ArrayList()
            }

            var COLUMN_MEM1: String
            var COLUMN_MEM2: String
            var COLUMN_MEM3: String
            var COLUMN_MEM4: String
            var COLUMN_MEM5: String
            var COLUMN_MEM6: String
            var COLUMN_MEM7: String
            var COLUMN_MEM8: String
            var userid: String

            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    userid =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID))
                    COLUMN_MEM1 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM1))
                    COLUMN_MEM2 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM2))
                    COLUMN_MEM3 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM3))
                    COLUMN_MEM4 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM4))
                    COLUMN_MEM5 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM5))
                    COLUMN_MEM6 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM6))
                    COLUMN_MEM7 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM7))
                    COLUMN_MEM8 =
                        cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MEM8))

                    users.add(
                        UserModel(
                            userid,
                            0,
                            COLUMN_MEM1,
                            COLUMN_MEM2,
                            COLUMN_MEM3,
                            COLUMN_MEM4,
                            COLUMN_MEM5,
                            COLUMN_MEM6,
                            COLUMN_MEM7,
                            COLUMN_MEM8
                        )
                    )
                    cursor.moveToNext()
                }
            }
            return users

        }
        companion object {
            // If you change the database schema, you must increment the database version.
            val DATABASE_VERSION = 1
            val DATABASE_NAME = "FeedReader.db"

            private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                        DBContract.UserEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                        DBContract.UserEntry.COLUMN_ROUNDNO + " INTEGER," +

                        DBContract.UserEntry.COLUMN_MEM1 + " TEXT," +
                        DBContract.UserEntry.COLUMN_MEM2 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM3 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM4 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM5 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM6 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM7 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM8 + " TEXT)"
            private val SQL_CREATE_SCORE_ENTRIES =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_SCORES + " (" +
                        DBContract.UserEntry.COLUMN_ID + " TEXT," +
                        DBContract.UserEntry.COLUMN_ROUNDNO + " INTEGER," +
                        DBContract.UserEntry.COLUMN_MEM1 + " TEXT," +
                        DBContract.UserEntry.COLUMN_MEM2 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM3 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM4 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM5 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM6 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM7 + " TEXT,"+
                        DBContract.UserEntry.COLUMN_MEM8 + " TEXT)"

            private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
            private val SQL_DELETE_SCORE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME

        }

    }