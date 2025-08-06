package foodly_backend.controller;

import foodly_backend.entity.MenuEntity;
import foodly_backend.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "menu")
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void createMenu(@RequestBody MenuEntity menu) {
        this.menuService.createMenu(menu);
    }
}
