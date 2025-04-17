package com.example.lttbdd_t2_b2.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {
    // Danh sách sách
    val books = mutableStateListOf("Sách 01", "Sách 02", "Sách 03")

    // Danh sách người dùng
    val users = mutableStateListOf("Nguyễn Văn A", "Trần Thị B")

    // Map (Sách -> Tên người mượn)
    val borrowedBooks = mutableStateMapOf<String, String>()

    fun addBook(newBook: String) {
        books.add(newBook)
    }

    fun removeBook(book: String) {
        books.remove(book)
        borrowedBooks.remove(book) // Xóa cả thông tin mượn
    }

    fun addUser(newUser: String) {
        users.add(newUser)
    }

    fun removeUser(user: String) {
        users.remove(user)
    }

    fun borrowBook(book: String, borrowerName: String) {
        borrowedBooks[book] = borrowerName
    }

    fun returnBook(book: String) {
        borrowedBooks.remove(book)
    }
}
