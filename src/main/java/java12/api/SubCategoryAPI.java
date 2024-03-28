package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.SubCategoryRequest;
import java12.dto.response.SimpleResponse;
import java12.dto.response.SubCategoryResponse;
import java12.service.CategoryService;
import java12.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subCategory")
public class SubCategoryAPI {
    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;

    @Secured("ADMIN")
    @PostMapping("/save/{categoryId}")
    public SimpleResponse save(@PathVariable Long categoryId,
                               @RequestBody @Valid SubCategoryRequest subCategoryRequest){
        return subCategoryService.save(categoryId, subCategoryRequest);
    }

    @Secured("ADMIN")
    @GetMapping("/get/{subcategoryId}")
    public SubCategoryResponse get(@PathVariable Long subcategoryId){
        return subCategoryService.get(subcategoryId);
    }

    @Secured("ADMIN")
    @PutMapping("/update/{subCategoryId}")
    public SimpleResponse update(@PathVariable Long subCategoryId,
                                 @RequestBody @Valid SubCategoryRequest subCategoryRequest){
        return subCategoryService.update(subCategoryId, subCategoryRequest);
    }

    @Secured("ADMIN")
    @GetMapping("/getAll")
    public List<SubCategoryResponse> getAll(){
        return subCategoryService.getAll();
    }

    @Secured("ADMIN")
    @PostMapping("/delete/{subCategoryId}")
    public SimpleResponse delete(@PathVariable Long subCategoryId){
        return subCategoryService.delete(subCategoryId);
    }

    @Secured("ADMIN")
    @GetMapping("/getSubCatById/{categoryId}")
    public List<SubCategoryResponse> getSubCategoryById(@PathVariable Long categoryId){
        return subCategoryService.getAllSubs(categoryId);
    }

    @Secured("ADMIN")
    @GetMapping("/group")
    public Map<String, List<SubCategoryResponse>> group(){
        return subCategoryService.group();
    }

}
