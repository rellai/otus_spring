package ru.otus.aivanov.home08.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.aivanov.home08.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
