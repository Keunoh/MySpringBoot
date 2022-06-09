package com.shopping.repository;

import com.shopping.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 상품의 이미지 정보를 저장하기 위한 Repository 인터페이스입니다.
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> { // 페이지 246
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);  // 페이지 251

    ItemImg findByItemIdAndRepImgYn(Long itemId, String repimgYn); // 페이지 312
}