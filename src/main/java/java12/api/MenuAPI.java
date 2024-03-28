package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.MenuRequest;
import java12.dto.response.MenuResponse;
import java12.dto.response.SimpleResponse;
import java12.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuAPI {
    private final MenuService menuService;

    @Secured({"ADMIN", "CHEF"})
    @PostMapping("/saveMenu/{restaurantId}/{subCategoryId}")
    public SimpleResponse save(@PathVariable Long restaurantId,
                               @PathVariable Long subCategoryId,
                               @RequestBody @Valid MenuRequest menuRequest){
        return menuService.save(restaurantId, subCategoryId, menuRequest);
    }

    @Secured({"ADMIN", "CHEF"})
    @GetMapping("/get/{menuId}")
    public MenuResponse get(@PathVariable Long menuId){
        return menuService.get(menuId);
    }

    @Secured({"ADMIN", "CHEF"})
    @PutMapping("/update/{menuId}")
    public SimpleResponse update(@PathVariable Long menuId,
                            @RequestBody MenuRequest menuRequest){
        return menuService.update(menuId, menuRequest);
    }


    @Secured({"ADMIN", "CHEF"})
    @GetMapping("/getAll")
    public List<MenuResponse> getAll(){
        return menuService.getALl();
    }

    @Secured({"ADMIN", "CHEF"})
    @PostMapping("/delete/{menuId}")
    public SimpleResponse delete(@PathVariable Long menuId){
        return menuService.delete(menuId);
    }

    @Secured({"ADMIN", "CHEF"})
    @GetMapping("/search")
    public List<MenuResponse> searchMenu(@RequestParam String keyword){
        return menuService.searchMenu(keyword);
    }




}
