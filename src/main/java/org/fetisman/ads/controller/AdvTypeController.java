package org.fetisman.ads.controller;

import java.util.Map;
import javax.validation.Valid;
import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.repo.AdvTypeRepo;
import org.fetisman.ads.service.AdvTypeService;
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
@RequestMapping("/type")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdvTypeController {

    @Autowired
    private AdvTypeService advTypeService;

    @Autowired
    private AdvTypeRepo advTypeRepo;

    @GetMapping("{catalog}")
    public String advTypeList(Model model,
            @PathVariable Catalog catalog
    ) {
        modelAddAttribute(model, null, null, catalog);
        return "advTypes";
    }

    @GetMapping("{catalog}/{advType}")
    public String advTypeEditForm(
            @PathVariable Catalog catalog,
            @PathVariable AdvType advType,
            Model model
    ){
        modelAddAttribute(model, advType, null, catalog);
        return "advTypeEditor";
    }

    @GetMapping("delete/{catalog}/{advType}")
    public String deleteAdvType(
            @PathVariable Catalog catalog,
            @PathVariable AdvType advType,
            Model model
    ){
        catalog.getAdvTypes().remove(advType);
        advTypeRepo.delete(advType);

        return "redirect:/type/" + catalog.getId();
    }

    @PostMapping("/edit")
    public String advTypeEdit(
            @RequestParam String advTypeTitle,
            @RequestParam("advTypeId") AdvType advType,
            Model model
    ){
        if (StringUtils.isEmpty(advTypeTitle)){
            modelAddAttribute(model, advType, "Ad type title can not be empty", advType.getCatalog());
            return "advTypeEditor";
        }
        if (!advTypeService.saveUpdatedAdvType(advType, advTypeTitle)){
            modelAddAttribute(model, advType, "Ad type exists", advType.getCatalog());
            return "advTypeEditor";
        }

        return "redirect:/type/" + advType.getCatalog().getId();
    }

    @PostMapping("{catalog}")
    public String addAdvType(
            @PathVariable Catalog catalog,
            @Valid AdvType advType,
            BindingResult bindingResult,
            Model model,
            @RequestParam String advTypeTitle
    ) {
        if (StringUtils.isEmpty(advTypeTitle)) {
            modelAddAttribute(model, advType, "Ad type title can not be empty", catalog);
            return "advTypes";
        } else {
            advType.setAdvType(advTypeTitle);
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            modelAddAttribute(model, advType, null, catalog);
            return "advTypes";
        }
        if (!advTypeService.addAdvType(advType, catalog)) {
            modelAddAttribute(model, advType, "Ad type exists", catalog);
            return "advTypes";
        }

        return "redirect:/type/" + catalog.getId();
    }

    private void modelAddAttribute(Model model, AdvType advType, String advTypeError, Catalog catalog){
        if (advType != null){
            model.addAttribute("advType", advType);
        }
        if (!StringUtils.isEmpty(advTypeError)) {
            model.addAttribute("advTypeError", advTypeError);
        }
        if (catalog != null) {
            model.addAttribute("isCatalogEmpty", catalog.getAdvTypes().size() > 0);
            model.addAttribute("advTypes", catalog.getAdvTypes());
            model.addAttribute("catalogId", catalog.getId());
        }
    }

}
