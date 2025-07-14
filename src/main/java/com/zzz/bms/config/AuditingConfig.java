package com.zzz.bms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 启用JPA的自动审计功能（用于@CreatedDate等注解生效）
@Configuration
@EnableJpaAuditing
public class AuditingConfig {
}