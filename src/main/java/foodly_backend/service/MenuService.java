package foodly_backend.service;

import foodly_backend.entity.MenuEntity;
import foodly_backend.entity.UserEntity;
import foodly_backend.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteMenu(int id) {
        this.menuRepository.deleteById(id);
    }

    public void updateMenu(int id, MenuEntity updatedMenu) throws Exception {
        Optional<MenuEntity> optionalMenu = menuRepository.findById(id);

        if (optionalMenu.isPresent()) {
            MenuEntity existingMenu = optionalMenu.get();

            existingMenu.setTitle(updatedMenu.getTitle());
            existingMenu.setImage_source(updatedMenu.getImage_source());
            existingMenu.setPrice(updatedMenu.getPrice());
            existingMenu.setQuantity(updatedMenu.getQuantity());
            existingMenu.setIs_available(updatedMenu.isIs_available());
            existingMenu.setIs_advertised(updatedMenu.isIs_advertised());

            if (updatedMenu.getUser() != null && updatedMenu.getUser().getId() != 0) {
                UserEntity user = userService.getUserById(updatedMenu.getUser().getId());
                if (user != null) {
                    existingMenu.setUser(user);
                } else {
                    throw new Exception("Utilisateur introuvable");
                }
            }

            menuRepository.save(existingMenu);
        } else {
            throw new Exception("Menu non trouvé");
        }
    }

    public MenuEntity getMenuById(int id) {
        return this.menuRepository.findById(id).orElse(null);
    }
}
