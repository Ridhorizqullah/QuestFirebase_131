package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.repositori.RepositorySiswa
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<Siswa> = listOf()) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

/**
 * ViewModel untuk halaman utama (Home).
 * Bertanggung jawab untuk mengambil dan menampilkan daftar siswa.
 *
 * @property repositorySiswa Repository untuk mengambil data siswa
 */
class HomeViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {
    var statusUiSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            statusUiSiswa = StatusUiSiswa.Loading
            try {
                val data = repositorySiswa.getDataSiswa()
                android.util.Log.d("HomeViewModel", "Data loaded: ${data.size} items")
                statusUiSiswa = StatusUiSiswa.Success(data)
            } catch (e: IOException) {
                android.util.Log.e("HomeViewModel", "Network error", e)
                statusUiSiswa = StatusUiSiswa.Error
            } catch (e: Exception) {
                android.util.Log.e("HomeViewModel", "Unknown error", e)
                statusUiSiswa = StatusUiSiswa.Error
            }
        }
    }
}
