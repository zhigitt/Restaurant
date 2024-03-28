package java12.service;

import java12.dto.request.MenuRequest;
import java12.dto.response.MenuResponse;
import java12.dto.response.SimpleResponse;

import java.util.List;

public interface MenuService {
    SimpleResponse save(Long restaurantId, Long subCategoryId, MenuRequest menuRequest);

    MenuResponse get(Long menuId);

    SimpleResponse update(Long menuId, MenuRequest menuRequest);

    List<MenuResponse> getALl();

    SimpleResponse delete(Long menuId);

    List<MenuResponse> searchMenu(String keyword);
}
