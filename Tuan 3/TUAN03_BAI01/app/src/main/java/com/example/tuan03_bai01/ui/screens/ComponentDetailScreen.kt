package com.example.tuan03_bai01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // 🔥 Import để lấy ViewModel
import androidx.navigation.NavController
import com.example.tuan03_bai01.R
// 🔥 Không import UIComponentRepository nữa
// Import các Demo Composable (giữ nguyên)
import com.example.tuan03_bai01.ui.components.demos.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailScreen(
    navController: NavController,
    // 🔥 Không cần truyền componentName vào đây nữa
    // 🔥 Khởi tạo ViewModel bằng hàm viewModel()
    viewModel: ComponentDetailViewModel = viewModel()
) {
    // 🔥 Lắng nghe và lấy trạng thái UI từ ViewModel
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        // 🔥 Lấy tên component từ uiState
                        text = "${uiState.componentName} Demo",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            // Điều chỉnh padding để tiêu đề thực sự ở giữa khi có icon back
                            .padding(end = 48.dp) // 48dp là kích thước thường thấy của icon button
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Điều hướng back vẫn ở View
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        // 🔥 Column chứa nội dung, áp dụng padding từ Scaffold và cho phép cuộn
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Áp dụng padding an toàn từ Scaffold
                .verticalScroll(scrollState) // Cho phép cuộn
                .padding(horizontal = 16.dp) // Padding ngang cho nội dung bên trong
        ) {
            // 🔥 Box để hiển thị loading hoặc error ở giữa phần nội dung
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)) { // Thêm padding dọc để không dính sát AppBar
                when {
                    // 1. Trạng thái đang tải
                    uiState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    // 2. Trạng thái lỗi
                    uiState.error != null -> {
                        Text(
                            text = uiState.error ?: "An unexpected error occurred",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp)
                        )
                    }
                    // 3. Trạng thái thành công -> Hiển thị mô tả và demo
                    else -> {
                        Column { // Column riêng cho nội dung thành công
                            // Hiển thị Mô tả Component từ uiState
                            Text(
                                text = uiState.componentDescription,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp) // Khoảng cách trên/dưới mô tả
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )

                            Divider(modifier = Modifier.padding(vertical = 16.dp)) // Ngăn cách

                            // 🔥 Hiển thị Demo dựa trên componentName từ uiState
                            ShowComponentDemo(componentName = uiState.componentName)

                            Spacer(modifier = Modifier.height(16.dp)) // Khoảng trống dưới cùng
                        }
                    }
                }
            }
        }
    }
}

/**
 * Composable phụ trợ để hiển thị Composable Demo tương ứng.
 * Giúp cho ComponentDetailScreen gọn gàng hơn.
 */
@Composable
private fun ShowComponentDemo(componentName: String) {
    // Khối when để chọn và hiển thị Demo phù hợp
    when (componentName) {
        "Text" -> TextDemo()
        "Image" -> ImageDemo()
        "TextField" -> TextFieldDemo()
        "PasswordField" -> PasswordFieldDemo()
        "Column" -> ColumnDemo()
        "Row" -> RowDemo()
        // Trường hợp mặc định nếu componentName không khớp
        else -> {
            Text(
                text = "Demo for '$componentName' is not implemented yet.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Màu chữ nhẹ nhàng
            )
        }
    }
}