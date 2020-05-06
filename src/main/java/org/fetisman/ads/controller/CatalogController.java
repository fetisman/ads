package org.fetisman.ads.controller;

import java.util.Map;
import javax.validation.Valid;
import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.repo.CatalogRepo;
import org.fetisman.ads.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalog")
@PreAuthorize("hasAuthority('ADMIN')")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogRepo catalogRepo;

    @GetMapping
    public String catalogList(Model model) {
        model.addAttribute("catalogs", catalogRepo.findAll());
        return "catalogList";
    }

    @GetMapping("{catalog}")
    public String catalogEditForm(@PathVariable Catalog catalog, Model model){
        model.addAttribute("catalog", catalog);
        return "catalogEdit";
    }

    @PostMapping("/edit")
    public String catalogEdit(
            @RequestParam String title,
            @RequestParam("catalogId") Catalog catalog,
            Model model
    ){
        if (StringUtils.isEmpty(title)){
            model.addAttribute("titleError", "Catalog title can not be empty");
            model.addAttribute("catalog", catalog);
            return "catalogEdit";
        }

        if (!catalogService.saveUpdatedCatalog(catalog, title)){
            model.addAttribute("titleError", "Catalog exists");
            model.addAttribute("catalog", catalog);
            return "catalogEdit";
        }

        return "redirect:/catalog";
    }

    @PostMapping
    public String addCatalog(
            @Valid Catalog catalog,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("catalogs", catalogRepo.findAll());
            return "catalogList";
        }

        if (!catalogService.addCatalog(catalog)) {
            model.addAttribute("titleError", "Catalog exists");
            model.addAttribute("catalogs", catalogRepo.findAll());
            return "catalogList";
        }

        return "redirect:/catalog";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("delete/{catalog}")
    public String deleteCatalog(@PathVariable Catalog catalog, Model model){
        catalogService.deleteCatalog(catalog);

        model.addAttribute("catalogs", catalogService.catalogList());
        return "catalogList";
    }
}