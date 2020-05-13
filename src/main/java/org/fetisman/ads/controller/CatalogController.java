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
        modelAddAttribute(model, null, catalogRepo.findById(0L).get());
        return "catalogs";
    }

    @GetMapping("{catalog}")
    public String catalogEditForm(@PathVariable Catalog catalog, Model model){
        modelAddAttribute(model, null, catalog);
        return "catalogEditor";
    }

    @PostMapping("/edit")
    public String catalogEdit(
            @RequestParam String title,
            @RequestParam("catalogId") Catalog catalog,
            Model model
    ){
        if (StringUtils.isEmpty(title)){
            modelAddAttribute(model, "Catalog title can not be empty", catalog);
            return "catalogEditor";
        }
        if (!catalogService.saveUpdatedCatalog(catalog, title)){
            modelAddAttribute(model, "Catalog exists", catalog);
            return "catalogEditor";
        }
        return "redirect:/catalog";
    }

    @PostMapping
    public String addCatalog(
            @RequestParam String title,
            @Valid Catalog catalog,
            BindingResult bindingResult,
            Model model
    ){
        if (StringUtils.isEmpty(title)){
            modelAddAttribute(model, "Catalog title can not be empty", catalog);
            return "catalogs";
        }
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            modelAddAttribute(model, null, catalog);
            return "catalogs";
        }
        if (!catalogService.addCatalog(catalog)) {
            modelAddAttribute(model, "Catalog exists", catalog);
            return "catalogs";
        }

        return "redirect:/catalog";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("delete/{catalog}")
    public String deleteCatalog(@PathVariable Catalog catalog){
        catalogService.deleteCatalog(catalog);
        return "redirect:/catalog";
    }

    private void modelAddAttribute(Model model, String titleError, Catalog catalog){
        if (!StringUtils.isEmpty(titleError)) {
            model.addAttribute("titleError", titleError);
        }
        model.addAttribute("catalog", catalog);
        model.addAttribute("catalogs", catalogRepo.findAll());
    }
}