package com.upliftfoundation.virtualstoreweb.DataHandlers;

import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;

public interface ICreateData{
    public UUID Create(StoreItem item);
}
