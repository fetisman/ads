package org.fetisman.ads.controller;

import org.fetisman.ads.domain.Adv;
import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.repo.AdvRepo;
import org.fetisman.ads.service.AdvService;
import org.fetisman.ads.service.AdvTypeService;
import org.fetisman.ads.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class AdvController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AdvRepo advRepo;

    @Autowired
    private AdvService advService;

    private List<Catalog> catalogList;

    private AdvTypeService advTypeService;

    private List<AdvType> advTypeList;

    public AdvController(CatalogService catalogService, AdvTypeService advTypeService) {
        this.catalogList = catalogService.catalogList();
        this.advTypeService = advTypeService;
    }

    @GetMapping("/")
    public String greeting(
            Model model
    ) {
        model.addAttribute("catalogs", catalogList);

        return "greeting";
    }

    @GetMapping("/main/{catalog}")
    public String main(
            @PathVariable Catalog catalog,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User user) {
        if (user.isActive()) {

            this.advTypeList = advTypeService.advTypeList(catalog);

            Page<Adv> page;
            if (!StringUtils.isEmpty(filter)) {
                AdvType advType = advTypeService.loadAdvTypeByType(filter);
                page = advService.advList(pageable, advType);
            } else {
                page = advService.advList(pageable, advTypeList);
            }

            model.addAttribute("advTypes", advTypeList);
            model.addAttribute("page", page);
            model.addAttribute("url", "/main/" + catalog.getId());
            model.addAttribute("filter", filter);
            model.addAttribute("catalog", catalog);
        }

        return "main";
    }

    @PostMapping("/main/{catalog}")
    public String add(@PathVariable Catalog catalog,
                      @Valid Adv adv,
                      BindingResult bindingResult,
                      Model model,
                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                      @RequestParam("file") MultipartFile file,
                      @AuthenticationPrincipal User user) throws IOException {

        AdvType advType = advTypeService.loadAdvTypeByType(adv.getType());

        adv.setAdvType(advType);
        adv.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("adv", adv);

        } else {
            saveFile(adv, file);

            model.addAttribute("adv", null);
            advRepo.save(adv);
        }

        Page<Adv> page = advRepo.findAll(pageable);

        model.addAttribute("advTypes", advTypeList);
        model.addAttribute("page", page);
        model.addAttribute("url", "/main/" + catalog.getId() + "?page=" + page.getNumber());
        model.addAttribute("user", user);
        model.addAttribute("catalog", catalog);

        return "main";
    }

    @PostMapping("/user-advs/{user}")
    public String updateAdv(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Adv adv,
            @RequestParam("shortDesc") String shortDesc,
            @RequestParam("longDesc") String longDesc,
            @RequestParam("phone") String phone,
            @RequestParam("price") String price,
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (adv.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(shortDesc)) {
                adv.setShortDesc(shortDesc);
            }

            if (!StringUtils.isEmpty(longDesc)) {
                adv.setLongDesc(longDesc);
            }

            if (!StringUtils.isEmpty(phone)) {
                adv.setPhone(phone);
            }

            if (!StringUtils.isEmpty(price)) {
                adv.setPrice(price);
            }

            AdvType advType = advTypeService.loadAdvTypeByType(type);
            adv.setAdvType(advType);
            adv.setType(type);

            saveFile(adv, file);

            advRepo.save(adv);
        }
        return "redirect:/user-advs/" + user;
    }

    private void saveFile(@Valid Adv adv, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            adv.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-advs/{author}")
    public String userAdvs(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Adv adv
    ) {
        Page<Adv> page = advService.advListForUser(pageable, author);

        if (adv != null) {
            List<AdvType> advTypeList = advTypeService.advTypeList(null);
            int advTypeIndex = advTypeList.indexOf(adv.getAdvType());
            if (advTypeIndex > 0) {
                Collections.swap(advTypeList, 0, advTypeIndex);
            }
            model.addAttribute("advTypes", advTypeList);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/user-advs/" + author.getId());
        model.addAttribute("adv", adv);
        model.addAttribute("isCurrentUser", currentUser.equals(author));

        return "userAdvs";
    }

    @GetMapping("/delete/{adv}")
    public String deleteAdv(@PathVariable Long adv){

        advService.deleteAdv(adv);

        return "redirect:/main/" + 0L;
    }

}
