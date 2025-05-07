package dev.demo.kmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
) : ViewModel() {
    private val permissionManager: PermissionManager = PermissionManager()
    private val _permissionStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val permissionStatus: StateFlow<Boolean> = _permissionStatus

    fun requestLocationPermission() {
        viewModelScope.launch {
            if (!_permissionStatus.value) {
                _permissionStatus.value = permissionManager.requestLocationPermission()
            }
        }
    }

    fun hasLocationPermission() {
        viewModelScope.launch {
            _permissionStatus.value = permissionManager.hasLocationPermission()
        }
    }


}