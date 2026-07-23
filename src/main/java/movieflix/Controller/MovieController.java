package movieflix.Controller;

import lombok.RequiredArgsConstructor;
import movieflix.Controller.request.MovieRequest;
import movieflix.Controller.response.MovieResponse;
import movieflix.Entity.Movie;
import movieflix.Mapper.MovieMapper;
import movieflix.Service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request){
        Movie savedMovie = MovieMapper.toMovie(request);
        Movie updateMovie = movieService.save(savedMovie);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(updateMovie)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id){
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findMovieByCategoryId(@RequestParam Long category){
        List<MovieResponse> list = movieService.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public ResponseEntity<MovieResponse> update(@RequestBody MovieRequest movieRequest){
        Movie movie = MovieMapper.toMovie(movieRequest);
        return movieService.update(movie)
                .map(m -> ResponseEntity.ok(MovieMapper.toMovieResponse(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
