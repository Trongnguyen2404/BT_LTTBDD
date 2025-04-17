package com.example.tuan03_bai01.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // 🔥 Import để lấy ViewModel
import androidx.navigation.NavController
// 🔥 Không import UIComponentRepository nữa
import com.example.tuan03_bai01.navigation.Screen

@Composable
fun ComponentsListScreen(
    navController: NavController,
    // 🔥 Khởi tạo ViewModel bằng hàm viewModel()
    viewModel: ComponentsListViewModel = viewModel()
) {
    // 🔥 Lắng nghe và lấy trạng thái UI từ ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            // Sử dụng padding chuẩn của Material Design hoặc tùy chỉnh nhẹ nhàng hơn
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Tiêu đề chính
        Text(
            text = "UI Components List",
            style = MaterialTheme.typography.headlineMedium, // Có thể dùng headlineMedium
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary, // Dùng màu từ theme
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), // Tăng padding dọc cho tiêu đề
            textAlign = TextAlign.Center
        )

        // 🔥 Hiển thị nội dung dựa trên trạng thái từ ViewModel
        Box(modifier = Modifier.fillMaxSize()) { // Box bao ngoài để căn giữa loading/error
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
                // 3. Trạng thái thành công
                else -> {
                    // 🔥 Sử dụng LazyColumn để hiển thị danh sách từ uiState
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp) // Khoảng cách giữa các item/nhóm
                    ) {
                        // Duyệt qua danh sách đã nhóm từ uiState
                        uiState.groupedComponents.forEach { (category, components) ->
                            // a. Hiển thị tiêu đề nhóm
                            item {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, bottom = 8.dp), // Khoảng cách trên/dưới tiêu đề nhóm
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // b. Hiển thị danh sách button cho từng component trong nhóm
                            items(components) { component ->
                                Button(
                                    // Điều hướng vẫn thực hiện ở View
                                    onClick = { navController.navigate(Screen.ComponentDetail.createRoute(component.name)) },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Màu nền nhẹ nhàng hơn
                                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 12.dp, horizontal = 8.dp), // Padding bên trong button
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            text = component.name,
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold), // Dùng titleMedium
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = component.description,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                    }
                                }
                            }

                            // c. Thêm Divider sau mỗi nhóm (trừ nhóm cuối cùng nếu muốn)
                            item {
                                Divider(modifier = Modifier.padding(top = 16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}