package pl.markowski.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.markowski.tournament.model.Info;
import pl.markowski.tournament.repo.InfoRepo;

@Service
public class InfoService {

    private InfoRepo infoRepo;

    @Autowired
    public InfoService(InfoRepo infoRepo) {
        this.infoRepo = infoRepo;
    }

    public Page<Info> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.infoRepo.findAll(pageable);
    }

    public void deleteInfoById(long id) {
        Info info = infoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID : " + id));
        this.infoRepo.delete(info);
    }

    public void deleteInfoAll() {
        this.infoRepo.deleteAll();
    }
}

