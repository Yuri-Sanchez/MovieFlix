package movieflix.Service;

import lombok.RequiredArgsConstructor;
import movieflix.Controller.request.CategoryRequest;
import movieflix.Controller.response.CategoryResponse;
import movieflix.Entity.Category;
import movieflix.Mapper.CategoryMapper;
import movieflix.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse findById(Long id){
        Optional<Category> categoriesId = categoryRepository.findById(id);
        return categoriesId
                .map(CategoryMapper::toCategoryResponse)
                .orElse(null);
    }

    public CategoryResponse save (CategoryRequest category){
        Category newCategory = CategoryMapper.toCategory(category);
        Category savedNewCategory = categoryRepository.save(newCategory);
        return CategoryMapper.toCategoryResponse(savedNewCategory);

    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
