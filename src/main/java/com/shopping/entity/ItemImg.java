package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="item_img")
@Getter @Setter // 페이지 230
// 상품의 이미지를 저장해주는 Entity 파일입니다.
public class ItemImg extends BaseEntity{
	@Id
	@Column(name="item_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private String imgName ; // 이미지 파일 이름('UUID.확장자' 형식)
	private String oriImgName ; // 업로드 된 상품의 원본 이미지 파일 이름	
	private String imgUrl ; // 이미지 조회 경로
	private String repImgYn ; // 대표 이미지 여부
	
	// 상품 Entity와 다대일 관계로 매핑하되, 
	// 매핑된 상품 Entity 정보가 필요한 경우 조회하도록 지연 로딩으로 설정합니다.
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="item_id")
	private Item item ;
	
	// 이미지 정보를 업데이트 해주는 메소드 
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName ;
		this.imgName = imgName ; 
		this.imgUrl = imgUrl ;				
	}
}