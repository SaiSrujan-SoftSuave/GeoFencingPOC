package dev.demo.kmp

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.koin.core.context.GlobalContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class PermissionManager {
    private val context: Context =
        GlobalContext.get().get<Context>()

    actual suspend fun requestLocationPermission(): Boolean {
        return suspendCoroutine { continuation ->
            val activity = context as? Activity
            if (activity == null) {
                continuation.resume(false)
                return@suspendCoroutine
            }

            val permission = ACCESS_FINE_LOCATION
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                continuation.resume(true)
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), 1001)
                // Handle in Activity's onRequestPermissionsResult
                continuation.resume(false) // You'll need to communicate real result using a callback
            }
        }
    }

    actual suspend fun hasLocationPermission(): Boolean {
        val permission = ACCESS_FINE_LOCATION
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}