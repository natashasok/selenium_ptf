package ru.stqa.training.selenium.model;

import java.util.Objects;

public class ProductData {
    private String title;
    private String campaignPrice;
    private String regularPrice;
    public ProductData withTitle (String title) {
        this.title = title;
        return this;
    }
    public ProductData withCampaignPrice (String campaignPrice) {
        this.campaignPrice = campaignPrice;
        return this;
    }
    public ProductData withRegularPrice (String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }
    public String getTitle() {return title;}
    public String regularPrice() {return regularPrice;}
    public String getCampaignPrice() {return campaignPrice;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductData that = (ProductData) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(campaignPrice, that.campaignPrice) &&
                Objects.equals(regularPrice, that.regularPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, campaignPrice, regularPrice);
    }
}
