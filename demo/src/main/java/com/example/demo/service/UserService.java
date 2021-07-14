package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * 全件取得
	 * @return
	 */
	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	/**
	 * 主キーを指定して1件取得
	 * @param id
	 * @return
	 */
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	/**
	 * 主キーを指定して削除
	 * @param id
	 */
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	/**
	 * レコード追加・更新
	 * @param student
	 */
	public void save(User student) {
		repository.save(student);
	}

	/**
	 * 名前の部分一致検索
	 * @param name
	 * @return
	 */
	public List<User> findByNameContaining(String name) {
		return repository.findByNameContaining(name);
	}

}
