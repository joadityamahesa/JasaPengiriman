package com.example.android.jasapengiriman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.jasapengiriman.Adapter.AdapterListJasa;
import android.support.v7.widget.RecyclerView;


import com.example.android.jasapengiriman.modelservice.Jasa_;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.android.jasapengiriman.APIService.APIInterfacesRest;
import com.example.android.jasapengiriman.APIService.APIClient;
import com.example.android.jasapengiriman.modelservice.Jasa;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListJasa extends AppCompatActivity {
    private RecyclerView rc ;
    private AdapterListJasa mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jasa);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);


        rc = (RecyclerView) findViewById(R.id.recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setHasFixedSize(true);

        initToolbar();
        getAllJasa();

    }
    private void getAllJasa () {
        APIInterfacesRest apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        Call<Jasa> jasa = apiInterface.getAllList();
        jasa.enqueue(new Callback<Jasa>() {
            @Override
            public void onResponse(Call<Jasa> call, Response<Jasa> response) {
                Jasa j = response.body();
                if (response.body() != null) {
                    if (j.getData().getJasa().size() > 0) {

                        setAdapterList(j.getData().getJasa());
                        //savedb(j.getData().getJasa());
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListJasa.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListJasa.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Jasa> call, Throwable t) {
                Toast.makeText(ListJasa.this, "Terjadi gangguan koneksi", Toast.LENGTH_LONG).show();
                call.cancel();
                // getOffline();
            }
        });

    }

    private void setAdapterList(List<Jasa_> items){

        //set data and list adapter
        mAdapter = new AdapterListJasa(this, items);
        rc.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdapterListJasa.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Jasa_ obj, int position) {
                Intent intent = new Intent(ListJasa.this,DetailJasa.class);
                Bundle b = new Bundle();
                b.putSerializable("data", obj);
                b.putString("type","update");
                intent.putExtras(b);
                startActivityForResult(intent,88);
            }
        });




    }

//    public void savedb(List<Jasa_> items){
//
//        FlowManager.getDatabase(ApplicationBootCamp.class)
//                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
//                        new ProcessModelTransaction.ProcessModel<Jasa_>() {
//                            @Override
//                            public void processModel(Jasa_ orderItem, DatabaseWrapper wrapper) {
//
//                                orderItem.save();
//
//
//                            }
//
//                        }).addAll(items).build())  // add elements (can also handle multiple)
//                .error(new Transaction.Error() {
//                    @Override
//                    public void onError(Transaction transaction, Throwable error) {
//                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                })
//                .success(new Transaction.Success() {
//                    @Override
//                    public void onSuccess(Transaction transaction) {
//                        Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();
//
//                    }
//                }).build().execute();
//
//
//    }

//    public List<Jasa_> getAllJasaOffline (){
//
//
//        List<Jasa_> news = SQLite.select()
//                .from(Jasa_.class)
//                //    .where(User_Table.age.greaterThan(18))
//                .queryList();
//
//        return news;
//    }

/*
    public void sqlQueryList(String orderno){

        String rawQuery = "SELECT distinct * FROM `Dataorder` where driver ='"+AppController.username+"' and shipmentNo ='"+orderno+"' group by shipmentNo;";
        StringQuery<Dataorder> stringQuery = new StringQuery<>(Dataorder.class, rawQuery);
        stringQuery
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<Dataorder>() {
                                             @Override
                                             public void onListQueryResult(QueryTransaction transaction, @NonNull List<Dataorder> models) {

                                                 progress_bar.setVisibility(View.GONE);
                                                 fab.setAlpha(1f);
                                                 if (models.size()>0){

                                                     Intent intent = new Intent(getApplicationContext(),ShipToActivity.class);
                                                     intent.putExtra("shipmentno",models.get(0).getShipmentNo());
                                                     startActivityForResult(intent,999);

                                                 }else{
                                                     Toast.makeText(getApplicationContext(),"Document ID Not Found !",Toast.LENGTH_LONG).show();
                                                 }


                                             }
                                         }


                )
                .execute();
*/


//    @Override
//    protected void onResume() {
//        super.onResume();
//        getAllJasa();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {

            Intent intent = new Intent(ListJasa.this,DetailJasa.class);
            Bundle b = new Bundle();
            b.putString("type","save");
            intent.putExtras(b);
            startActivityForResult(intent,88);


        } else {
//            Intent intent = new Intent(MainActivity.this,NewsDetail.class);
//            Bundle b = new Bundle();
//            b.putSerializable("data", obj);
//            intent.putExtras(b);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ListJasa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
//    private void getOffline (){
//
//        setAdapterList(getAllJasaOffline());
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==99 & requestCode==88){
            getAllJasa();
        }
    }
}
