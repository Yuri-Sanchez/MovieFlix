package movieflix.Controller;

import lombok.RequiredArgsConstructor;
import movieflix.Controller.response.CategoryResponse;
import movieflix.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAll()
                .stream()
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        CategoryResponse categoryId = categoryService.findById(id);

        if(categoryId != null){
            return ResponseEntity.ok(categoryId);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria com o ID " + id + "nao existe nos nossos registros");
        }
    }


}


