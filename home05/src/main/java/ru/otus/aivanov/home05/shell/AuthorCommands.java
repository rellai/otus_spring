package ru.otus.aivanov.home05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.aivanov.home05.converters.AuthorConverter;
import ru.otus.aivanov.home05.models.Author;
import ru.otus.aivanov.home05.services.AuthorService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "Find all authors", key = "aa")
    public String findAllAuthors() {
        return authorService.findAll().stream()
                .map(authorConverter::authorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Delete author", key = "da")
    public long deleteGenre(long id) {
        return authorService.deleteById(id);
    }

    @ShellMethod(value = "Find author by id", key = "fa")
    public String findById(long id) {
        return authorService.findById(id)
                .map(authorConverter::authorToString)
                .orElse(String.format("Author with id = %s not found", id));
    }

    @ShellMethod(value = "Save author", key = "sa")
    public String saveGenre(String name) {
        Author author = new Author();
        author.setName(name);
        return authorConverter.authorToString(authorService.save(author));
    }

    @ShellMethod(value = "Update genre", key = "ua")
    public String updateGenre(long id, String name) {
        return authorConverter.authorToString(authorService.save(new Author(id, name)));
    }
}
