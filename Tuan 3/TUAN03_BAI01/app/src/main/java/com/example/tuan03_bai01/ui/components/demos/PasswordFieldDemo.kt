package com.example.tuan03_bai01.ui.components.demos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock // Icon khóa
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation // Để ẩn/hiện password
import androidx.compose.ui.unit.dp
import androidx.lint.kotlin.metadata.Visibility

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldDemo() {
    // State cho ví dụ OutlinedTextField
    var outlinedPassword by rememberSaveable { mutableStateOf("") }
    var outlinedPasswordVisible by rememberSaveable { mutableStateOf(false) }

    // State riêng cho ví dụ TextField (Standard/Filled)
    var standardPassword by rememberSaveable { mutableStateOf("") }
    var standardPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Khoảng cách giữa các phần tử
    ) {
        // --- Ví dụ 1: Password Field với OutlinedTextField ---
        Text("Password Field (Outlined)", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = outlinedPassword,
            onValueChange = { outlinedPassword = it },
            label = { Text("Enter password (Outlined)") },
            singleLine = true,
            placeholder = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            visualTransformation = if (outlinedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done // Đổi thành Done cho ví dụ này
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    println("Outlined Password submitted (length: ${outlinedPassword.length})")
                    focusManager.clearFocus()
                }
            ),
            trailingIcon = {
                val image = if (outlinedPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (outlinedPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { outlinedPasswordVisible = !outlinedPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp)) // Ngăn cách giữa hai ví dụ

        // --- Ví dụ 2: Password Field với TextField (Standard/Filled) ---
        Text("Password Field (Standard/Filled)", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))

        TextField( // 🔥 Sử dụng TextField thay vì OutlinedTextField
            value = standardPassword,
            onValueChange = { standardPassword = it },
            label = { Text("Enter password (Standard)") }, // Nhãn khác
            singleLine = true,
            placeholder = { Text("Password") },
            leadingIcon = { // Vẫn có thể thêm leading icon
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            visualTransformation = if (standardPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go // Đổi thành Go cho ví dụ này
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    println("Standard Password submitted (length: ${standardPassword.length})")
                    focusManager.clearFocus()
                }
            ),
            trailingIcon = {
                val image = if (standardPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (standardPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { standardPasswordVisible = !standardPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}