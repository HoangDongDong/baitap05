package dao;

import java.util.List;
import model.Category;

public interface CategoryDao {
	List<Category> findAll();

	Category findById(int id);

	void insert(Category cat);

	void update(Category cat);

	void delete(int id);
}
