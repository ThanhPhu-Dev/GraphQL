package dinhthanhphu.graphql.data;

import dinhthanhphu.graphql.dto.Author;
import dinhthanhphu.graphql.dto.Book;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class staticData {
    public static final List<Book> BOOKS = Stream.of(
            new Book(1L, "Tắt Đèn", "Phiên Lưu", 1L),
            new Book(2L, "Chí Phèo", "Giáo Dục", 2L),
            new Book(3L, "Số Đỏ", "Giáo Dục", 3L),
            new Book(4L, "Đời Thừa", "Giáo Dục", 2L),
            new Book(5L, "Sống Mòn", "Giáo Dục", 2L),
            new Book(6L, "kỹ nghệ Lấy Tây", "Phiên Lưu", 3L)
    ).collect(Collectors.toList());

    public static final List<Author> AUTHORS = Stream.of(
            new Author(1L, "Ngô Tất Tố", 127),
            new Author(2L, "Nam Cao", 106),
            new Author(3L, "Vũ Trọng Phụng", 109)
    ).collect(Collectors.toList());
}
