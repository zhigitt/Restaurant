package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.StopListRequest;
import java12.dto.response.SimpleResponse;
import java12.dto.response.StopListResponse;
import java12.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stopList")
public class StopListAPI {
    private final StopListService stopListService;

    @Secured({"ADMIN", "CHEF"})
    @PostMapping("/save/{menuId}")
    public SimpleResponse save(@PathVariable Long menuId,
                               @RequestBody @Valid StopListRequest stopListRequest){
        return stopListService.save(menuId, stopListRequest);
    }

    @Secured({"ADMIN", "CHEF"})
    @GetMapping("/get/{stopListId}")
    public StopListResponse get(@PathVariable Long stopListId){
        return stopListService.get(stopListId);
    }

    @Secured({"ADMIN", "CHEF"})
    @GetMapping("/getAll")
    public List<StopListResponse> getAll(){
        return stopListService.getAll();
    }

    @Secured({"ADMIN", "CHEF"})
    @PostMapping("/delete/{stopListId}")
    public SimpleResponse delete(@PathVariable Long stopListId){
        return stopListService.delete(stopListId);
    }

}
