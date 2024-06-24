package com.example.webdevtask.controller;

import com.example.webdevtask.entity.Book;
import com.example.webdevtask.pojo.BookPojo;
import com.example.webdevtask.service.BookService;
import com.example.webdevtask.shared.pojo.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.example.webdevtask.shared.pojo.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@Service
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/get")
    public GlobalApiResponse<List<Book>> getData() {
        return GlobalApiResponse.
                <List<Book>>builder()
                .data(bookService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody BookPojo bookPojo) {
        if (!bookService.existsById(id.longValue())) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }
        bookService.updateData(id.longValue(), bookPojo);
        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }
    @PostMapping("/save")
    public void save(@RequestBody BookPojo bookPojo) {
        this.bookService.saveData(bookPojo);
    }
    @GetMapping("/get/{id}")
    public Optional<Book> getData(@PathVariable Long id) {
        return bookService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.bookService.deleteById(id);
    }
}
