package com.gree.entity.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gree.entity.BaseEntity;
import com.gree.entity.vo.FileVO;
import com.gree.entity.vo.MaterialVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StorageMaterialVO extends BaseEntity {

    private static final long serialVersionUID = -8320470909800531772L;
    private String receiver;
    private String venderId;
    private String telephone;
    private List<MaterialVO> materialList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime storageDate;
    private String business;
    private List<FileVO> pictureList;
}
