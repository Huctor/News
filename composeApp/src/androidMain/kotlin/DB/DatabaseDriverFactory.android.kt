package DB

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.newsapp.database.NewsDatabase
import android.content.Context

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(NewsDatabase.Schema, context, "NewsDatabase.db")
    }
}