package dinhthanhphu.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import dinhthanhphu.graphql.data.staticData;
import dinhthanhphu.graphql.dto.Author;
import dinhthanhphu.graphql.dto.Book;
import dinhthanhphu.graphql.dto.StatusPayload;
import dinhthanhphu.graphql.entity.AuthorEntity;
import dinhthanhphu.graphql.entity.BookEntity;
import dinhthanhphu.graphql.repository.AuthorRepository;
import dinhthanhphu.graphql.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final ModelMapper modelMapper = new ModelMapper();

//    public Author createAuthor(Long id, String name, Integer age){
//        Author author = new Author(id, name, age);
//        staticData.AUTHORS.add(author);
//        return author;
//    }
//    public Book createBook(Long id, String name, String genre, Long authorId) {
//        Book book = new Book(id, name, genre, authorId);
//        staticData.BOOKS.add(book);
//        return book;
//    }

    public Author createAuthor(String name, Integer age) {
        AuthorEntity author = authorRepository.save(AuthorEntity.builder().name(name).age(age).build());
        return modelMapper.map(author, Author.class);
    }

    public Book createBook(String name, String genre, Long authorId) {
        AuthorEntity author = authorRepository.getAuthor(authorId);
        BookEntity book = bookRepository.save(BookEntity.builder().name(name).genre(genre).author(author).build());
        return modelMapper.map(book, Book.class);
    }

    public Author updateAuthor(Long id, String name, Integer age){
        AuthorEntity author = authorRepository.save(AuthorEntity.builder().id(id).name(name).age(age).build());
        return modelMapper.map(author, Author.class);
    }

    public Book updateBook(Long id, String name, String genre, Long authorId){
        AuthorEntity author = authorRepository.getAuthor(authorId);
        BookEntity book = bookRepository.save(BookEntity.builder().id(id).name(name).genre(genre).author(author).build());
        return modelMapper.map(book, Book.class);
    }

    public StatusPayload deleteBook(Long id){
        StatusPayload payload = new StatusPayload();
        try {
            bookRepository.delete(id);
            payload.setCode(200);
            payload.setMsg("Delete Book Success");
        } catch (Exception e) {
            e.printStackTrace();
            payload.setCode(500);
            payload.setMsg(e.getMessage());
        }
        return payload;
    }

    public StatusPayload deleteAuthor(Long id){
        StatusPayload payload = new StatusPayload();
        try {
            authorRepository.delete(id);
            payload.setCode(200);
            payload.setMsg("Delete Author Success");
        } catch (Exception e) {
            e.printStackTrace();
            payload.setCode(500);
            payload.setMsg(e.getMessage());
        }
        return payload;
    }
}
