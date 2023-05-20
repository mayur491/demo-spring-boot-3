package com.codemayur.core.record;

import com.codemayur.core.enums.ProductType;
import lombok.Builder;

@Builder
public record GenerateRtoRecord(BureauReportRecord bureauReportRecord, ProductType productType) {
}
