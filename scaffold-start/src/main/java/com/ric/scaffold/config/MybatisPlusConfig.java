package com.ric.scaffold.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ric.scaffold.core.consts.CommonCode;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

  /**
   * 全局插件
   *
   * @return
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    // 分页插件
    mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
    // 禁止全表 删除/更新 插件
    mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    // 乐观锁插件
    mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    return mybatisPlusInterceptor;
  }

  /**
   * 自动填充字段
   *
   * @return
   */
  @Bean
  public MetaObjectHandler metaObjectHandler() {
    return new MetaObjectHandler() {
      @Override
      public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("deleted", CommonCode.NORMAL.getCode(), metaObject);
      }

      @Override
      public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
      }
    };
  }

  @Bean
  public PaginationInnerInterceptor paginationInterceptor() {
    PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
    // 设置请求的页面大于最大页后操作，true 调回到首页，false 继续请求，默认 false
    // paginationInterceptor.setOverflow(false);
    // 设置最大单页限制数量，默认 100 条，-1 不受限制
    paginationInterceptor.setMaxLimit(100L);
    return paginationInterceptor;
  }
}