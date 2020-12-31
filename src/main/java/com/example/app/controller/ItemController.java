package com.example.app.controller;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.MessageSource;

import com.example.app.domain.Item;
import com.example.app.service.ItemService;

@Controller
@RequestMapping("/accountbook")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	* メッセージ管理
	*/
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public String index(Model model) {
		// model.addAttribute("title", messageSource.getMessage("label.sample.index.title", new String[] {}, Locale.getDefault()));
		model.addAttribute("items", itemService.findAll());
		return "index";
	}

	@GetMapping("{id}")
	public String show(@PathVariable 
			Long id, Model model) {
		model.addAttribute("item", itemService.findOne(id));
		return "show";
	}

	@GetMapping("new")
	public String newItem(@ModelAttribute("item") Item item, Model model) {
		model.addAttribute("categories",getCategories());
		return "new";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) {
		model.addAttribute("item", itemService.findOne(id));
		model.addAttribute("categories",getCategories());
		return "edit";
	}

	@PostMapping
	public String create(@ModelAttribute("item") @Validated Item item, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "new";
		} else {
			itemService.save(item);
			return "redirect:/accountbook";
		}
	}

	@PutMapping("{id}")
	public String update(@PathVariable Long id, @ModelAttribute("item") @Validated Item item, BindingResult result,
			Model model) {
		System.out.println(id);
		if (result.hasErrors()) {
			model.addAttribute("item", item);
			return "edit";
		} else {
			item.setId(id);
			itemService.update(item);
			return "redirect:/accountbook";
		}
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id) {
		itemService.delete(id);
		return "redirect:/accountbook";
	}
	
	// カテゴリをセットする
	private Map<String,String> getCategories(){
	     Map<String, String> categoryMap = new LinkedHashMap<String, String>();
	     categoryMap.put("1", messageSource.getMessage("label.app.new.category1", new String[] {}, Locale.getDefault()));
	     categoryMap.put("2", messageSource.getMessage("label.app.new.category2", new String[] {}, Locale.getDefault()));
	     categoryMap.put("3", messageSource.getMessage("label.app.new.category3", new String[] {}, Locale.getDefault()));
	     categoryMap.put("4", messageSource.getMessage("label.app.new.category4", new String[] {}, Locale.getDefault()));
	     categoryMap.put("5", messageSource.getMessage("label.app.new.category5", new String[] {}, Locale.getDefault()));
	     categoryMap.put("6", messageSource.getMessage("label.app.new.category6", new String[] {}, Locale.getDefault()));
	     categoryMap.put("7", messageSource.getMessage("label.app.new.category7", new String[] {}, Locale.getDefault()));
	     categoryMap.put("8", messageSource.getMessage("label.app.new.category8", new String[] {}, Locale.getDefault()));
	     return categoryMap;
	 }   
}