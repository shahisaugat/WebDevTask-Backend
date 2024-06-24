package com.example.webdevtask.service.implementor;

import com.example.webdevtask.entity.Book;
import com.example.webdevtask.entity.Ground;
import com.example.webdevtask.entity.User;
import com.example.webdevtask.pojo.BookPojo;
import com.example.webdevtask.repo.BookRepo;
import com.example.webdevtask.repo.GroundRepo;
import com.example.webdevtask.repo.UserRepo;
import com.example.webdevtask.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookImpl implements BookService {

    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private final GroundRepo groundRepo;

    @Override
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Long bookingId) {
        return bookRepo.findById(bookingId);
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepo.existsById(id);
    }

    @Override
    public void saveData(BookPojo bookPojo) {
        Book book = new Book();
        book.setUserName(bookPojo.getUserName());

        // Fetch and set User entity
        User user = userRepo.findById(bookPojo.getUser().getId().longValue())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        book.setUser(user);

        // Fetch and set Ground entity
        Ground ground = groundRepo.findById(bookPojo.getGround().getId().longValue())
                .orElseThrow(() -> new EntityNotFoundException("Ground not found"));
        book.setGround(ground);

        bookRepo.save(book);
    }

    @Override
    public List<Book> getAll() {
        System.out.println("Hello honey bunny");
        return bookRepo.findAll();
    }

    @Override
    public void updateData(Long id, BookPojo bookPojo) {
        Optional<Book> teacherAOptional = bookRepo.findById(id);
        if (teacherAOptional.isPresent()) {
            Book existingTeacher = teacherAOptional.get();
            updateStudentProperties(existingTeacher, bookPojo);
            bookRepo.save(existingTeacher); // Save the updated student
        } else {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
    }

    private void updateStudentProperties(Book book, BookPojo bookPojo) {
        User user = userRepo.findById(bookPojo.getUser().getId().longValue())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        book.setUser(user);

        Ground ground = groundRepo.findById(bookPojo.getGround().getId().longValue())
                .orElseThrow(() -> new EntityNotFoundException("Ground not found"));
        book.setGround(ground);

        book.setUserName(bookPojo.getUserName());
        System.out.println("Attendance before save: " + book);
        bookRepo.save(book);
    }
}
