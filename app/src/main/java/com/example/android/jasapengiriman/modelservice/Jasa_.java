
package com.example.android.jasapengiriman.modelservice;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.android.jasapengiriman.ApplicationBootCamp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = ApplicationBootCamp.class)
public class Jasa_ extends BaseModel implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private String id;
    @SerializedName("pengirim")
    @Expose
    @Column
    private String pengirim;
    @SerializedName("penerima")
    @Expose
    @Column
    private String penerima;
    @SerializedName("asal")
    @Expose
    @Column
    private String asal;
    @SerializedName("tujuan")
    @Expose
    @Column
    private String tujuan;
    @SerializedName("foto")
    @Expose
    @Column
    private String foto;
    public final static Parcelable.Creator<Jasa_> CREATOR = new Creator<Jasa_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Jasa_ createFromParcel(Parcel in) {
            return new Jasa_(in);
        }

        public Jasa_[] newArray(int size) {
            return (new Jasa_[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1103593686004471260L;

    protected Jasa_(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.pengirim = ((String) in.readValue((String.class.getClassLoader())));
        this.penerima = ((String) in.readValue((String.class.getClassLoader())));
        this.asal = ((String) in.readValue((String.class.getClassLoader())));
        this.tujuan = ((String) in.readValue((String.class.getClassLoader())));
        this.foto = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Jasa_() {
    }

    /**
     * 
     * @param asal
     * @param id
     * @param tujuan
     * @param pengirim
     * @param penerima
     * @param foto
     */
    public Jasa_(String id, String pengirim, String penerima, String asal, String tujuan, String foto) {
        super();
        this.id = id;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.asal = asal;
        this.tujuan = tujuan;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(pengirim);
        dest.writeValue(penerima);
        dest.writeValue(asal);
        dest.writeValue(tujuan);
        dest.writeValue(foto);
    }

    public int describeContents() {
        return  0;
    }

}
