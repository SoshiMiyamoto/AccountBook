package com.example.app.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Item {

	private Long id;

	@NotBlank(message = "カテゴリは必須です。")
	private String category;
	
	private String[] categories;
	
	private String date;

	@Min(value = 10, message = "10以上の数値を入力してください。")
	@Max(value = 100000, message = "100000以下の数値を入力してください。")
	private float expenses;

	@Size(max = 50, message = "ベーダー名は50文字を超えないでください。")
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getExpenses() {
		return expenses;
	}

	public void setExpenses(float expenses) {
		this.expenses = expenses;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
