package com.example.tuan03_bai01.ui.components.demos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear // Icon xóa
import androidx.compose.material.icons.filled.Email // Icon email
import androidx.compose.material.icons.filled.Info // Icon lỗi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable // Để lưu trạng thái khi xoay màn hình
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager // Để quản lý focus
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class) // Cần cho TextField
@Composable
fun TextFieldDemo() {
    // State cho các TextField, sử dụng rememberSaveable để giữ giá trị khi cấu hình thay đổi (vd: xoay màn hình)
    var basicText by rememberSaveable { mutableStateOf("") }
    var outlinedText by rememberSaveable { mutableStateOf("") }
    var leadingIconText by rememberSaveable { mutableStateOf("") }
    var errorText by rememberSaveable { mutableStateOf("invalid input") } // Giả sử có lỗi ban đầu
    var isError by rememberSaveable { mutableStateOf(true) } // Trạng thái lỗi

    val focusManager = LocalFocusManager.current // Lấy focus manager

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Khoảng cách giữa các thành phần con
    ) {
        // --- Ví dụ 1: Basic TextField ---
        Text("Basic TextField", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))
        TextField(
            value = basicText,
            onValueChange = { basicText = it },
            label = { Text("Enter text") }, // Nhãn hiển thị khi trống và thu nhỏ khi có focus/dữ liệu
            singleLine = true, // Chỉ cho phép nhập một dòng
            modifier = Modifier.fillMaxWidth()
        )

        Divider()

        // --- Ví dụ 2: Outlined TextField ---
        Text("Outlined TextField", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = outlinedText,
            onValueChange = { outlinedText = it },
            label = { Text("Outlined") },
            placeholder = { Text("Placeholder text") }, // Chữ gợi ý khi trống và không có focus
            modifier = Modifier.fillMaxWidth()
        )

        Divider()

        // --- Ví dụ 3: TextField với Leading/Trailing Icons ---
        Text("TextField with Icons", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = leadingIconText,
            onValueChange = { leadingIconText = it },
            label = { Text("Email Address") },
            leadingIcon = { // Icon ở đầu
                Icon(Icons.Default.Email, contentDescription = "Email Icon")
            },
            trailingIcon = { // Icon ở cuối (ví dụ: nút xóa)
                if (leadingIconText.isNotEmpty()) { // Chỉ hiện khi có text
                    IconButton(onClick = { leadingIconText = "" }) { // Xóa text khi nhấn
                        Icon(Icons.Default.Clear, contentDescription = "Clear Text")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, // Bàn phím dạng email
                imeAction = ImeAction.Done // Hành động nút Enter/Done trên bàn phím
            ),
            keyboardActions = KeyboardActions(
                onDone = { // Xử lý khi nhấn nút Done
                    println("Email entered: $leadingIconText") // In ra log hoặc xử lý khác
                    focusManager.clearFocus() // Ẩn bàn phím
                }
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Divider()

        // --- Ví dụ 4: TextField với Trạng thái Lỗi ---
        Text("TextField with Error State", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = errorText,
            onValueChange = {
                errorText = it
                // Kiểm tra lỗi đơn giản: không được rỗng
                isError = it.isBlank()
            },
            label = { Text("Input with validation") },
            isError = isError, // Đặt trạng thái lỗi
            supportingText = { // Hiển thị text hỗ trợ hoặc thông báo lỗi
                if (isError) {
                    Text(
                        text = "Field cannot be empty",
                        color = MaterialTheme.colorScheme.error // Màu lỗi từ theme
                    )
                } else {
                    Text("Looks good!")
                }
            },
            trailingIcon = {
                if (isError) {
                    Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}