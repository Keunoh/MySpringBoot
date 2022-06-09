package com.shopping.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.shopping.entity.ItemImg;
import com.shopping.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;

// 상품에 대한 이미지 정보를 업로드시키고, 이미지 정보를 저장해주는 서비스 클래스입니다.
@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {// 페이지 247
	// @Value : Properties 파일에서 지정 프로퍼티를 읽을 때 사용합니다.
	@Value("${itemImgLocation}") // application.properties 파일의 properties의 값을 읽어 옵니다.
	private String itemImgLocation ; // 상품 이미지가 업로드 되는 경로
	
	private final ItemImgRepository itemImgRepository ;
	private final FileService fileService ;
	
	// 상품의 이미지 정보를 저장해 줍니다.
	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
		String oriImgName = itemImgFile.getOriginalFilename() ; // 업로드 했던 상품 이미지의 원본 파일 이름
		String imgName = "" ; // 로컬에 저장된 이미지 파일의 이름(파일 형식은 'UUID.확장자' 형식입니다.)
		String imgUrl = "" ; // 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러 오는 경로
		
		System.out.println("itemImgLocation : " + itemImgLocation);
		
		// 파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName ;
			System.out.println("imgUrl : " + imgUrl);
		}
		
		// 상품 이미지 정보를 저장합니다.
		itemImg.updateItemImg(oriImgName, imgName, imgUrl); 
		
		itemImgRepository.save(itemImg) ;		
	}
	
	public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception { // 페이지 259
		/*
			itemImgId : 특정 상품 이미지에 대한 id 번호
			itemImgFile : 상품의 이미지 파일 정보
		*/
		if (!itemImgFile.isEmpty()) { // 업로드할 파일이 있으면
			// 상품 이미지 id(itemImgId)를 이용하여 이전에 저장하였던 해당 상품 이미지 Entity를 조회합니다.
			ItemImg savedItemImg 
				= itemImgRepository.findById(itemImgId)
					.orElseThrow(EntityNotFoundException::new); 
			
			// 기존에 등록된 상품 이미지 파일이 존재하면 해당 파일을 삭제합니다.
			if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
				fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
			}
			
			String oriImgName = itemImgFile.getOriginalFilename() ;
			
			// 업데이트하고자 하는 상품 이미지 파일을 업로드 합니다.
			String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes()) ;
			String imgUrl = "/images/item/" + imgName ;
			savedItemImg.updateItemImg(oriImgName, imgName, imgUrl) ;
		}
	}	
}