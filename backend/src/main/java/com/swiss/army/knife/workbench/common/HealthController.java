package com.swiss.army.knife.workbench.common;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康檢查端點，供本機確認服務已啟動，之後也給 Docker / K8s 的 health check 使用。
 * 只回報程式本身有在跑，不檢查資料庫連線。
 */
@RestController
@RequestMapping("/api")
public class HealthController {

	@GetMapping("/health")
	public Map<String, String> health() {
		return Map.of("status", "UP");
	}

}
