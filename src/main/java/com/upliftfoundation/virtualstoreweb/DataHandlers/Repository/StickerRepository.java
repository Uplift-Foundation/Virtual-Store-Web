package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Sticker;

import org.springframework.stereotype.Repository;

@Repository
public interface StickerRepository<T extends Sticker> extends StoreItemRepository<T>{

}