
package com.ibm.mobileappbuilder.localsell20160923170219.ds;
import ibmmobileappbuilder.ds.restds.GeoPoint;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ProductsDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;

    @SerializedName("productimage") public String productimage;
    @SerializedName("productname") public String productname;
    @SerializedName("SalePrice") public String salePrice;
    @SerializedName("productlocation") public GeoPoint productlocation;

    @Override
    public void setIdentifiableId(String id) {
        this.cloudantIdentifiableId = id;
    }

    @Override
    public String getIdentifiableId() {
        return cloudantIdentifiableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cloudantIdentifiableId);
        dest.writeString(productimage);
        dest.writeString(productname);
        dest.writeString(salePrice);
        dest.writeDoubleArray(productlocation != null  && productlocation.coordinates.length != 0 ? productlocation.coordinates : null);
    }

    public static final Creator<ProductsDSSchemaItem> CREATOR = new Creator<ProductsDSSchemaItem>() {
        @Override
        public ProductsDSSchemaItem createFromParcel(Parcel in) {
            ProductsDSSchemaItem item = new ProductsDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            item.productimage = in.readString();
            item.productname = in.readString();
            item.salePrice = in.readString();
            double[] productlocation_coords = in.createDoubleArray();
            if (productlocation_coords != null)
                item.productlocation = new GeoPoint(productlocation_coords);
            return item;
        }

        @Override
        public ProductsDSSchemaItem[] newArray(int size) {
            return new ProductsDSSchemaItem[size];
        }
    };
}


