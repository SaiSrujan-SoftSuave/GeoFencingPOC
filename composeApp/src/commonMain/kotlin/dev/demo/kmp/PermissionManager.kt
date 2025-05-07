package dev.demo.kmp

expect class PermissionManager() {
    suspend fun requestLocationPermission(): Boolean
    suspend fun hasLocationPermission(): Boolean
}