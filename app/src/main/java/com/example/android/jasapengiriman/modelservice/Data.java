
package com.example.android.jasapengiriman.modelservice;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("jasa")
    @Expose
    private List<Jasa_> jasa = null;
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5320119319872178473L;

    protected Data(Parcel in) {
        in.readList(this.jasa, (com.example.android.jasapengiriman.modelservice.Jasa_.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param jasa
     */
    public Data(List<Jasa_> jasa) {
        super();
        this.jasa = jasa;
    }

    public List<Jasa_> getJasa() {
        return jasa;
    }

    public void setJasa(List<Jasa_> jasa) {
        this.jasa = jasa;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(jasa);
    }

    public int describeContents() {
        return  0;
    }

}
