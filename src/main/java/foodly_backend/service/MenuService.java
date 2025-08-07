package foodly_backend.service;

import foodly_backend.entity.MenuEntity;
import foodly_backend.entity.UserEntity;
import foodly_backend.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private MenuRepository menuRepository;
    private UserService userService;

    public MenuService(MenuRepository menuRepository, UserService userService) {
        this.menuRepository = menuRepository;
        this.userService = userService;
    }

    public List<MenuEntity> getListMenu() {
        return this.menuRepository.findAll();
    }
    
    public void createMenu(MenuEntity menu) throws Exception {
        if (menu.getUser() == null || menu.getUser().getId() == 0) {
            throw new Exception("Utilisateur manquant");
        }

        UserEntity user = userService.getUserById(menu.getUser().getId());
        if (user == null) {
            throw new Exception("Utilisateur introuvable");
        }

        menu.setUser(user);
        menuRepository.save(menu);
    }
}
