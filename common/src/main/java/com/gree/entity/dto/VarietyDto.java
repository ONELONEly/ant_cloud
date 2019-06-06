package com.gree.entity.dto;
import com.gree.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class VarietyDto extends BaseEntity {
    private static final long serialVersionUID = 3417950800961938677L;

    /**
     * 种类
     */
    private Integer varietyId;

    /**
     * 种类类型 0：画册，1：礼品
     */
    private Integer varietyType;

    /**
     * 种类名称
     */
    private String varietyName;

    public VarietyDto(String varietyName) {
        this.varietyName = varietyName;
    }
}
