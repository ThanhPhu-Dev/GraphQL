package dinhthanhphu.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import dinhthanhphu.graphql.data.staticData;
import dinhthanhphu.graphql.dto.Author;
import dinhthanhphu.graphql.dto.Book;
import dinhthanhphu.graphql.entity.AuthorEntity;
import dinhthanhphu.graphql.entity.BookEntity;
import dinhthanhphu.graphql.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorResolver implements GraphQLResolver<Author> {

    @Autowired
    private BookRepository bookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Book> getBooks(Author author){
//        return staticData.BOOKS.stream().filter(b -> b.getAuthorId() == author.getId()).collect(Collectors.toList());
        List<BookEntity> books  = bookRepository.getBooksByAuthorId(author.getId());
        return books.stream().map(b -> modelMapper.map(b, Book.class)).collect(Collectors.toList());
    }
}
