package java12.service;

import java12.dto.request.CategoryRequest;
import java12.dto.response.CategoryResponse;
import java12.dto.response.SimpleResponse;

import java.util.List;

public interface CategoryService {
    SimpleResponse update(Long categoryId, CategoryRequest categoryRequest);

    List<CategoryResponse> getAll();

    SimpleResponse delete(Long categoryId);

    CategoryResponse getCategory(Long categoryId);
}
