package com.example.lttbdd_t2_b2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lttbdd_t2_b2.ui.viewmodel.LibraryViewModel

@Composable
fun UsersScreen(viewModel: LibraryViewModel) {
    var newUser by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Danh sách nhân viên",
            style = MaterialTheme.typography.headlineMedium
        )

        // Danh sách nhân viên
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = user,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = { viewModel.removeUser(user) }) {
                            Text("Xóa")
                        }
                    }
                }
            }
        }

        // Thêm nhân viên mới
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newUser,
            onValueChange = { newUser = it },
            label = { Text("Tên nhân viên mới") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (newUser.isNotBlank()) {
                    viewModel.addUser(newUser)
                    newUser = ""
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Thêm nhân viên")
        }
    }
}
