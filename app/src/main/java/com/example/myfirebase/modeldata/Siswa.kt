package com.example.myfirebase.modeldata

/**
 * Model data yang merepresentasikan entitas Siswa.
 *
 * @property id ID unik siswa (Long)
 * @property nama Nama lengkap siswa
 * @property alamat Alamat tempat tinggal siswa
 * @property telpon Nomor telepon siswa
 */
data class Siswa(
    val id: Long = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

data class DetailSiswa(
    val id: Long = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
) {
    /**
     * Memvalidasi apakah data siswa valid (tidak kosong).
     */
    fun isValid(): Boolean {
        return nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
    }
}

fun DetailSiswa.toDataSiswa(): Siswa = Siswa(id, nama, alamat, telpon)
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(id, nama, alamat, telpon)

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

fun Siswa.toUIStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)
