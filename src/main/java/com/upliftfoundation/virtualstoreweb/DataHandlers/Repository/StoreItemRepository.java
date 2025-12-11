package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import java.util.UUID;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository<T extends StoreItem> extends JpaRepository<T, UUID> {   
}