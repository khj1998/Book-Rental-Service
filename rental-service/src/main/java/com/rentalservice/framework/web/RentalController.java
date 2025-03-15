package com.rentalservice.framework.web;

import com.rentalservice.application.usecase.*;
import com.rentalservice.framework.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {
    private final RentItemUseCase rentItemUsecase;
    private final ReturnItemUseCase returnItemUsecase;
    private final OverDueItemUseCase overdueItemUsecase;
    private final CreateRentalCardUseCase createRentalCardUsecase;
    private final InquiryUseCase inquiryUsecase;
    private final ClearOverDueItemUseCase clearOverdueItemUsecase;

    @Operation(summary = "도서카드 생성",description = "사용자정보 -> 도서카드정보")
    @PostMapping("/RentalCard/")
    public ResponseEntity<RentalCardOutputDto> createRentalCard(@RequestBody UserInputDto userInputDTO) {
        RentalCardOutputDto createdRentalCard = createRentalCardUsecase.createRentalCard(userInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRentalCard);
    }

    @Operation(summary = "도서카드 조회",description = "사용자정보(id) -> 도서카드정보")
    @GetMapping("/RentalCard/{id}")
    public ResponseEntity<RentalCardOutputDto> getRentalCard(@PathVariable String id) {
        Optional<RentalCardOutputDto> RentalCardOutputDto = inquiryUsecase.getRentalCard(id);
        return ResponseEntity.ok(RentalCardOutputDto.get());
    }

    @Operation(summary = "대여도서목록 조회",description = "사용자정보(id) -> 대여도서목록 조회")
    @GetMapping("/RentalCard/{id}/rentbook")
    public ResponseEntity<List<RentItemOutputDto>> getAllRentItem(@PathVariable String id) {
        Optional<List<RentItemOutputDto>> RentalCardOutputDtos = inquiryUsecase.getAllRentItem(id);
        return ResponseEntity.ok(RentalCardOutputDtos.get());
    }

    @Operation(summary = "반납도서목록 조회",description = "사용자정보(id) -> 반납도서목록 조 회")
    @GetMapping("/RentalCard/{id}/returnbook")
    public ResponseEntity<List<ReturnItemOutputDto>> getAllReturnItem(@PathVariable String id) {
        Optional<List<ReturnItemOutputDto>> allReturnItem = inquiryUsecase.getAllReturnItem(id);
        return ResponseEntity.ok(allReturnItem.get());
    }

    @Operation(summary = "대여기능",description = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/rent")
    public ResponseEntity<RentalCardOutputDto> rentItem(@RequestBody UserItemInputDto userItemInputDTO) throws Exception {
        RentalCardOutputDto resultDTO= rentItemUsecase.rentItem(userItemInputDTO);
        return ResponseEntity.ok(resultDTO);
    }

    @Operation(summary = "반납기능",description= "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/return")
    public ResponseEntity<RentalCardOutputDto> returnItem(@RequestBody UserItemInputDto userItemInputDTO) throws Exception {
        RentalCardOutputDto RentalCardOutputDto = returnItemUsecase.returnItem(userItemInputDTO);
        return ResponseEntity.ok(RentalCardOutputDto);
    }

    @Operation(summary = "연체기능",description = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/overdue")
    public ResponseEntity<RentalCardOutputDto> overdueItem(@RequestBody UserItemInputDto userItemInputDto) throws Exception {
        RentalCardOutputDto RentalCardOutputDto = overdueItemUsecase.overDueItem(userItemInputDto);
        return ResponseEntity.ok(RentalCardOutputDto);
    }

    @Operation(summary = "연체해제기능",description = "사용자정보,포인트 -> 도서카드정보 ")
    @PostMapping("/RentalCard/clearoverdue")
    public ResponseEntity<RentalResultOutputDto> clearOverdueItem(@RequestBody ClearOverDueInfoDto clearOverdueInfoDTO) throws Exception {
        RentalResultOutputDto rentalResultOuputDTO = clearOverdueItemUsecase.clearOverDue(clearOverdueInfoDTO);
        return ResponseEntity.ok(rentalResultOuputDTO);
    }
}
