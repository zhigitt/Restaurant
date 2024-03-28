package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.CategoryRequest;
import java12.dto.response.CategoryResponse;
import java12.dto.response.SimpleResponse;
import java12.entities.Category;
import java12.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryAPI {
    private final CategoryService categoryService;

    @Secured("ADMIN")
    @PutMapping("/update/{categoryId}")
    public SimpleResponse update(@PathVariable Long categoryId,
                                  @RequestBody @Valid CategoryRequest categoryRequest){
        return categoryService.update(categoryId, categoryRequest);
    }

    @Secured("ADMIN")
    @GetMapping("/getAll")
    public List<CategoryResponse> getAll(){
        return categoryService.getAll();
    }

    @Secured("ADMIN")
    @PostMapping("/delete/{categoryId}")
    public SimpleResponse delete(@PathVariable Long categoryId){
        return categoryService.delete(categoryId);
    }

    @Secured("ADMIN")
    @GetMapping("/getCategory/{categoryId}")
    public CategoryResponse getCategory(@PathVariable Long categoryId){
        return categoryService.getCategory(categoryId);
    }


}
