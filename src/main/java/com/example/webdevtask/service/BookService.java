package com.example.webdevtask.service;

import com.example.webdevtask.entity.Book;
import com.example.webdevtask.pojo.BookPojo;

import java.util.List;
import java.util.Optional;

import com.example.webdevtask.entity.Book;
import com.example.webdevtask.pojo.BookPojo;
import com.example.webdevtask.pojo.RolePojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public interface BookService {

    void saveData(BookPojo bookPojo);
    List<Book> getAll();

    void updateData(Long id,BookPojo bookPojo);

    void deleteById(Long id);
    Optional<Book> findById(Long id);
    boolean existsById(Long id);
}