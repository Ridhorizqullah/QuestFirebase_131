package com.example.myfirebase.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.view.route.DestinasiDetail
import com.example.myfirebase.viewmodel.DetailViewModel
import com.example.myfirebase.viewmodel.PenyediaViewModel
import com.example.myfirebase.viewmodel.StatusUIDetail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    navigateToEditItem: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        DetailStatus(
            statusUIDetail = viewModel.statusUIDetail,
            retryAction = { viewModel.getSatuSiswa() },
            modifier = Modifier.padding(innerPadding),
            onEditClick = navigateToEditItem,
            onDeleteClick = {
                coroutineScope.launch {
                    viewModel.hapusSatuSiswa()
                    navigateBack()
                }
            }
        )
    }
}

@Composable
fun DetailStatus(
    statusUIDetail: StatusUIDetail,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: (Long) -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    when (statusUIDetail) {
        is StatusUIDetail.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is StatusUIDetail.Success -> {
            if (statusUIDetail.satusiswa == null) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan")
                }
            } else {
                ItemDetailSiswa(
                    siswa = statusUIDetail.satusiswa,
                    modifier = modifier.fillMaxWidth(),
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
        is StatusUIDetail.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailSiswa(
    siswa: Siswa,
    modifier: Modifier = Modifier,
    onEditClick: (Long) -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ComponentDetailSiswaItem(judul = "ID", isinya = siswa.id.toString())
                ComponentDetailSiswaItem(judul = "Nama", isinya = siswa.nama)
                ComponentDetailSiswaItem(judul = "Alamat", isinya = siswa.alamat)
                ComponentDetailSiswaItem(judul = "Telpon", isinya = siswa.telpon)
            }
        }

        Button(
            onClick = { onEditClick(siswa.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit")
        }

        OutlinedButton(
            onClick = onDeleteClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
    }
}

@Composable
fun ComponentDetailSiswaItem(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = judul,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = isinya,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
