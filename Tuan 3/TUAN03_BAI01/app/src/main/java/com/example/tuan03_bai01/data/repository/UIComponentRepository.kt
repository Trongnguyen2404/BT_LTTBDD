package com.example.tuan03_bai01.data.repository

import com.example.tuan03_bai01.data.model.UIComponent

object UIComponentRepository {
    val groupedComponents = listOf(
        "Display" to listOf(
            UIComponent("Text", "Displays text with various styling options."), // Cập nhật mô tả cho rõ hơn
            UIComponent("Image", "Displays bitmap and vector graphics.") // Cập nhật mô tả
        ),
        "Input" to listOf(
            UIComponent("TextField", "Allows users to enter and modify text."), // Cập nhật mô tả
            UIComponent("PasswordField", "A TextField specialized for password input.") // Cập nhật mô tả
        ),
        "Layout" to listOf(
            UIComponent("Column", "Arranges child composables vertically."), // Cập nhật mô tả
            UIComponent("Row", "Arranges child composables horizontally.") // Cập nhật mô tả
        )
    )

    // 🔥 Hàm mới: Tìm UIComponent dựa vào tên
    fun findComponentByName(name: String): UIComponent? {
        return groupedComponents.flatMap { it.second }.find { it.name == name }
    }

    // 🔥 Hàm mới: Tìm mô tả dựa vào tên (tiện lợi hơn)
    fun findDescriptionByName(name: String): String {
        return findComponentByName(name)?.description ?: "No description available."
    }
}