package dinhthanhphu.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import dinhthanhphu.graphql.data.staticData;
import dinhthanhphu.graphql.dto.Author;
import dinhthanhphu.graphql.dto.Book;
import dinhthanhphu.graphql.entity.AuthorEntity;
import dinhthanhphu.graphql.repository.AuthorRepository;
import dinhthanhphu.graphql.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookResolver implements GraphQLResolver<Book> {

    @Autowired
    private AuthorRepository authorRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Author getAuthor(Book book){
//        return staticData.AUTHORS.stream().filter(a -> a.getId() == book.getAuthorId())
//                .findFirst().orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tác giả"));
        return modelMapper.map(authorRepository.getAuthor(book.getAuthorId()),Author.class);
    }
}
