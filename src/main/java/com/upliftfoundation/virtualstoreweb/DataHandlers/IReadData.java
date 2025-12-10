package com.upliftfoundation.virtualstoreweb.DataHandlers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;

public interface IReadData {
    public StoreItem Read(UUID ID);
    public List<StoreItem> ReadAll();
}
