package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.ChequeRequest;
import java12.dto.response.ChequeResponse;
import java12.dto.response.GetCheckResponse;
import java12.dto.response.SimpleResponse;
import java12.dto.response.SumCheckResponse;
import java12.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cheque")
public class ChequeAPI {
    private final ChequeService chequeService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    @PostMapping("/{waiterId}")
    public SimpleResponse giveCheque(@PathVariable Long waiterId,
                                     @RequestBody @Valid ChequeRequest chequeRequest){
        return  chequeService.giveCheck(waiterId,chequeRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    @GetMapping("/findCheck/{cheId}")
    public ChequeResponse findCheckById(@PathVariable @Valid Long cheId){
        return chequeService.findCheck(cheId);
    }
    @Secured("ADMIN")
    @GetMapping("/delete/{chequeId}")
    public SimpleResponse deleteCheck(@PathVariable @Valid Long chequeId){
        return chequeService.delete(chequeId);
    }

//    @Secured({"ADMIN","WAITER"})
//    @PostMapping()
//    public SimpleResponse save(@RequestBody ChequeRequest  chequeRequest) {
//        return chequeService.save(chequeRequest);
//    }
//    @Secured({"ADMIN"})
//    @PostMapping("/update/{chekId}")
//    public SimpleResponse update(@PathVariable Long chekId,
//                                  @RequestBody  ChequeRequest checkRequest) {
//        return chequeService.update(chekId, checkRequest);
//    }
//    @Secured({"ADMIN"})
//    @PostMapping("/delete/{chekId}")
//    public SimpleResponse delete(@PathVariable Long chekId) {
//        return chequeService.delete(chekId);
//    }
//
//    @Secured("ADMIN")
//    @GetMapping("/all")
//    public List<GetCheckResponse> all() {
//        return chequeService.getAll();
//    }
//    @Secured({"ADMIN","WAITER"})
//    @GetMapping("/findCheck/{cheId}")
//    public GetCheckResponse findCheckById(@PathVariable Long cheId) {
//        return chequeService.findCheckById(cheId);
//    }
//
//    @Secured("ADMIN")
//    @GetMapping("/sum")
//    public SumCheckResponse getSum(){
//        return chequeService.sumWaiter();
//    }
//
////    @Secured("ADMIN")
////    @GetMapping("/summa/{resId}")
////    public ResSumResponse getAverage(@PathVariable Long resId){
////        return chequeService.getAverage(resId);
////    }

}
