package com.example.apple.xianjinxia.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table e_allinfo.
*/
public class AllInfoDao extends AbstractDao<AllInfo, Long> {

    public static final String TABLENAME = "e_allinfo";

    /**
     * Properties of entity AllInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property A_pid = new Property(0, Long.class, "a_pid", false, "A_PID");
        public final static Property A_id = new Property(1, Long.class, "a_id", true, "A_ID");
        public final static Property A_add_date = new Property(2, String.class, "a_add_date", false, "A_ADD_DATE");
        public final static Property A_notes = new Property(3, Integer.class, "a_notes", false, "A_NOTES");
        public final static Property A_cards = new Property(4, Integer.class, "a_cards", false, "A_CARDS");
        public final static Property A_bills = new Property(5, Integer.class, "a_bills", false, "A_BILLS");
        public final static Property A_diarys = new Property(6, Integer.class, "a_diarys", false, "A_DIARYS");
        public final static Property A_feeling = new Property(7, Integer.class, "a_feeling", false, "A_FEELING");
    };


    public AllInfoDao(DaoConfig config) {
        super(config);
    }
    
    public AllInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'e_allinfo' (" + //
                "'A_PID' INTEGER," + // 0: a_pid
                "'A_ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 1: a_id
                "'A_ADD_DATE' TEXT," + // 2: a_add_date
                "'A_NOTES' INTEGER," + // 3: a_notes
                "'A_CARDS' INTEGER," + // 4: a_cards
                "'A_BILLS' INTEGER," + // 5: a_bills
                "'A_DIARYS' INTEGER," + // 6: a_diarys
                "'A_FEELING' INTEGER);"); // 7: a_feeling
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'e_allinfo'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AllInfo entity) {
        stmt.clearBindings();
 
        Long a_pid = entity.getA_pid();
        if (a_pid != null) {
            stmt.bindLong(1, a_pid);
        }
 
        Long a_id = entity.getA_id();
        if (a_id != null) {
            stmt.bindLong(2, a_id);
        }
 
        String a_add_date = entity.getA_add_date();
        if (a_add_date != null) {
            stmt.bindString(3, a_add_date);
        }
 
        Integer a_notes = entity.getA_notes();
        if (a_notes != null) {
            stmt.bindLong(4, a_notes);
        }
 
        Integer a_cards = entity.getA_cards();
        if (a_cards != null) {
            stmt.bindLong(5, a_cards);
        }
 
        Integer a_bills = entity.getA_bills();
        if (a_bills != null) {
            stmt.bindLong(6, a_bills);
        }
 
        Integer a_diarys = entity.getA_diarys();
        if (a_diarys != null) {
            stmt.bindLong(7, a_diarys);
        }
 
        Integer a_feeling = entity.getA_feeling();
        if (a_feeling != null) {
            stmt.bindLong(8, a_feeling);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    /** @inheritdoc */
    @Override
    public AllInfo readEntity(Cursor cursor, int offset) {
        AllInfo entity = new AllInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // a_pid
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // a_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // a_add_date
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // a_notes
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // a_cards
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // a_bills
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // a_diarys
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7) // a_feeling
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AllInfo entity, int offset) {
        entity.setA_pid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setA_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setA_add_date(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setA_notes(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setA_cards(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setA_bills(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setA_diarys(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setA_feeling(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(AllInfo entity, long rowId) {
        entity.setA_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(AllInfo entity) {
        if(entity != null) {
            return entity.getA_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
