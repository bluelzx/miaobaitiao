package com.example.apple.xianjinxia.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table e_allcardinfo.
*/
public class AllCardInfoDao extends AbstractDao<AllCardInfo, Long> {

    public static final String TABLENAME = "e_allcardinfo";

    /**
     * Properties of entity AllCardInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Ac_pid = new Property(0, Long.class, "ac_pid", false, "AC_PID");
        public final static Property Ac_id = new Property(1, Long.class, "ac_id", true, "AC_ID");
        public final static Property Ac_add_date = new Property(2, String.class, "ac_add_date", false, "AC_ADD_DATE");
        public final static Property Ac_type = new Property(3, Integer.class, "ac_type", false, "AC_TYPE");
        public final static Property Ac_card_name = new Property(4, String.class, "ac_card_name", false, "AC_CARD_NAME");
    };


    public AllCardInfoDao(DaoConfig config) {
        super(config);
    }
    
    public AllCardInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'e_allcardinfo' (" + //
                "'AC_PID' INTEGER," + // 0: ac_pid
                "'AC_ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 1: ac_id
                "'AC_ADD_DATE' TEXT," + // 2: ac_add_date
                "'AC_TYPE' INTEGER," + // 3: ac_type
                "'AC_CARD_NAME' TEXT);"); // 4: ac_card_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'e_allcardinfo'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AllCardInfo entity) {
        stmt.clearBindings();
 
        Long ac_pid = entity.getAc_pid();
        if (ac_pid != null) {
            stmt.bindLong(1, ac_pid);
        }
 
        Long ac_id = entity.getAc_id();
        if (ac_id != null) {
            stmt.bindLong(2, ac_id);
        }
 
        String ac_add_date = entity.getAc_add_date();
        if (ac_add_date != null) {
            stmt.bindString(3, ac_add_date);
        }
 
        Integer ac_type = entity.getAc_type();
        if (ac_type != null) {
            stmt.bindLong(4, ac_type);
        }
 
        String ac_card_name = entity.getAc_card_name();
        if (ac_card_name != null) {
            stmt.bindString(5, ac_card_name);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    /** @inheritdoc */
    @Override
    public AllCardInfo readEntity(Cursor cursor, int offset) {
        AllCardInfo entity = new AllCardInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ac_pid
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // ac_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ac_add_date
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // ac_type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // ac_card_name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AllCardInfo entity, int offset) {
        entity.setAc_pid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAc_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setAc_add_date(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAc_type(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setAc_card_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(AllCardInfo entity, long rowId) {
        entity.setAc_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(AllCardInfo entity) {
        if(entity != null) {
            return entity.getAc_id();
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
