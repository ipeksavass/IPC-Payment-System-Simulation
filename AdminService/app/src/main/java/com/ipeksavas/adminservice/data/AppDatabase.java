package com.ipeksavas.adminservice.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ipeksavas.adminservice.data.dao.ReceiptDao;
import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;

@Database(
        entities = {Receipt.class, ReceiptItem.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReceiptDao receiptDao();
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "admin_service_database";
    
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME
                            )
                            //.addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    
    /*
    INDUSTRY STANDARD AND LIVE ENVIRONMENT MIGRATION GUIDE:
    If a change to the database schema is required in the future (for example, adding a new column
    to the Receipt table or upgrading to version 2), a migration definition like the one below is
    written to update the data without deleting user data:
    
    static final Migration MIGRATION_1_2 = new Migration(1,2){
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database){
            database.execSQL("our SQL migration query here");
        }
     */
}
