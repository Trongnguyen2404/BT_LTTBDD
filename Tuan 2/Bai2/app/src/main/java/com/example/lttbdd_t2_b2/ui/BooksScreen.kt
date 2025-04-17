package com.example.lttbdd_t2_b2.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lttbdd_t2_b2.ui.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(viewModel: LibraryViewModel) {
    var newBookTitle by remember { mutableStateOf("") }

    // Khi bấm vào sách, ta lưu sách được chọn vào selectedBook
    var selectedBook by remember { mutableStateOf<String?>(null) }

    // Biến cho biết cách mượn:
    // true = chọn user có sẵn, false = nhập tên mới
    var useExistingUser by remember { mutableStateOf(true) }

    // Tên user được chọn từ danh sách
    var selectedUser by remember { mutableStateOf<String?>(null) }

    // Tên user nhập thủ công
    var borrowerName by remember { mutableStateOf("") }

    // Dialog mượn sách
    if (selectedBook != null) {
        AlertDialog(
            onDismissRequest = {
                // Đóng dialog
                selectedBook = null
                borrowerName = ""
                selectedUser = null
            },
            title = { Text("Mượn sách: $selectedBook") },
            text = {
                Column {
                    Text("Chọn phương thức mượn:", style = MaterialTheme.typography.titleMedium)

                    // RadioButton: 1) Chọn user có sẵn
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        RadioButton(
                            selected = useExistingUser,
                            onClick = { useExistingUser = true }
                        )
                        Text("Chọn user có sẵn")
                    }

                    // RadioButton: 2) Nhập tên mới
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        RadioButton(
                            selected = !useExistingUser,
                            onClick = { useExistingUser = false }
                        )
                        Text("Nhập tên mới")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nếu chọn user sẵn có, hiển thị ExposedDropdownMenu
                    if (useExistingUser) {
                        var expanded by remember { mutableStateOf(false) }

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            OutlinedTextField(
                                value = selectedUser ?: "",
                                onValueChange = { },
                                readOnly = true,
                                label = { Text("Chọn user từ danh sách") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                // Nếu menuAnchor() gạch đỏ, hãy xóa .menuAnchor() hoặc nâng cấp Compose lên 1.5.0
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                viewModel.users.forEach { user ->
                                    DropdownMenuItem(
                                        text = { Text(user) },
                                        onClick = {
                                            selectedUser = user
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        // Nếu nhập tên mới
                        OutlinedTextField(
                            value = borrowerName,
                            onValueChange = { borrowerName = it },
                            label = { Text("Tên người mượn") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    // Xác nhận mượn sách
                    if (useExistingUser) {
                        // Mượn bằng user sẵn có
                        if (!selectedUser.isNullOrBlank()) {
                            viewModel.borrowBook(selectedBook!!, selectedUser!!)
                            selectedBook = null
                            borrowerName = ""
                            selectedUser = null
                        }
                    } else {
                        // Mượn bằng user mới nhập
                        if (borrowerName.isNotBlank()) {
                            viewModel.borrowBook(selectedBook!!, borrowerName)
                            selectedBook = null
                            borrowerName = ""
                            selectedUser = null
                        }
                    }
                }) {
                    Text("Xác nhận")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    selectedBook = null
                    borrowerName = ""
                    selectedUser = null
                }) {
                    Text("Hủy")
                }
            }
        )
    }

    // Layout chính
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Danh sách sách",
            style = MaterialTheme.typography.headlineMedium
        )

        // Danh sách sách
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.books) { book ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        // Bấm vào card => mở dialog mượn sách
                        .clickable {
                            selectedBook = book
                            useExistingUser = true  // Mặc định chọn user có sẵn
                            borrowerName = ""
                            selectedUser = null
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = book,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = { viewModel.removeBook(book) }) {
                            Text("Xóa")
                        }
                    }
                }
            }
        }

        // Thêm sách mới
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newBookTitle,
            onValueChange = { newBookTitle = it },
            label = { Text("Tên sách mới") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (newBookTitle.isNotBlank()) {
                    viewModel.addBook(newBookTitle)
                    newBookTitle = ""
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Thêm sách")
        }
    }
}
