package foodly_backend.service;

import foodly_backend.entity.MenuEntity;
import foodly_backend.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void createMenu(MenuEntity menu) {
        this.menuRepository.save(menu);
    }
}
