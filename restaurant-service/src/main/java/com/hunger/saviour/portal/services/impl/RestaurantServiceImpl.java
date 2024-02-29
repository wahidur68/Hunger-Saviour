package com.hunger.saviour.portal.services.impl;

import com.hunger.saviour.portal.dtos.RestaurantDTO;
import com.hunger.saviour.portal.dtos.RestaurantMenuDTO;
import com.hunger.saviour.portal.entities.RestaurantEntity;
import com.hunger.saviour.portal.entities.RestaurantMenuEntity;
import com.hunger.saviour.portal.services.RestaurantService;
import com.hunger.saviour.portal.repositories.RestaurantRepository;
import com.hunger.saviour.portal.utilities.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = RestaurantMapper.INSTANCE.DTOtoEntity(restaurantDTO);
        this.restaurantRepository.save(restaurantEntity);
        return RestaurantMapper.INSTANCE.entityToDTO(restaurantEntity);
    }

    @Override
    public Page<RestaurantDTO> getRestaurants(int offset, int pagesize) {
        Page<RestaurantEntity> restaurantEntityPage = this.restaurantRepository.findAll(PageRequest.of(offset, pagesize));
        return restaurantEntityPage.map(RestaurantMapper.INSTANCE::entityToDTO);
    }

    @Override
    public RestaurantDTO getRestaurantById(Integer restaurantId) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findById(restaurantId).get();
        List<RestaurantMenuDTO> menuDTOList=new ArrayList<>();
//        RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.entityToDTOWithMenus(restaurantEntity);
        for (RestaurantMenuEntity entity:restaurantEntity.getMenus()) {

            RestaurantMenuDTO menuDTO = RestaurantMenuDTO.builder()
                    .restaurantMenuId(entity.getRestaurantMenuId())
                    .itemName(entity.getItemName())
                    .description(entity.getDescription())
                    .menuImageUrl(entity.getMenuImageUrl())
                    .menuType(entity.getMenuType())
                    .price(entity.getPrice())
                    .build();
            menuDTOList.add(menuDTO);
        }
        RestaurantDTO restaurantDTO = RestaurantDTO.builder().restaurantId(restaurantEntity.getRestaurantId())
                .restaurantName(restaurantEntity.getRestaurantName())
                .rating(restaurantEntity.getRating())
                .location(restaurantEntity.getLocation())
                .menuTypes(restaurantEntity.getMenuTypes())
                .menuItems(menuDTOList)
                .imageUrl(restaurantEntity.getImageUrl())
                .build();
        return restaurantDTO;
    }
}
