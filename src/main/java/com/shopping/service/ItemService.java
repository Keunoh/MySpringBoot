package com.shopping.service;

import com.shopping.dto.ItemFormDto;
import com.shopping.dto.ItemImgDto;
import com.shopping.entity.Item;
import com.shopping.entity.ItemImg;
import com.shopping.repository.ItemImgRepository;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
//상품을 등록해주는 서비스 클래스입니다.
public class ItemService {
	private final ItemRepository itemRepository ;
	private final ItemImgService itemImgService ;
	
	 // 페이지 248
	public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
		// 상품 등록
		// 상품 등록 폼에서 입력 받은 데이터를 이용하여 item 객체를 생성합니다. 
		Item item = itemFormDto.createItem() ; // p234
		itemRepository.save(item); // 상품 데이터를 저장합니다.
		
		// 이미지 등록
		for (int i = 0; i < itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			if (i==0) { // 1번째 이미지를 대표 이미지로 처리하기
				itemImg.setRepImgYn("Y");
			}else {
				itemImg.setRepImgYn("N");
			}
			
			// 상품의 이미지 정보를 저장합니다.
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		} // end for	
		
		return item.getId();
	}
	
	private final ItemImgRepository itemImgRepository ;
	
	@Transactional(readOnly = true) // 트랜잭션을 읽기 전용으로 설정합니다.
	// 등록된 상품을 불러 오는 메소드를 구현합니다.
	public ItemFormDto getItemDtl(Long itemId) { // itemId : 상품의 id   // 페이지 257
		// 해당 상품 id에 속해 있는 ItemImg 목록을 구하되, 정렬을 사용하기 등록된 순서대로 읽어 옵니다.
		List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
		
		List<ItemImgDto> itemImgDtoList = new ArrayList<ItemImgDto>() ;
		
		// 조회된 ItemImg를 반복문을 사용하여 ItemImgDto 객체로 변환 후 List 컬렉션에 추가합니다. 
		for(ItemImg itemImg : itemImgList) {
			ItemImgDto itemImgDto = ItemImgDto.of(itemImg) ;
			itemImgDtoList.add(itemImgDto) ;
		}
		
		// 상품의 id를 사용하여 상품 Entity 객체를 조회합니다.
		Item item = itemRepository.findById(itemId)
						.orElseThrow(EntityNotFoundException::new);
		ItemFormDto itemFormDto = ItemFormDto.of(item) ;
		itemFormDto.setItemImgDtoList(itemImgDtoList);
		
		return itemFormDto ;
	}

	// 상품을 업데이트 하기 위한 메소드입니다. // 페이지 261
	public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
		/*
			itemFormDto : 상품 등록 화면에서 전달 받은 dto 객체입니다.
		*/
		// 상품 수정
		// 상품 화면으로부터 전달 받은 상품 id를 사용하여 Entity를 조회합니다.
		Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
		item.updateItem(itemFormDto); // dto 객체를 사용하여 상품 Entity를 전달합니다.
		
		// 상품 이미지 id 리스트를 조회합니다.
		List<Long> itemImgIds = itemFormDto.getItemImgIds() ;
		
		// 상품 이미지 업데이트
		for (int i = 0; i < itemImgFileList.size(); i++) {
			itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
		}
		
		return item.getId() ;
	}
}
