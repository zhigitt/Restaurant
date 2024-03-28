package java12.service;

import java12.dto.request.SubCategoryRequest;
import java12.dto.response.SimpleResponse;
import java12.dto.response.SubCategoryResponse;

import java.util.List;
import java.util.Map;

public interface SubCategoryService {
    SimpleResponse save(Long categoryId, SubCategoryRequest subCategoryRequest);

    SubCategoryResponse get(Long subcategoryId);

    SimpleResponse update(Long subCategoryId, SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> getAll();

    SimpleResponse delete(Long subCategoryId);

    List<SubCategoryResponse> getAllSubs(Long categoryId);

    Map<String, List<SubCategoryResponse>> group();
}
