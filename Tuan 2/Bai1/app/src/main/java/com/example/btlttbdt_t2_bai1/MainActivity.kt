@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.btlttbdt_t2_bai1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgeCheckScreen()
        }
    }
}

@Composable
fun AgeCheckScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tiêu đề
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // KHUNG XÁM
        Box(
            modifier = Modifier
                .width(300.dp)                                  // Cố định độ rộng
                .background(
                    color = Color(0xFFD3D3D3),                 // Màu xám nhạt
                    shape = RoundedCornerShape(10.dp)          // Bo góc khung
                )
                .padding(16.dp)                                // Khoảng trống bên trong
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                // DÒNG 1: "Họ và tên" + TextField
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    // Nhãn "Họ và tên"
                    Text(
                        text = "Họ và tên",
                        fontSize = 16.sp,
                        modifier = Modifier.width(80.dp),       // Độ rộng cột nhãn
                        fontWeight = FontWeight.Bold
                    )
                    // Ô nhập liệu
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .height(45.dp),                     // Chiều cao ô nhập
                        shape = RoundedCornerShape(8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        )
                    )
                }

                // DÒNG 2: "Tuổi" + TextField
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    // Nhãn "Tuổi"
                    Text(
                        text = "Tuổi",
                        fontSize = 16.sp,
                        modifier = Modifier.width(80.dp),
                        fontWeight = FontWeight.Bold
                    )
                    // Ô nhập liệu
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .height(45.dp),
                        shape = RoundedCornerShape(8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // NÚT "Kiểm tra"
        Button(
            onClick = {
                result = checkAgeCategory(age)
            },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007BFF) // Màu xanh
            )
        ) {
            Text(
                text = "Kiểm tra",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // HIỂN THỊ KẾT QUẢ
        if (result.isNotEmpty()) {
            Text(
                text = result,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
    }
}

// Hàm kiểm tra tuổi
fun checkAgeCategory(age: String): String {
    val ageInt = age.toIntOrNull()
    return when {
        ageInt == null -> "Vui lòng nhập số hợp lệ!"
        ageInt > 65 -> "Người già (>65)"
        ageInt in 6..65 -> "Người lớn (6-65)"
        ageInt in 2..6 -> "Trẻ em (2-6)"
        ageInt > 0 -> "Em bé (>2)"
        else -> "Tuổi không hợp lệ!"
    }
}
