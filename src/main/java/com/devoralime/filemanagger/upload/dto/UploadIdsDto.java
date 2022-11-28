package com.devoralime.filemanagger.upload.dto;

import java.util.List;

public class UploadIdsDto {
    private List<Long> ids;

    public UploadIdsDto() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
