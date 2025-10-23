package ru.mentee.power.streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamTransformTaskUtil {

    public static List<String> findSciFiTitlesAfter2000(List<Book> books) {
        if (books == null) {
            return List.of();
        }
        return books.stream()
                .filter(book -> book.getYear() > 2000)
                .filter(book -> book.getGenres().stream().anyMatch(genre -> genre.equalsIgnoreCase("фантастика")))
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    public static List<String> findUniqueAuthors(List<Book> books) {
        if (books == null) {
            return List.of();
        }
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Set<String> findUniqueGenresUpperCase(List<Book> books) {
        if (books == null) {
            return Set.of();
        }
        return books.stream()
                .flatMap(book -> book.getGenres().stream())
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
}
