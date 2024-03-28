package java12.service.impl;

import java12.dto.request.ChequeRequest;
import java12.dto.response.ChequeResponse;
import java12.dto.response.MenuResponse;
import java12.dto.response.SimpleResponse;
import java12.entities.Cheque;
import java12.entities.MenuItem;
import java12.entities.Restaurant;
import java12.entities.User;
import java12.entities.enums.Role;
import java12.exception.ForbiddenException;
import java12.exception.NotFoundException;
import java12.repository.ChequeRepo;
import java12.repository.MenuRepo;
import java12.repository.UserRepo;
import java12.service.ChequeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ChequeImpl implements ChequeService {
    private final ChequeRepo chequeRepo;
    private final MenuRepo menuRepo;
    private final UserRepo userRepo;

    @Override
    public SimpleResponse giveCheck(Long id, ChequeRequest chequeRequest) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new RuntimeException("not found"));

        List<Long> menu = new ArrayList<>();
        if (chequeRequest.getMenuItemIds() != null) {
            menu.addAll(chequeRequest.getMenuItemIds());
        }
        List<MenuItem> allMenuId = menuRepo.getAllMenuId(menu);

        int totalPrice = 0;
        for (MenuItem menuItem : allMenuId) {
            totalPrice += menuItem.getPrice();
        }

        double serviceChargePercentage = 0.1;
        double serviceCharge = totalPrice * serviceChargePercentage;
        Cheque cheque = new Cheque();
        cheque.setTotalPrice(totalPrice);
        cheque.setService((int) serviceCharge);
        cheque.setUser(user);
        chequeRepo.save(cheque);
        for (int i = 0; i < allMenuId.size(); i++) {
            cheque.getMenuItems().add(allMenuId.get(i));
            log.error(String.valueOf(i));
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cheque created successfully")
                .build();
    }

    @Override
    public ChequeResponse findCheck(Long cheId) {
        Cheque cheque = chequeRepo.findById(cheId)
                .orElseThrow(() -> new NotFoundException("Not found Cheque with Id " + cheId));

        User user = cheque.getUser();
        if (user == null) {
            throw new NotFoundException("User is not found for the cheque");
        }

        Restaurant restaurant = user.getRestaurant();

        if (restaurant != null) {
            Integer service1 = restaurant.getService();

        } else {
            throw new NotFoundException("Restaurant is not found for the user");
        }

        List<MenuResponse> menuItemResponses = new ArrayList<>();
        if (restaurant != null && restaurant.getMenuItems() != null) {
            for (MenuItem menuItem : restaurant.getMenuItems()) {
                menuItemResponses.add(new MenuResponse(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getImage(),
                        menuItem.getPrice(),
                        menuItem.getDescription(),
                        menuItem.isVegetarian()
                ));
            }
        }

        return null;
//        return ChequeResponse
//                .builder()
//                .fullName(user.getFirstName())
//                .item(menuItemResponses)
//                .avgPrice()
//                .service(String.valueOf(cheque.getService()) + " = 10%")
//                .totalPrice(cheque.getTotalPrice())
//                .build();
    }
    @Override
    public SimpleResponse delete(Long chequeId) {
        Cheque checkNotFound = chequeRepo.findById(chequeId).orElseThrow(() -> new NotFoundException("check not found"));
        chequeRepo.delete(checkNotFound);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success deleted")
                .build();
    }






//
//    @Override
//    public SimpleResponse save(ChequeRequest chequeRequest) {
//        return null;
//    }
//
//    @Override
//    public SimpleResponse update(Long chekId, ChequeRequest checkRequest) {
//        return null;
//    }
//
//    @Override
//    public SimpleResponse delete(Long chekId) {
//        return null;
//    }
//
//    @Override
//    public List<GetCheckResponse> getAll() {
//        return null;
//    }
//
//    @Override
//    public GetCheckResponse findCheckById(Long cheId) {
//        return null;
//    }
//
//    @Override
//    public SumCheckResponse sumWaiter() {
//        return null;
//    }


    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User current = userRepo.getByEmail(email);
        if (current.getRole().equals(Role.ADMIN) || current.getRole().equals(Role.WAITER))
            return current;
        else throw new ForbiddenException("Forbidden 403");
    }
}
