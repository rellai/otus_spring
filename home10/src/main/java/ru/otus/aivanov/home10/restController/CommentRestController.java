package ru.otus.aivanov.home10.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.aivanov.home10.dto.CommentCreateDto;
import ru.otus.aivanov.home10.dto.CommentDto;
import ru.otus.aivanov.home10.dto.CommentUpdateDto;
import ru.otus.aivanov.home10.services.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping("/api/comments")
    public List<CommentDto> getAllComments(@RequestParam("bookId") long bookId) {
        return commentService.findAllByBook(bookId);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") long id) {
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/comments")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentCreateDto comment) {
        commentService.create(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") long id,
                                           @Valid @RequestBody CommentUpdateDto comment) throws Exception {
        if (comment.id() != id) {
            throw new Exception("JSON is not valid:id not equal path variable /api/comments/{id}");
        }
        commentService.update(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
