package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

/**
 * маппер для {@link AdEntity} в
 * {@link Ad},
 * {@link Ads},
 * {@link CreateOrUpdateAd},
 * {@link ExtendedAd}
 * и обратно
 */
@Mapper(componentModel = "spring")
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    String ADS_IMAGES = "/ads/image/";

    //_____ toEntity___
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "image", target = "image.filePath")
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "title", target = "name")
    AdEntity toEntity(Ad ad);

    //    AdEntity toEntity(Ads ads);

    @Mapping(source = "title", target = "name")

    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);

    //
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "authorLastName", target = "author.lastName")
    @Mapping(source = "email", target = "author.email")
    @Mapping(source = "image", target = "image.id")
    @Mapping(source = "phone", target = "author.phone")
    @Mapping(source = "title", target = "name")
    AdEntity toEntity(ExtendedAd extendedAd);

    //
    //_____ toDto___
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "image",qualifiedByName = "imageMapping")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "title", source = "name")
    Ad toAd(AdEntity adEntity);

    //    Ads toAds(AdEntity adEntity);
    @Mapping(source = "name", target = "title")
    CreateOrUpdateAd toCreateOrUpdateAd(AdEntity adEntity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", source = "image",qualifiedByName = "imageMapping")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "title", source = "name")
    ExtendedAd toExtendedAd(AdEntity adEntity);



    @Mapping(target = "count", source = "size")
    @Mapping(target = "results", source = "list")
    Ads toAds(Integer size, List<AdEntity> list);


    @Named("imageMapping")
    default String imageMapping(ImageEntity image) {
        if (image == null) {
            return null;
        }
        return ADS_IMAGES + image.getId();
    }
}
