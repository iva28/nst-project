package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.MemberRoleHistoryDTO;
import com.ivastanisic.nst.service.abstraction.MemberRoleHistoryService;
import liquibase.license.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member-role-history")
@AllArgsConstructor
public class MemberRoleHistoryController {
    private final MemberRoleHistoryService memberRoleHistoryService;

    @GetMapping("/department")
    public ResponseEntity<List<MemberRoleHistoryDTO>> getAllByDepartment(
            @RequestParam(name = "short-name") String shortName)
            throws Exception {
        return ResponseEntity.ok(memberRoleHistoryService.getAllByDepartment(shortName));
    }

    @GetMapping("/role-name")
    public ResponseEntity<List<MemberRoleHistoryDTO>> getAllByRole(
            @RequestParam(name = "role") String role)
            throws Exception {
        return ResponseEntity.ok(memberRoleHistoryService.getAllByRole(role));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberRoleHistoryDTO>> getAll() throws Exception {
        return ResponseEntity.ok(memberRoleHistoryService.getAll());
    }

}
