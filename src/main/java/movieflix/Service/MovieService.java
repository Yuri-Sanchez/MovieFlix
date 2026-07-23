package movieflix.Service;

import lombok.RequiredArgsConstructor;
import movieflix.Entity.Category;
import movieflix.Entity.Movie;
import movieflix.Entity.Streaming;
import movieflix.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreaming(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findByCategoriesId(categoryId);
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    public Optional<Movie> update(Movie updateMovie){
        Optional<Movie> optMovie = findById(updateMovie.getId());

        if(optMovie.isPresent()){
            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreaming(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setName(updateMovie.getName());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setReleaseDate(updateMovie.getReleaseDate());

            movie.getCategories().clear();
            movie.getCategories().addAll(findCategories(updateMovie.getCategories()));

            movie.getStreamings().clear();
            movie.getStreamings().addAll(findStreaming(updateMovie.getStreamings()));

            movieRepository.save(movie);
            return Optional.of(movie);
        }

        return Optional.empty();
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesList = new ArrayList<>();
        categories.forEach(category -> {
            Optional<Category> optCategory = categoryService.findById(category.getId());
            optCategory.ifPresent(categoriesList::add);
        });
        return categoriesList;
    }

    private List<Streaming> findStreaming(List<Streaming> streamings){
        List<Streaming> streamingList = new ArrayList<>();
        streamings.forEach(streming -> {
            Optional<Streaming> optStreaming = streamingService.findById(streming.getId());
            optStreaming.ifPresent(streamingList::add);
        });
        return streamingList;
    }
}
