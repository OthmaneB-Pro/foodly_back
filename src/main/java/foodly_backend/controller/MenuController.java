package foodly_backend.controller;

import foodly_backend.entity.MenuEntity;
import foodly_backend.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "menu")
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping
    public List<MenuEntity> getListMenu() {
        return this.menuService.getListMenu();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void createMenu(@RequestBody MenuEntity menu) throws Exception {
        this.menuService.createMenu(menu);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @DeleteMapping(path = "{id}")
    public void deleteMenu(@PathVariable int id) {
        this.menuService.deleteMenu(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateMenu(@PathVariable int id, @RequestBody MenuEntity updatedMenu) {
        try {
            menuService.updateMenu(id, updatedMenu);
            return ResponseEntity.ok("Menu mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
