package com.example.lttbdd_t2_b2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lttbdd_t2_b2.ui.viewmodel.LibraryViewModel

@Composable
fun BorrowScreen(viewModel: LibraryViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Danh sách sách đã mượn",
            style = MaterialTheme.typography.headlineMedium
        )

        // Danh sách sách đã mượn
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.borrowedBooks.entries.toList()) { (book, user) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "$book - $user",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = { viewModel.returnBook(book) }) {
                            Text("Trả")
                        }
                    }
                }
            }
        }
    }
}
