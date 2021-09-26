package dinhthanhphu.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import dinhthanhphu.graphql.data.staticData;
import dinhthanhphu.graphql.dto.Author;
import dinhthanhphu.graphql.dto.Book;
import dinhthanhphu.graphql.entity.AuthorEntity;
import dinhthanhphu.graphql.repository.AuthorRepository;
import dinhthanhphu.graphql.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Book> getBooks(){
//        return staticData.BOOKS;
        return bookRepository.getBooks().stream().map(b -> modelMapper.map(b, Book.class)).collect(Collectors.toList());
    }

    public List<Author> getAuthors(){
//        return staticData.AUTHORS;
        List<AuthorEntity> rs = authorRepository.getAuthors();
        return authorRepository.getAuthors().stream().map(a -> modelMapper.map(a, Author.class)).collect(Collectors.toList());
    }

    public Book getBook(Long id){
//        return staticData.BOOKS.stream().filter(b -> b.getId() == id).findFirst().get();
        return modelMapper.map(bookRepository.getBookById(id), Book.class);
    }

    public Author getAuthor(Long id){
//        return staticData.AUTHORS.stream().filter(a -> a.getId() == id).findFirst().get();
        return modelMapper.map(authorRepository.getAuthor(id), Author.class);
    }

}
