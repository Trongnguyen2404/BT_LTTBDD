package com.example.myapplication_kt // Khai báo package của ứng dụng

import android.os.Bundle // Import lớp Bundle để quản lý trạng thái khi Activity được tạo lại
import androidx.activity.ComponentActivity // Import ComponentActivity - Activity cơ bản trong Android
import androidx.activity.compose.setContent // Import hàm setContent để đặt giao diện Compose

// Lớp MainActivity kế thừa từ ComponentActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // Phương thức được gọi khi Activity được tạo
        super.onCreate(savedInstanceState) // Gọi phương thức onCreate của lớp cha
        setContent { // Thiết lập giao diện của Activity bằng Jetpack Compose
            ProfileScreen() // Gọi hàm Composable ProfileScreen để hiển thị giao diện hồ sơ
        }
    }
}
