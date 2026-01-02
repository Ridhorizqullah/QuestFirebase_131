# Strategi Pengujian MyFirebase

Dokumen ini menjelaskan pendekatan pengujian untuk aplikasi MyFirebase.

## Unit Testing
Unit test digunakan untuk menguji logika bisnis di ViewModel dan Repository.

### ViewModel Tests
- Menguji state awal ViewModel
- Menguji perubahan state setelah memuat data sukses
- Menguji perubahan state saat terjadi error

### Repository Tests
- Menguji fungsi mapping data dari Firestore ke model lokal
- Menguji penanganan exception saat koneksi gagal

## UI Testing
UI test menggunakan Compose Test Rule untuk memverifikasi tampilan.

### Skenario Pengujian
1. **Halaman Home**: Memastikan daftar siswa muncul
2. **Halaman Entry**: Memastikan validasi form berfungsi
3. **Navigasi**: Memastikan perpindahan antar halaman berjalan lancar

## Cara Menjalankan Test
Jalankan perintah berikut di terminal:
```bash
./gradlew testDebugUnitTest
```
