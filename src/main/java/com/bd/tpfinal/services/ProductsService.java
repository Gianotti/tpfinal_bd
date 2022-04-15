package com.bd.tpfinal.services;

import com.bd.tpfinal.dtos.request.ProductRequestDto;
import com.bd.tpfinal.dtos.response.BaseResponseDto;

public interface ProductsService {

    BaseResponseDto update(String productId, ProductRequestDto productRequestDto);
    BaseResponseDto getProductsWithProductTypeBySupplier(String supplierId);
    BaseResponseDto getAverageProductPriceByProductType();
    BaseResponseDto create(ProductRequestDto product);
    void delete(String productId);
    BaseResponseDto retrieve();
    BaseResponseDto retrieve(String productId);


}
