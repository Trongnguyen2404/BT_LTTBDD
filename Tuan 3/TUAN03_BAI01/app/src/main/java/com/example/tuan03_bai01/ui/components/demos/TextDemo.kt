package com.example.tuan03_bai01.ui.components.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDemo() {
    Column(modifier = Modifier.padding(16.dp)) {
        // --- Ví dụ 1: Text Cơ bản ---
        Text(
                text = "Basic Text",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
                text = "This is a simple Text composable displaying a static string.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
        )
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 2: Text với Tùy chỉnh ---
        Text(
                text = "Customized Text",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
                text = "This text is customized with blue color, larger font size, bold weight, and centered alignment. It also demonstrates overflow handling with ellipsis.",
                color = Color.Blue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 1.5.sp,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                maxLines = 3, // Giới hạn số dòng
                overflow = TextOverflow.Ellipsis, // Hiển thị ... nếu quá dài
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
        )
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 3: Styled Text (AnnotatedString) ---
        Text(
                text = "Styled Text (AnnotatedString)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
        )
        StyledTextExampleInternal() // Gọi hàm đã có từ trước
    }
}

// Đổi tên hàm gốc để tránh trùng lặp và giữ nó trong file này
@Composable
private fun StyledTextExampleInternal() {
    Text(
            text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("The ") }
        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick ") }
        withStyle(style = SpanStyle(color = Color(0xFFB5651D), fontSize = 24.sp)) { append("B") } // Điều chỉnh size
        withStyle(style = SpanStyle(color = Color(0xFFB5651D), fontSize = 20.sp)) { append("rown ") } // Điều chỉnh size
        append("fox ")
        withStyle(style = SpanStyle(letterSpacing = 3.sp)) { append("jumps ") }
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic)) { append("over ") }
        withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)) { append("the ") }
        withStyle(style = SpanStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Medium)) { append("lazy ") }
        append("dog.")
    },
    fontSize = 18.sp, // Điều chỉnh size chung
            letterSpacing = 1.sp,
            lineHeight = 28.sp,
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Thêm padding dọc
    )
}