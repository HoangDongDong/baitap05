package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDao;
import model.Category;
import model.DBConnection;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		String sql = "SELECT cate_id, cate_name, icons FROM Categoryy ORDER BY cate_id DESC";
		try (Connection c = new DBConnection().getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Category cat = new Category();
				cat.setCateId(rs.getInt("cate_id"));
				cat.setCateName(rs.getString("cate_name"));
				cat.setIcons(rs.getString("icons"));
				list.add(cat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Category findById(int id) {
		String sql = "SELECT cate_id, cate_name, icons FROM Categoryy WHERE cate_id=?";
		try (Connection c = new DBConnection().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Category(rs.getInt("cate_id"), rs.getString("cate_name"), rs.getString("icons"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Category cat) {
		String sql = "INSERT INTO Categoryy (cate_name, icons) VALUES (?, ?)";
		try (Connection c = new DBConnection().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, cat.getCateName());
			ps.setString(2, cat.getIcons());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Category cat) {
		String sql = "UPDATE Categoryy SET cate_name=?, icons=? WHERE cate_id=?";
		try (Connection c = new DBConnection().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, cat.getCateName());
			ps.setString(2, cat.getIcons());
			ps.setInt(3, cat.getCateId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Categoryy WHERE cate_id=?";
		try (Connection c = new DBConnection().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
