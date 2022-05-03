package game.ceelo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import game.ceelo.repository.entity.DicesThrow
import game.ceelo.repository.CeeloRepository

@Database(entities = [DicesThrow::class], version = 1)
abstract class CeeloDataBase  : RoomDatabase() {
    abstract fun dicesThrowRepository(): CeeloRepository?
    companion object {
        const val DATABASE_NAME = "CeeloDataBase.db"
        @Volatile
        private var instance: CeeloDataBase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): CeeloDataBase? {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            CeeloDataBase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return instance
        }
    }
}
/*

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import education.cccp.mobile.room.dao.UserDao
import education.cccp.mobile.room.dao.converter.JavaUtilDateConverter
import education.cccp.mobile.room.model.User

@Database(entities = [User::class], version = 1)
@TypeConverters(value = [JavaUtilDateConverter::class])
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private const val DB_NAME = "application.db"

        @Volatile
        private var instance: AppDb? = null
        private val LOCK = Any()
        fun getInstance(context: Context): AppDb? {
            if (instance == null) synchronized(LOCK) {
                if (instance == null) instance = databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    DB_NAME
                ).build()
            }
            return instance
        }
    }
}
 */