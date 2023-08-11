package com.github.cloud.common.core.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : huweihua
 * @date 2023-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteRequest extends BaseRequest {

    private static final long serialVersionUID = 6570330618925738673L;

    private Long id;

    private Long versionNo;

    private Long modifyBy;
}
