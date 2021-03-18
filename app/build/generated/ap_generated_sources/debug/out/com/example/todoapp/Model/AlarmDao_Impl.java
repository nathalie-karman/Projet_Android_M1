package com.example.todoapp.Model;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AlarmDao_Impl implements AlarmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EventAlarm> __insertionAdapterOfEventAlarm;

  public AlarmDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventAlarm = new EntityInsertionAdapter<EventAlarm>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `BDD` (`id`,`msg_alarm`,`time_alarm`,`date_alarm`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EventAlarm value) {
        stmt.bindLong(1, value.getId());
        if (value.getMsg_alarm() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMsg_alarm());
        }
        if (value.getTime_alarm() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTime_alarm());
        }
        if (value.getDate_alarm() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate_alarm());
        }
      }
    };
  }

  @Override
  public void insertAll(final EventAlarm eventAlarm) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEventAlarm.insert(eventAlarm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<EventAlarm> getAllData() {
    final String _sql = "SELECT * FROM BDD";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMsgAlarm = CursorUtil.getColumnIndexOrThrow(_cursor, "msg_alarm");
      final int _cursorIndexOfTimeAlarm = CursorUtil.getColumnIndexOrThrow(_cursor, "time_alarm");
      final int _cursorIndexOfDateAlarm = CursorUtil.getColumnIndexOrThrow(_cursor, "date_alarm");
      final List<EventAlarm> _result = new ArrayList<EventAlarm>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final EventAlarm _item;
        _item = new EventAlarm();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpMsg_alarm;
        _tmpMsg_alarm = _cursor.getString(_cursorIndexOfMsgAlarm);
        _item.setMsg_alarm(_tmpMsg_alarm);
        final String _tmpTime_alarm;
        _tmpTime_alarm = _cursor.getString(_cursorIndexOfTimeAlarm);
        _item.setTime_alarm(_tmpTime_alarm);
        final String _tmpDate_alarm;
        _tmpDate_alarm = _cursor.getString(_cursorIndexOfDateAlarm);
        _item.setDate_alarm(_tmpDate_alarm);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
