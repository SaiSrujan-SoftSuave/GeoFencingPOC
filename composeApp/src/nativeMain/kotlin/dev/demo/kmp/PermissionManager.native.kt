package dev.demo.kmp

import kotlinx.cinterop.ObjCAction
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.darwin.NSObject
import kotlin.coroutines.resume

actual class PermissionManager : NSObject(), CLLocationManagerDelegateProtocol {
    private var locationManager: CLLocationManager? = null
    private var permissionContinuation: CancellableContinuation<Boolean>? = null

    actual suspend fun requestLocationPermission(): Boolean =
        suspendCancellableCoroutine { continuation ->
            locationManager = CLLocationManager().apply {
                delegate = this@PermissionManager
                requestWhenInUseAuthorization()
            }
            permissionContinuation = continuation
        }

    actual suspend fun hasLocationPermission(): Boolean {
        return CLLocationManager.authorizationStatus() == kCLAuthorizationStatusAuthorizedWhenInUse ||
                CLLocationManager.authorizationStatus() == kCLAuthorizationStatusAuthorizedAlways
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @ObjCAction
    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
        val granted = didChangeAuthorizationStatus == kCLAuthorizationStatusAuthorizedWhenInUse ||
                didChangeAuthorizationStatus == kCLAuthorizationStatusAuthorizedAlways
        permissionContinuation?.resume(granted)
        permissionContinuation = null
    }
}