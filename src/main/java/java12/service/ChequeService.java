package java12.service;

import java12.dto.request.ChequeRequest;
import java12.dto.response.ChequeResponse;
import java12.dto.response.GetCheckResponse;
import java12.dto.response.SimpleResponse;
import java12.dto.response.SumCheckResponse;

import java.security.Principal;
import java.util.List;

public interface ChequeService {

    SimpleResponse giveCheck(Long userId, ChequeRequest chequeRequest);

    ChequeResponse findCheck(Long cheId);

    SimpleResponse delete(Long chequeId);



//    SimpleResponse save(ChequeRequest chequeRequest);
//
//    SimpleResponse update(Long chekId, ChequeRequest checkRequest);
//
//    SimpleResponse delete(Long chekId);
//
//    List<GetCheckResponse> getAll();
//
//    GetCheckResponse findCheckById(Long cheId);
//
//    SumCheckResponse sumWaiter();
}
