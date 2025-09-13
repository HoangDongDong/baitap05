package service;

import java.util.List;
import model.Category;

public interface CategoryService {
	List<Category> findAll();

	Category findById(int id);

	void insert(Category c);

	void update(Category c);

	void delete(int id);
}
