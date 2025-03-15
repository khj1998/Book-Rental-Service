package com.userservice.framework.web;

import com.userservice.application.usecase.AddMemberUseCase;
import com.userservice.application.usecase.DeleteMemberUseCase;
import com.userservice.application.usecase.InquiryMemberUseCase;
import com.userservice.framework.web.dto.MemberInfoDto;
import com.userservice.framework.web.dto.MemberOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final AddMemberUseCase addMemberUseCase;
    private final InquiryMemberUseCase inquiryMemberUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;

    @PostMapping("/Member/")
    public ResponseEntity<MemberOutputDto> addMember(@RequestBody MemberInfoDto memberInfoDto) {
        MemberOutputDto addedMember =
                addMemberUseCase.addMember(memberInfoDto);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping("/Member/{no}")
    public ResponseEntity<MemberOutputDto> getMember(@PathVariable("no")long no) {
        MemberOutputDto member = inquiryMemberUseCase.getMember(no);
        return member != null
            ? new ResponseEntity<>(member,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/Membger/{no}")
    public ResponseEntity<?> deleteMember(@PathVariable("no") long no) {
        deleteMemberUseCase.deleteMember(no);
        return ResponseEntity.ok().build();
    }
}
