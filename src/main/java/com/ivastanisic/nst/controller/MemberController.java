package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.*;
import com.ivastanisic.nst.service.abstraction.MemberService;
import jakarta.validation.Valid;
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
    private MemberService memberService;

    @GetMapping("/all-active")
    public ResponseEntity<List<MemberDTO>> getAllMembers() throws Exception {
        return ResponseEntity.ok(memberService.getAll());
    }

    @GetMapping("/role")
    public ResponseEntity<List<MemberDTO>> getAllMembersByRole(@RequestParam(name = "role") String role) throws Exception {
        List<MemberDTO> memberDTOS = memberService.getAllMembersByRole(role);
        return new ResponseEntity<>(memberDTOS, HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<MemberDTO>> getAllMembersInDepartment(@PathVariable Long id) throws Exception {
        List<MemberDTO> memberDTOS = memberService.getAllMembersInDepartment(id);
        return new ResponseEntity<>(memberDTOS, HttpStatus.OK);
    }

    @GetMapping("/academic-title-histories/{id}")
    public ResponseEntity<List<AcademicTitleHistoryDTO>> getAllAcademicTitleHistoryForMemberId(@PathVariable Long id) throws Exception {
        List<AcademicTitleHistoryDTO> academicTitleHistoryDTOS = memberService.getAllAcademicTitleHistoryForMemberId(id);
        return new ResponseEntity<>(academicTitleHistoryDTOS, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<MemberDTO> saveMember(@RequestBody @Valid MemberDTO memberDTO) throws Exception {
        MemberDTO savedMember = memberService.save(memberDTO);
        return ResponseEntity.ok(savedMember);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMember(@PathVariable Long id) throws Exception {
        memberService.delete(id);
        return new ResponseEntity<>("Member with id " + id + " deleted", HttpStatus.OK);
    }

    @PatchMapping("/update-academic-title/{id}")
    public ResponseEntity<MemberDTO> updateMemberAcademicTitle(@PathVariable Long id, @RequestBody AcademicTitleDTO academicTitleDTO) throws Exception {
        MemberDTO updatedMember = memberService.updateMemberAcademicTitle(id, academicTitleDTO);
        return ResponseEntity.ok(updatedMember);
    }

    @PatchMapping("/update-director-secretary-roles/{id}")
    public ResponseEntity<MemberDTO> updateMemberRole(@PathVariable Long id, @RequestBody MemberRoleChangeDTO roleChangeDTO) throws Exception {
        return ResponseEntity.ok(memberService.updateMemberRole(id, roleChangeDTO));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MemberDTO> getByMemberId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/academic-title")
    public ResponseEntity<List<MemberDTO>> getAllByAcedemicTitle(@RequestParam(name = "name") String name) throws Exception {
        return ResponseEntity.ok(memberService.getAllByAcedemicTitle(name));
    }

    @PatchMapping("/update-department")
    public ResponseEntity<MemberDTO> updateMemberDepartment(
            @Valid @RequestBody MemberDepartmentChangeDTO memberDepartment) throws Exception {

        return ResponseEntity.ok(memberService.updateMemberDepartment(memberDepartment));
    }

    @GetMapping("/director/department")
    public ResponseEntity<MemberDTO> getDirectorForDepartment(@RequestParam(name = "name") String name) throws Exception {
        return ResponseEntity.ok(memberService.findDirectorForDepartment(name));
    }

    @GetMapping("/secretary/department")
    public ResponseEntity<MemberDTO> getSecretaryForDepartment(@RequestParam(name = "name") String name) throws Exception {
        return ResponseEntity.ok(memberService.findSecretaryForDepartment(name));
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<MemberDTO>> getAllInactiveMembers() throws Exception {
        return ResponseEntity.ok(memberService.findAllInactiveMembers());
    }
    @PostMapping("/save/director/secretary")
    public ResponseEntity<MemberDTO> saveNewDirector(@RequestBody MemberDTO memberDTO) throws Exception {
        return ResponseEntity.ok(memberService.saveDirectorOrSecretary(memberDTO));
    }
}