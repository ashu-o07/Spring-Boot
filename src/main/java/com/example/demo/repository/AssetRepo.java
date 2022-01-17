package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Asset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Long> {

	@Query(value = "select * from Asset a where lower(a.name)  like %:keyword% ", nativeQuery = true)
	List<Asset> findByKeyword(@Param("keyword") String keyword);

}
