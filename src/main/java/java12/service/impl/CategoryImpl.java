package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.CategoryRequest;
import java12.dto.response.CategoryResponse;
import java12.dto.response.SimpleResponse;
import java12.entities.Category;
import java12.entities.Restaurant;
import java12.entities.User;
import java12.exception.NotFoundException;
import java12.repository.CategoryRepo;
import java12.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j

public class CategoryImpl implements CategoryService {
    private final CategoryRepo categoryRepo;


    @Override @Transactional
    public SimpleResponse update(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new NotFoundException("Category id invalid!"));

        category.setName(categoryRequest.getName());

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Category updated " + category.getName())
                .build();
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> all = categoryRepo.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : all) {
            categoryResponses.add(new CategoryResponse(category.getId(), category.getName()));
        }

        return categoryResponses;
    }

    @Override
    public SimpleResponse delete(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new NotFoundException("Category id invalid!"));

        categoryRepo.delete(category);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Deleted category! " + category.getName())
                .build();
    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new NotFoundException("Category id invalid!"));

        return new CategoryResponse(category.getId(), category.getName());
    }

}
