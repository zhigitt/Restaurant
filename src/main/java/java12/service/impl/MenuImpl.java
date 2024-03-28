package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.MenuRequest;
import java12.dto.response.MenuResponse;
import java12.dto.response.SimpleResponse;
import java12.entities.MenuItem;
import java12.entities.Restaurant;
import java12.entities.SubCategory;
import java12.exception.NotFoundException;
import java12.repository.MenuRepo;
import java12.repository.RestaurantRepo;
import java12.repository.SubCategoryRepo;
import java12.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class MenuImpl implements MenuService {
    private final MenuRepo menuRepo;
    private final SubCategoryRepo subCategoryRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    @Transactional
    public SimpleResponse save(Long restaurantId, Long subCategoryId, MenuRequest menuRequest) {
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId)
                .orElseThrow(() -> new NotFoundException("Sub category id invalid!"));

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant id invalid!"));

        MenuItem menu = new MenuItem();

        menu.setName(menuRequest.name());
        menu.setImage(menuRequest.image());
        menu.setPrice(menuRequest.price());
        menu.setDescription(menuRequest.description());
        menu.setVegetarian(menuRequest.isVegetarian());


        restaurant.addMenuItem(menu);
        subCategory.addMenuItem(menu);

        menuRepo.save(menu);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Saved menu! " + menuRequest.name())
                .build();
    }

    @Override
    public MenuResponse get(Long menuId) {
        MenuItem menuItem = menuRepo.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menu id invalid!"));

        return new MenuResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getImage(),
                menuItem.getPrice(),
                menuItem.getDescription(),
                menuItem.isVegetarian()
        );
    }

    @Override
    @Transactional
    public SimpleResponse update(Long menuId, MenuRequest menuRequest) {
        MenuItem menuItem = menuRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu id invalid!"));

        MenuItem menu = new MenuItem();

        menu.setName(menuRequest.name());
        menu.setImage(menuRequest.image());
        menu.setPrice(menuRequest.price());
        menu.setVegetarian(menuRequest.isVegetarian());

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Updated " + menuRequest.name())
                .build();
    }

    @Override
    public List<MenuResponse> getALl() {
        List<MenuItem> all = menuRepo.findAll();

        List<MenuResponse> menuResponses = new ArrayList<>();
        for (MenuItem menuItem : all) {
            menuResponses.add(new MenuResponse(
                    menuItem.getId(),
                    menuItem.getName(),
                    menuItem.getImage(),
                    menuItem.getPrice(),
                    menuItem.getDescription(),
                    menuItem.isVegetarian()
            ));
        }

        return menuResponses;
    }

    @Override
    public SimpleResponse delete(Long menuId) {
        MenuItem menuItem = menuRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu id invalid!"));
        menuRepo.deleteMenu(menuId);
        menuRepo.deletRes(menuId);
        menuRepo.delete(menuItem);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Deleted menu " + menuItem.getName())
                .build();
    }

    @Override
    public List<MenuResponse> searchMenu(String keyword) {
        return menuRepo.search(keyword);
    }

    @Secured({"ADMIN", "CHEF"})
    @GetMapping("/sort")
    public List<MenuResponse> sort(@RequestParam String word){
        return menuRepo.sort(word);
    }


}
