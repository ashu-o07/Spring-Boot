package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.AssetRepo;
import com.example.demo.repository.CatRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.model.Asset;
import com.example.demo.model.AssetStatus;
import com.example.demo.model.Category;
import com.example.demo.model.Employee;

@Controller
@RequestMapping("/api")
public class AssetController {

	@Autowired
	AssetRepo asset_repo;
	@Autowired
	CatRepo cat_repo;
	@Autowired
	EmployeeRepo em_repo;

	@GetMapping("/asset")
	public String getAllAssets(Model model) {

		Asset a = new Asset();
		List<Asset> list = asset_repo.findAll();
		List<Category> cat = cat_repo.findAll();
		List<Employee> emp = em_repo.findAll();

		model.addAttribute("list", list);
		model.addAttribute("asset", a);
		model.addAttribute("cat", cat);
		model.addAttribute("emp", emp);
		model.addAttribute("identifyPage", 0);
		return "asset"; // src/main/resources/templates/asset.html

	}

	// Adding a new Asset
	@PostMapping("/asset")
	public String addAsset(@ModelAttribute("asset") Asset asset) {

		asset_repo.save(asset);
		return "redirect:/api/asset";
	}

	// **************************************** Update Asset methods/api starts************************************
	@GetMapping("/asset/{id}")
	public String updateAssetForm(@PathVariable Long id, Model model) {

		Optional<Asset> a = asset_repo.findById(id);

		List<Asset> list = asset_repo.findAll();
		List<Category> cat = cat_repo.findAll();
		List<Employee> emp = em_repo.findAll();

		model.addAttribute("list", list);

		model.addAttribute("cat", cat);
		model.addAttribute("emp", emp);
		model.addAttribute("identifyPage", 1);
		model.addAttribute("asset", a.get());

		return "asset";

	}

	@PostMapping("/UpdateAsset")
	public String updateAsset(@ModelAttribute("asset") Asset asset) {

		asset_repo.save(asset);

		return "redirect:/api/asset";

	}
	// **************************************** Update Asset methods/api ends************************************

	// **************************************** Assigning Asset methods/api starts************************************
	@GetMapping("/assignAsset/{id}")
	public String assignAssetForm(@PathVariable Long id, Model model) {

		Optional<Asset> a = asset_repo.findById(id);

		List<Asset> list = asset_repo.findAll();
		List<Category> cat = cat_repo.findAll();
		List<Employee> emp = em_repo.findAll();

		model.addAttribute("list", list);

		model.addAttribute("cat", cat);
		model.addAttribute("emp", emp);
		model.addAttribute("identifyPage", 2);
		model.addAttribute("asset", a.get());

		return "asset";

	}

	@PostMapping("/assignAsset")
	public String assignAsset(@ModelAttribute("asset") Asset asset) {

		asset.setStatus(AssetStatus.Assigned);
		asset_repo.save(asset);

		return "redirect:/api/asset";

	}

	// **************************************** Assigning Asset methods/api ends************************************

	// Recover asset assigned to a employee
	@GetMapping("/recoverAsset/{id}")
	public String recoverAsset(@PathVariable Long id, Model model) {

		Optional<Asset> asset = asset_repo.findById(id);

		asset.get().setStatus(AssetStatus.Recovered);
		asset_repo.save(asset.get());

		List<Asset> list = asset_repo.findAll();
		List<Category> cat = cat_repo.findAll();
		List<Employee> emp = em_repo.findAll();

		model.addAttribute("list", list);

		model.addAttribute("cat", cat);
		model.addAttribute("emp", emp);
		model.addAttribute("identifyPage", 2);
		model.addAttribute("asset", asset.get());

		return "asset";

	}

	// delete an asset
	@GetMapping("/deleteAsset/{id}")
	public String deleteAsset(@PathVariable Long id) {

		Optional<Asset> asset = asset_repo.findById(id);
		if (asset.isPresent()) {

			asset_repo.delete(asset.get());
		}
		return "redirect:/api/asset";

	}

	// Searching api
	@GetMapping(path = { "/searchAsset" })
	public String home(Model model, String keyword) {
		if (keyword != null) {
			List<Asset> list = asset_repo.findByKeyword(keyword.toLowerCase());
			model.addAttribute("list", list);
		} else {
			List<Asset> list = asset_repo.findAll();
			model.addAttribute("list", list);
		}

		Asset a = new Asset();

		List<Category> cat = cat_repo.findAll();
		List<Employee> emp = em_repo.findAll();

		model.addAttribute("asset", a);

		model.addAttribute("cat", cat);
		model.addAttribute("emp", emp);
		model.addAttribute("identifyPage", 0);
		return "asset";

	}
}
