package movieflix.Mapper;

import lombok.experimental.UtilityClass;
import movieflix.Controller.request.CategoryRequest;
import movieflix.Controller.response.CategoryResponse;
import movieflix.Entity.Category;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
