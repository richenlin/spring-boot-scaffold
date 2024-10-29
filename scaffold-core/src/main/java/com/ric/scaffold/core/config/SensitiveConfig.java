package com.ric.scaffold.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 日志敏感词配置
 */
@Component
@ConfigurationProperties(prefix = "logging.sensitive")
public class SensitiveConfig {
  private String[] words;

  public String[] getWords() {
    return words;
  }

  public void setWords(String[] words) {
    this.words = words;
  }
}