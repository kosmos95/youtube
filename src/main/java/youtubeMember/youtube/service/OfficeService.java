package youtubeMember.youtube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.doamin.Office;
import youtubeMember.youtube.form.MemberForm;
import youtubeMember.youtube.repository.OfficeRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;

    public Office findOffice(Long id) {
        return officeRepository.findOne(id);
    }

    public List<Office> findOffices() {
        return officeRepository.findAll();
    }

}
