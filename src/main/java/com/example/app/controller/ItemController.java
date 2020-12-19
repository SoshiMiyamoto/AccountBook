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
@RequestMapping("/items")
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
	public String show(@PathVariable Long id, Model model) {
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
			return "redirect:/items";
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
			return "redirect:/items";
		}
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id) {
		itemService.delete(id);
		return "redirect:/items";
	}
	
	// カテゴリをセットする
	private Map<String,String> getCategories(){
	     Map<String, String> categoryMap = new LinkedHashMap<String, String>();
	     categoryMap.put("1", "交際費");
	     categoryMap.put("2", "食費");
	     categoryMap.put("3", "交通費");
	     categoryMap.put("4", "日用品");
	     categoryMap.put("5", "光熱費");
	     categoryMap.put("6", "通信費");
	     categoryMap.put("7", "住宅費");
	     categoryMap.put("9", "その他");
	     return categoryMap;
	 }   
}