package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.AcademicTitleHistoryConverter;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.domain.AcademicTitleHistory;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.domain.ScientificField;
import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.repository.AcademicTitleHistoryRepository;
import com.ivastanisic.nst.repository.AcademicTitleRepository;
import com.ivastanisic.nst.repository.MemberRepository;
import com.ivastanisic.nst.repository.ScientificFieldRepository;
import com.ivastanisic.nst.service.abstraction.AcademicTitleHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    @Autowired
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    @Autowired
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final AcademicTitleRepository academicTitleRepository;
    @Autowired
    private final ScientificFieldRepository scientificFieldRepository;

    @Override
    public AcademicTitleHistoryDTO save(AcademicTitleHistoryDTO obj) throws Exception {
        System.out.println(obj);

        if (obj == null) {
            throw new Exception("Academic title history can't be empty");
        }

        if (obj.getMemberDTO() == null
                || obj.getAcademicTitleDTO() == null
                || obj.getScientificFieldDTO() == null) {
            throw new Exception("Academic title history must have member, academic title and scientific values");
        }

        System.out.println("Proverio prazna polja");

        Optional<Member> member = memberRepository.findById(obj.getMemberDTO());
        if (member.isEmpty()) {
            throw new Exception("You can't save academic title history for member that doesn't exist");
        }

        System.out.println("Proverio da postoji member");

        Optional<AcademicTitle> academicTitle = academicTitleRepository.findById(obj.getAcademicTitleDTO().getId());
        if (academicTitle.isEmpty()) {
            throw new Exception("You can't save academic title history for academic title that doesn't exits");
        }

        System.out.println("Proverio da postoji academic tile");

        Optional<ScientificField> scientificField = scientificFieldRepository.findById(obj.getScientificFieldDTO().getId());
        if (scientificField.isEmpty()) {
            throw new Exception("You can't save academic title history for scientific field that doesn't exist");
        }

        System.out.println("Proverio da postoji scientific field");

//        Optional<AcademicTitleHistory> existsAcademicTitleHistory = academicTitleHistoryRepository.
//                findByMemberIdAndAcademicTitleIdAndScientificFieldIdAndStartDate(
//                        obj.getMemberDTO(),
//                        obj.getAcademicTitleDTO().getId(),
//                        obj.getScientificFieldDTO().getId(),
//                        obj.getStartDate()
//                );
//
//        System.out.println("Proverio da li postoji academic title history");
//
//
//        if (existsAcademicTitleHistory.get() != null) {
//            System.out.println("Postoji academic title history");
//            System.out.println(existsAcademicTitleHistory.get());
//            throw new Exception("Academic title history already exists");
//        } else {
//            System.out.println("Nepostoji academic title history");
//        }

        System.out.println("Saving academic title history...");

        return academicTitleHistoryConverter.toDTO(academicTitleHistoryRepository.save(
                academicTitleHistoryConverter.toEntity(obj)));
    }

    @Override
    public List<AcademicTitleHistoryDTO> getAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public AcademicTitleHistoryDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleHistoryDTO update(AcademicTitleHistoryDTO academicTitleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleHistoryDTO updateById(Long aLong, AcademicTitleHistoryDTO academicTitleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleHistoryDTO findByName(String name) throws Exception {
        return null;
    }

    @Override
    public List<AcademicTitleHistoryDTO> getAllByMemberId(Long id) {
        return academicTitleHistoryConverter.listToDTO(academicTitleHistoryRepository.findByMemberId(id));
    }
}
