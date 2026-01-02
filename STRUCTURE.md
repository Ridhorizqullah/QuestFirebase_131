# Struktur Folder Proyek MyFirebase

Dokumen ini menjelaskan struktur folder dan paket dalam aplikasi MyFirebase.

## `com.example.myfirebase`

### `modeldata`
Berisi kelas-kelas data (Data Classes) yang merepresentasikan objek dalam aplikasi.
- `Siswa.kt`: Model data untuk entitas Siswa.

### `repositori`
Layer repository untuk mengelola data dari sumber data (Firestore).
- `RepositorySiswa.kt`: Interface untuk operasi CRUD siswa.
- `NetworkRepositorySiswa.kt`: Implementasi repository menggunakan Firestore.

### `viewmodel`
State holder untuk UI, menghubungkan repository dengan UI.
- `EntryViewModel.kt`: ViewModel untuk halaman input data.
- `HomeViewModel.kt`: ViewModel untuk halaman utama/daftar siswa.
- `PenyediaViewModel.kt`: Factory untuk menyediakan instance ViewModel.

### `view`
Komponen UI yang dibangun menggunakan Jetpack Compose.
- `HalamanHome.kt`: Layar utama menampilkan daftar siswa.
- `HalamanEntry.kt`: Layar untuk menambah data siswa.
- `SiswaTopAppBar.kt`: Komponen AppBar kustom.

### `ui`
Konfigurasi tema dan utilitas UI.
- `Theme.kt`: Definisi tema aplikasi.
- `Color.kt`: Definisi warna.
- `Type.kt`: Definisi tipografi.
