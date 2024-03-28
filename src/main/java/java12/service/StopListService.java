package java12.service;

import java12.dto.request.StopListRequest;
import java12.dto.response.SimpleResponse;
import java12.dto.response.StopListResponse;

import java.util.List;

public interface StopListService {
    SimpleResponse save(Long menuId, StopListRequest stopListRequest);

    StopListResponse get(Long stopListId);

    List<StopListResponse> getAll();

    SimpleResponse delete(Long stopListId);
}
