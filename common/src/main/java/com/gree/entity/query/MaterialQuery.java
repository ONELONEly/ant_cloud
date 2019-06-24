package com.gree.entity.query;

import com.gree.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MaterialQuery extends BaseEntity {
    private static final long serialVersionUID = 2475313913120637224L;

    private Integer pageNumber;
    private Integer pageSize;
    private Integer varietyType;
}
