package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.SubCategoryRequest;
import java12.dto.response.SimpleResponse;
import java12.dto.response.SubCategoryResponse;
import java12.entities.Category;
import java12.entities.SubCategory;
import java12.exception.NotFoundException;
import java12.repository.CategoryRepo;
import java12.repository.SubCategoryRepo;
import java12.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j

public class SubCategoryImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;


    @Override
    @Transactional
    public SimpleResponse save(Long categoryId, SubCategoryRequest subCategoryRequest) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category id invalid!"));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.name());

        category.addSubCategory(subCategory);

        subCategoryRepo.save(subCategory);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Saved sub category " + subCategory.getName())
                .build();
    }

    @Override
    public SubCategoryResponse get(Long subcategoryId) {
        SubCategory subCategory = subCategoryRepo.findById(subcategoryId)
                .orElseThrow(() -> new NotFoundException("Sub category id invalid!"));

        return new SubCategoryResponse(subCategory.getId(), subCategory.getName()) ;
    }

    @Override
    @Transactional
    public SimpleResponse update(Long subCategoryId, SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId)
                .orElseThrow(() -> new NotFoundException("Sub category id invalid!"));

        subCategory.setName(subCategoryRequest.name());

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Sub category updated! " + subCategoryRequest.name())
                .build();
    }

    @Override
    public List<SubCategoryResponse> getAll() {
        List<SubCategory> all = subCategoryRepo.findAll();
        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();
        for (SubCategory subCategory : all) {
            subCategoryResponses.add(new SubCategoryResponse(subCategory.getId(), subCategory.getName()));
        }

        return subCategoryResponses;
    }

    @Override
    public SimpleResponse delete(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId)
                .orElseThrow(() -> new NotFoundException("Sub category id invalid!"));

        subCategoryRepo.deleteMenu(subCategoryId);
        subCategoryRepo.deleteRes(subCategoryId);
        subCategoryRepo.delete(subCategory);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Deleted!")
                .build();
    }

    @Override
    public List<SubCategoryResponse> getAllSubs(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category id invalid!"));
       return subCategoryRepo.getAllSubs(category.getId());

    }

    @Override
    public Map<String, List<SubCategoryResponse>> group() {
        Map<String, List<Object[]>> group = subCategoryRepo.group();
        return null;
    }


}
