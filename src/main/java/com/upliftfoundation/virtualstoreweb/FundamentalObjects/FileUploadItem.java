package com.upliftfoundation.virtualstoreweb.FundamentalObjects;

import jakarta.persistence.MappedSuperclass;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Transient;

@MappedSuperclass
public class FileUploadItem extends WeightItem
{
    @Transient
    private MultipartFile picture;

    public FileUploadItem(String name, double price, String description, double weight) {
        super(name, price, description,weight);
    }
    
    public FileUploadItem(String name, double price, String description, double weight, MultipartFile picture) {
        super(name, price, description,weight);
        this.picture = picture;
    }

    public FileUploadItem() {
        super();
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
