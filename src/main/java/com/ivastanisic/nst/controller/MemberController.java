package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.dto.MemberDTO;
import com.ivastanisic.nst.service.abstraction.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping()
    public ResponseEntity<List<MemberDTO>> getAllMembersByRole(@RequestParam(name="role") String role) throws Exception {
        List<MemberDTO> memberDTOS = memberService.getAllMembersByRole(role);
        return new ResponseEntity<>(memberDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<MemberDTO>> getAllMembersInDepartment(@PathVariable Long id) throws Exception {
        List<MemberDTO> memberDTOS = memberService.getAllMembersInDepartment(id);
        return new ResponseEntity<>(memberDTOS, HttpStatus.OK);
    }

    @GetMapping("/academic-title-histories/{id}")
    public ResponseEntity<List<AcademicTitleHistoryDTO>> getAllAcademicTitleHistoryForMemberId(@PathVariable Long id) throws Exception{
        List<AcademicTitleHistoryDTO> academicTitleHistoryDTOS = memberService.getAllAcademicTitleHistoryForMemberId(id);
        return new ResponseEntity<>(academicTitleHistoryDTOS, HttpStatus.OK);
    }
}