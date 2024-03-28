package java12.service.impl;

import java12.dto.request.StopListRequest;
import java12.dto.response.SimpleResponse;
import java12.dto.response.StopListResponse;
import java12.entities.MenuItem;
import java12.entities.StopList;
import java12.exception.NotFoundException;
import java12.repository.MenuRepo;
import java12.repository.StopListRepo;
import java12.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StopListImpl implements StopListService {
    private final StopListRepo stopListRepo;
    private final MenuRepo menuRepo;

    @Override
    public SimpleResponse save(Long menuId, StopListRequest stopListRequest) {
        MenuItem menuItem = menuRepo.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menu id invalid!"));

        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.reason());
        stopList.setDate(stopListRequest.date());
        stopList.setMenuItem(menuItem);
        stopListRepo.save(stopList);

        stopList.setMenuItem(menuItem);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Stop list saved")
                .build();
    }

    @Override
    public StopListResponse get(Long stopListId) {
        StopList stopList = stopListRepo.findById(stopListId)
                .orElseThrow(() -> new NotFoundException("Stop list id invalid!"));

        return new StopListResponse(
                stopList.getId(),
                stopList.getReason(),
                stopList.getDate()
        );
    }

    @Override
    public List<StopListResponse> getAll() {
        List<StopList> all = stopListRepo.findAll();
        List<StopListResponse> stopListResponses = new ArrayList<>();

        for (StopList stopList : all) {
            stopListResponses.add(new StopListResponse(
                    stopList.getId(),
                    stopList.getReason(),
                    stopList.getDate())
            );
        }
        return stopListResponses;
    }

    @Override
    public SimpleResponse delete(Long stopListId) {
        StopList stopList = stopListRepo.findById(stopListId)
                .orElseThrow(() -> new NotFoundException("Stop list id invalid!"));

        stopListRepo.delete(stopList);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Deleted stop list!")
                .build();
    }


}
