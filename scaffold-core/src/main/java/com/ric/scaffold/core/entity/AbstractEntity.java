package com.ric.scaffold.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author：ric
 * @date 2023-03-28 14:41
 * @description 通用 entity
 */
@Data
public abstract class AbstractEntity {

  /** 主键 */
  @TableId(type = IdType.AUTO)
  private Long id;

  /** 创建时间 */
  @ApiModelProperty(hidden = true)
  @TableField(fill = FieldFill.INSERT)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;

  /** 更新时间 */
  @ApiModelProperty(hidden = true)
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;

  /** 逻辑删除 */
  @ApiModelProperty(value = "逻辑删除 0-未删除 1-已删除)")
  @JsonIgnore
  @TableLogic
  @TableField(value = "deleted", fill = FieldFill.INSERT)
  private Integer deleted;

  /** 乐观锁 */
  @ApiModelProperty(value = "版本号")
  @Version
  @TableField(value = "version", fill = FieldFill.INSERT)
  private Integer version;

}
